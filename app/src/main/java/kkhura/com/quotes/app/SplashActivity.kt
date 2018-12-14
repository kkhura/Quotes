package kkhura.com.quotes.app

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kkhura.com.quotes.app.homescreen.activity.HomeActivity
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.concurrent.TimeUnit


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setSupportActionBar(toolbar)


        Handler().postDelayed({ HomeActivity.newIntent(this, Bundle.EMPTY) }, TimeUnit.MILLISECONDS.toMillis(30))
    }

}
