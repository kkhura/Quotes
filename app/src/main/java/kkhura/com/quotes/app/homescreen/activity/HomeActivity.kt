package kkhura.com.quotes.app.homescreen.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import kkhura.com.quotes.app.database.MyDatabase
import kkhura.com.quotes.app.utility.BaseActivity

import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity(){

    companion object {
        fun newIntent(context: Context, bundle: Bundle): Intent {
            val intent = Intent(context, HomeActivity::class.java)
            intent.putExtras(bundle)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fab.setOnClickListener { view ->

        }
        selectItem(1);

//        Thread(Runnable { MyDatabase.getInstance(this); }).start()
    }
}