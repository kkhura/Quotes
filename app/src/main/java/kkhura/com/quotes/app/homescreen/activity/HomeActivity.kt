package kkhura.com.quotes.app.homescreen.activity

import android.app.FragmentManager
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
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

    }

    /*override fun onBackPressed() {
        if (supportFragmentManager.getBackStackEntryCount() > 0) {
            supportFragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }*/

}