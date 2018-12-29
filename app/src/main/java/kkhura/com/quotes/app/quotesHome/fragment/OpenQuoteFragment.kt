package kkhura.com.quotes.app.quotesHome.fragment


import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import kkhura.com.quotes.app.R
import kkhura.com.quotes.app.quotesHome.adapter.OnItemClicked
import kkhura.com.quotes.app.quotesHome.adapter.QuotesAdapter
import kkhura.com.quotes.app.quotesHome.model.QuoteModel
import kkhura.com.quotes.app.quotesHome.viewmodel.QuoteCategoryViewModel
import kkhura.com.quotes.app.utility.BaseActivity
import kkhura.com.quotes.app.utility.BaseFragment
import kotlinx.android.synthetic.main.fragment_open_quote.*

private const val CATEGORY_ID: String = "CATEGORY_ID"
private const val CATEGORY_TITLE: String = "CATEGORY_TITLE"

/**
 * A simple [Fragment] subclass.
 * Use the [OpenQuoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class OpenQuoteFragment : BaseFragment(), OnItemClicked {
    override fun itemClicked(postion: Int, text: String) {
        val transaction: androidx.fragment.app.FragmentTransaction = fragmentManager!!.beginTransaction()
        transaction.add(R.id.frameContainer, EditQuoteFragment.newInstance(quoteTitle, text))
        transaction.addToBackStack(EditQuoteFragment::class.java.name)
        transaction.commit()
    }

    private var list: ArrayList<QuoteModel>? = null
    private var _id: Int = 0
    private var quoteTitle = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            _id = it.getInt(CATEGORY_ID)
            quoteTitle = it.getString(CATEGORY_TITLE)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_open_quote, container, false)
    }

    override fun onResume() {
        (activity as BaseActivity).setToolBar(quoteTitle, true)
        super.onResume()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        list = ArrayList()
        rvQuotes.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        rvQuotes.adapter = QuotesAdapter(list, this.activity!!, this)

        var quoteCategoryViewModel: QuoteCategoryViewModel = ViewModelProviders.of(this).get(QuoteCategoryViewModel::class.java)
        quoteCategoryViewModel.getQuotes(_id).observe(this, Observer { listQuotes ->
            if (listQuotes != null) {
                bindDataWithUi(listQuotes)
            }
        })
    }

    private fun bindDataWithUi(listQuoteModel: List<QuoteModel>) {
        list!!.addAll(listQuoteModel)
        rvQuotes.adapter!!.notifyDataSetChanged()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param _id
         * @param name
         * @return A new instance of fragment OpenQuoteFragment.
         */
        @JvmStatic
        fun newInstance(_id: Int, quoteTitle: String) =
                OpenQuoteFragment().apply {
                    arguments = Bundle().apply {
                        putInt(CATEGORY_ID, _id)
                        putString(CATEGORY_TITLE, quoteTitle)
                    }
                }
    }
}