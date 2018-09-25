package kkhura.com.quotes.app

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kkhura.com.quotes.app.homescreen.activity.HomeActivity
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.concurrent.TimeUnit
import com.google.common.io.Flushables.flush
import kkhura.com.quotes.app.database.MyDatabase
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setSupportActionBar(toolbar)


        Handler().postDelayed(Runnable { HomeActivity.newIntent(this, Bundle.EMPTY) }, TimeUnit.MILLISECONDS.toMillis(30));
    }

}
