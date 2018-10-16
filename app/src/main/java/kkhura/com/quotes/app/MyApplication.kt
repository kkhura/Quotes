package kkhura.com.quotes.app

import android.app.Application
import android.content.ContextWrapper
import android.util.Log
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

//        MyDatabase.getInstance(applicationContext)

        val sharedPreferences = getSharedPreferences("FILE", 0)
//        if (sharedPreferences.getBoolean("DB_COPIED", false)) {
            copyDataBase()
//            sharedPreferences.edit().putBoolean("DB_COPIED", true);
//        }

//        MyDatabase.getInstanceMigration(applicationContext)
    }


    private fun copyDataBase() {
        val cw = ContextWrapper(applicationContext)
        var DB_PATH: String = "/data/data/kkhura.com.quotes.app" + "/databases/"
        val buffer = ByteArray(1024)
        var myOutput: OutputStream? = null
        var length: Int
        // Open your local db as the input stream
        var myInput: InputStream? = null
        try {
            val DB_NAME = "quotes.db"
            myInput = applicationContext.getAssets().open(DB_NAME)
            // transfer bytes from the inputfile to the
            // outputfile
            myOutput = FileOutputStream(DB_PATH + DB_NAME)
            length = myInput!!.read(buffer)
            while (length > 0) {
                myOutput!!.write(buffer, 0, length)
                length = myInput!!.read(buffer)
            }
            myOutput!!.close()
            myOutput!!.flush()
            myInput!!.close()
            Log.i("Database",
                    "New database has been copied to device!")


        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}