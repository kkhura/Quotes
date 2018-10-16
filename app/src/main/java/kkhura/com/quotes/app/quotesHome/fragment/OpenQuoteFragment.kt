package kkhura.com.quotes.app.quotesHome.fragment


import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kkhura.com.quotes.app.R
import kkhura.com.quotes.app.database.DbWorkerThread
import kkhura.com.quotes.app.database.MyDatabase
import kkhura.com.quotes.app.quotesHome.adapter.QuotesAdapter
import kkhura.com.quotes.app.quotesHome.model.QuoteModel
import kkhura.com.quotes.app.utility.BaseFragment
import kotlinx.android.synthetic.main.fragment_open_quote.*
import kotlinx.android.synthetic.main.fragment_quotes_category.*

private const val CATEGORY_ID: String = "CATEGORY_ID"

/**
 * A simple [Fragment] subclass.
 * Use the [OpenQuoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class OpenQuoteFragment : BaseFragment() {
    private var list: ArrayList<QuoteModel>? = null
    private var mDB: MyDatabase? = null
//    private lateinit var mDbWorkerThread: DbWorkerThread
    private val mUiHandler = Handler()
    private var _id: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            _id = it.getInt(CATEGORY_ID)
        }
//        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
//        mDbWorkerThread.start()


//        mDB = MyDatabase.getInstance(activity!!.applicationContext)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_open_quote, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        list = ArrayList();
        rvQuotes.layoutManager = LinearLayoutManager(activity)
        rvQuotes.adapter = QuotesAdapter(list, this!!.activity!!)
//        fetchQuotesCategoryDataFromDb()
    }

    /*private fun fetchQuotesCategoryDataFromDb() {
        var task = Runnable {
            val listQuoteModel: List<QuoteModel>? =
                    mDB?.quotesCategoryDao()?.findQuoteId(_id)


            mUiHandler.post({
                if (listQuoteModel == null || listQuoteModel?.size == 0) {
                    Toast.makeText(activity, "No data in cache..!!", Toast.LENGTH_SHORT).show()
                } else {
                    bindDataWithUi(listQuoteModel);
                }
            })
        }
//        mDbWorkerThread.postTask(task)
    }*/

    private fun bindDataWithUi(listQuoteModel: List<QuoteModel>) {
        list!!.addAll(listQuoteModel)
        rvQuotes.adapter.notifyDataSetChanged()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment OpenQuoteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(_id: Int) =
                OpenQuoteFragment().apply {
                    arguments = Bundle().apply {
                        putInt(CATEGORY_ID, _id)
                    }
                }
    }
}