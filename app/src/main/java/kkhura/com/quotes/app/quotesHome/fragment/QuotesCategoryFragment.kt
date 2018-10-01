package kkhura.com.quotes.app.quotesHome.fragment

import android.app.Fragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.ContextWrapper
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import android.widget.Toast
import kkhura.com.quotes.app.R
import kkhura.com.quotes.app.database.MyDatabase
import kkhura.com.quotes.app.homescreen.adapter.QuotesCategoryAdapter
import kkhura.com.quotes.app.quotesHome.adapter.OnItemClicked
import kkhura.com.quotes.app.quotesHome.model.QuotesCategoryModel
import kkhura.com.quotes.app.quotesHome.viewmodel.QuoteCategoryViewModel
import kkhura.com.quotes.app.utility.BaseFragment
import kotlinx.android.synthetic.main.fragment_quotes_category.*
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream


/**
 * A simple [Fragment] subclass.
 * Use the [QuotesCategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class QuotesCategoryFragment : BaseFragment(), OnItemClicked {

    private lateinit var quoteCategoryViewModel: QuoteCategoryViewModel

    override fun itemClicked(postion: Int) {
        val transaction = activity!!.supportFragmentManager.beginTransaction();
        transaction.add(R.id.frameContainer, OpenQuoteFragment.newInstance(categoryList.get(postion)._id!!))
        transaction.commit()
    }

    private val mUiHandler = Handler()


    private val categoryList: ArrayList<QuotesCategoryModel> = ArrayList()

    private var isGrid: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {


        }

        copyDataBase()


        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        if (inflater != null) {
            inflater.inflate(R.menu.menu_format, menu)
        }

        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.itemId) {
                R.id.menuGrid -> isGrid = true
                R.id.menuList -> isGrid = false
            }
            onLayoutManagerGrid(isGrid)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onLayoutManagerGrid(isGridLayout: Boolean) {
        if (isGridLayout) {
            recycleView.layoutManager = GridLayoutManager(activity, 2)
        } else {
            recycleView.layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun copyDataBase() {
        val cw = ContextWrapper(activity!!.applicationContext)
        var DB_PATH: String = "/data/data/kkhura.com.quotes.app" + "/databases/"
        val buffer = ByteArray(1024)
        var myOutput: OutputStream? = null
        var length: Int
        // Open your local db as the input stream
        var myInput: InputStream? = null
        try {
            val DB_NAME = "quotes.db"
            myInput = activity!!.applicationContext.getAssets().open(DB_NAME)
            // transfer bytes from the inputfile to the
            // outputfile
            myOutput = FileOutputStream(DB_PATH + DB_NAME)
            length = myInput!!.read(buffer)
            while (length > 0) {
                myOutput!!.write(buffer, 0, length)
                length = myInput!!.read(buffer)
            }
            myOutput!!.close()
            myOutput!!.flush()
            myInput!!.close()
            Log.i("Database",
                    "New database has been copied to device!")


        } catch (e: IOException) {
            e.printStackTrace()
        }

    }


/*    private fun fetchQuotesCategoryDataFromDb() {
        var task = Runnable {
            val listQuotesCategoryModel: List<QuotesCategoryModel>? =
                    mDB?.quotesCategoryDao()?.getAll()


            mUiHandler.post({
                if (listQuotesCategoryModel == null || listQuotesCategoryModel?.size == 0) {
                    Toast.makeText(activity, "No data in cache..!!", Toast.LENGTH_SHORT).show()
                } else {
                    bindDataWithUi(listQuotesCategoryModel);
                }
            })
        }
        mDbWorkerThread.postTask(task)
    }*/

    private fun bindDataWithUi(listQuotesCategoryModel: List<QuotesCategoryModel>) {
        categoryList.addAll(listQuotesCategoryModel)
        recycleView.adapter.notifyDataSetChanged()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_quotes_category, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isGrid = false;

        recycleView.adapter = QuotesCategoryAdapter(categoryList, this!!.activity!!, this, isGrid)
        onLayoutManagerGrid(isGrid)

        quoteCategoryViewModel = ViewModelProviders.of(this).get(QuoteCategoryViewModel::class.java)
        quoteCategoryViewModel.quoteCategoryList.observe(
                this,
                Observer {
                    @Override
                    fun onChanged(listQuotesCategoryModel: List<QuotesCategoryModel>?) {
                        if (listQuotesCategoryModel == null || listQuotesCategoryModel?.size == 0) {
                            Toast.makeText(activity, "No data in cache..!!", Toast.LENGTH_SHORT).show()
                        } else {
                            bindDataWithUi(listQuotesCategoryModel);
                        }

                    }
                }
        )
    }

    override fun onDestroy() {
        MyDatabase.destroyInstance()
//        mDbWorkerThread.quit()
        super.onDestroy()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuotesCategoryFragment.
         */
        @JvmStatic
        fun newInstance() =
                QuotesCategoryFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}