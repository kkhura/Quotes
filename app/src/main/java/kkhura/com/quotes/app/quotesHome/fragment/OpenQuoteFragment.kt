package kkhura.com.quotes.app.quotesHome.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kkhura.com.quotes.app.R
import kkhura.com.quotes.app.quotesHome.adapter.QuotesAdapter
import kkhura.com.quotes.app.quotesHome.model.QuoteModel
import kkhura.com.quotes.app.quotesHome.viewmodel.QuoteCategoryViewModel
import kkhura.com.quotes.app.utility.BaseFragment
import kotlinx.android.synthetic.main.fragment_open_quote.*

private const val CATEGORY_ID: String = "CATEGORY_ID"

/**
 * A simple [Fragment] subclass.
 * Use the [OpenQuoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class OpenQuoteFragment : BaseFragment() {
    private var list: ArrayList<QuoteModel>? = null
    private var _id: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            _id = it.getInt(CATEGORY_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_open_quote, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        list = ArrayList()
        rvQuotes.layoutManager = LinearLayoutManager(activity)
        rvQuotes.adapter = QuotesAdapter(list, this.activity!!)

        var quoteCategoryViewModel: QuoteCategoryViewModel = ViewModelProviders.of(this).get(QuoteCategoryViewModel::class.java)
        quoteCategoryViewModel.getQuotes(_id).observe(this, Observer { listQuotes ->
            if (listQuotes != null) {
                bindDataWithUi(listQuotes)
            }
        })
    }

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