package kkhura.com.quotes.app

import android.app.Application
import com.google.android.gms.ads.MobileAds


class MyApplication : Application() {

    override fun onCreate() {
//        MobileAds.initialize(this, getString(R.string.ad_mob_id));
        super.onCreate()
    }
}