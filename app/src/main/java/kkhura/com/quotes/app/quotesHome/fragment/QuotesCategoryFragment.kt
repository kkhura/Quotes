package kkhura.com.quotes.app.quotesHome.fragment

import android.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.*
import kkhura.com.quotes.app.R
import kkhura.com.quotes.app.homescreen.adapter.OnItemClicked
import kkhura.com.quotes.app.homescreen.adapter.QuotesCategoryAdapter
import kkhura.com.quotes.app.quotesHome.model.QuotesCategoryModel
import kkhura.com.quotes.app.quotesHome.viewmodel.QuoteCategoryViewModel
import kkhura.com.quotes.app.utility.BaseFragment
import kotlinx.android.synthetic.main.fragment_quotes_category.*


/**
 * A simple [Fragment] subclass.
 * Use the [QuotesCategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class QuotesCategoryFragment : BaseFragment(), OnItemClicked {

    private val categoryList: ArrayList<QuotesCategoryModel> = ArrayList()
    private var isGrid: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_quotes_category, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isGrid = false

        recycleView.adapter = QuotesCategoryAdapter(categoryList, this.activity!!, this, isGrid)
        onLayoutManagerGrid(isGrid)

        var quoteCategoryViewModel: QuoteCategoryViewModel = ViewModelProviders.of(this).get(QuoteCategoryViewModel::class.java)
        quoteCategoryViewModel.getQuoteCategoryList().observe(this, Observer { listQuotesCategoryModel ->
            if (listQuotesCategoryModel != null) {
                bindDataWithUi(listQuotesCategoryModel)
            }
        })



        for((intex,value) in categoryList.withIndex()){

        }
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
            recycleView.adapter = QuotesCategoryAdapter(categoryList, this.activity!!, this, isGrid)
            if (recycleView.adapter is QuotesCategoryAdapter) {
                (recycleView.adapter as QuotesCategoryAdapter).isGrid = isGrid
            }
            onLayoutManagerGrid(isGrid)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onLayoutManagerGrid(isGridLayout: Boolean) {
        if (isGridLayout) {
            recycleView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(activity, 2)
        } else {
            recycleView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        }
    }


    private fun bindDataWithUi(listQuotesCategoryModel: List<QuotesCategoryModel>) {
        categoryList.addAll(listQuotesCategoryModel)
        recycleView.adapter!!.notifyDataSetChanged()
    }

    override fun itemClicked(postion: Int) {
        val transaction = fragmentManager!!.beginTransaction()
        transaction.add(R.id.frameContainer, OpenQuoteFragment.newInstance(categoryList.get(postion)._id!!, categoryList.get(postion).name))
        transaction.addToBackStack(OpenQuoteFragment::class.java.name)
        transaction.commit()
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