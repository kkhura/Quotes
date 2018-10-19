package kkhura.com.quotes.app.quotesHome.fragment

import android.app.Fragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kkhura.com.quotes.app.R
import kkhura.com.quotes.app.homescreen.adapter.QuotesCategoryAdapter
import kkhura.com.quotes.app.quotesHome.adapter.OnItemClicked
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

        var quoteCategoryViewModel = ViewModelProviders.of(this).get(QuoteCategoryViewModel::class.java)
        quoteCategoryViewModel.getQuoteCategoryList().observe(this, Observer { listQuotesCategoryModel ->
            if (listQuotesCategoryModel != null) {
                bindDataWithUi(listQuotesCategoryModel)
            }
        })
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


    private fun bindDataWithUi(listQuotesCategoryModel: List<QuotesCategoryModel>) {
        categoryList.addAll(listQuotesCategoryModel)
        recycleView.adapter.notifyDataSetChanged()
    }

    override fun itemClicked(postion: Int) {
        val transaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.add(R.id.frameContainer, OpenQuoteFragment.newInstance(categoryList.get(postion)._id!!))
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