package kkhura.com.quotes.app.utility

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import kkhura.com.quotes.app.quotesHome.fragment.QuotesCategoryFragment
import kkhura.com.quotes.app.R
import kkhura.com.quotes.app.homescreen.fragment.HomeFragment

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
    }

    open fun selectItem(position: Int) {
        when (position) {
            1 -> {
                val transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.frameContainer, QuotesCategoryFragment.newInstance())
                transaction.commit()
            }
            2 -> {
                val transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.frameContainer, HomeFragment.newInstance())
                transaction.commit()
            }
        }
    }
}