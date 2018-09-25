package kkhura.com.quotes.app

import android.app.Application
import android.content.ContextWrapper
import android.util.Log
import kkhura.com.quotes.app.database.MyDatabase
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MyDatabase.getInstance(this);
    }
}