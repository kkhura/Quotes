package kkhura.com.quotes.app.homescreen.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import kkhura.com.quotes.app.R
import kkhura.com.quotes.app.quotesHome.fragment.QuotesCategoryFragment
import kkhura.com.quotes.app.utility.BaseActivity


class HomeActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context, bundle: Bundle): Intent {
            val intent = Intent(context, HomeActivity::class.java)
            intent.putExtras(bundle)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ViewModelProviders.of(this)
        selectItem(1)

        supportFragmentManager.addOnBackStackChangedListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.frameContainer)
            if (fragment != null)
                updateTitle(fragment)
        }

    }

    override fun onBackPressed() {
        if (supportFragmentManager.getBackStackEntryCount() > 0) {
            supportFragmentManager.popBackStack();
        } else {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    private fun updateTitle(fragment: androidx.fragment.app.Fragment?) {
        if (fragment is QuotesCategoryFragment) {
            setToolBar(getString(R.string.app_name), false)
        }
    }

}