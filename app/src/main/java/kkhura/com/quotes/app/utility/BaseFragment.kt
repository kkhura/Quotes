package kkhura.com.quotes.app.utility

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
import kkhura.com.quotes.app.customview.CustomFontTextView
import kkhura.com.quotes.app.quotesHome.fragment.QuotesCategoryFragment


open class BaseFragment : androidx.fragment.app.Fragment() {


    open fun setTextViewProperty(textView: CustomFontTextView){
//        textView.setTextColor();
//        textView.setTextSize();
//        textView
    }


    override fun onCreate(savedInstanceState: Bundle?) {
//        if(this is QuotesCategoryFragment){
            setHasOptionsMenu(true)
//        }else{
//            setHasOptionsMenu(false)
//        }
        super.onCreate(savedInstanceState)
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        if(!(this is QuotesCategoryFragment)){
menu!!.clear();
        }
            super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
    }

}
