package kkhura.com.quotes.app.quotesHome.Reprository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import kkhura.com.quotes.app.database.MyDatabase
import kkhura.com.quotes.app.quotesHome.dao.QuotesCategoryDao
import kkhura.com.quotes.app.quotesHome.model.QuotesCategoryModel

class QuotesCatResprository {
    private var quoteCategoryDao: QuotesCategoryDao
    private lateinit var allCategory: LiveData<List<QuotesCategoryModel>>
//    private lateinit var mDbWorkerThread: DbWorkerThread

    constructor(application: Application) {
        var myDatabase: MyDatabase = MyDatabase.getInstance(application)!!
        quoteCategoryDao = myDatabase.quotesCategoryDao()

        allCategory = quoteCategoryDao.getAll()

//        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
//        mDbWorkerThread.start()
    }

    public fun getAll(): LiveData<List<QuotesCategoryModel>> {
        /*var task = Runnable {
            val listQuotesCategoryModel: List<QuotesCategoryModel>? =
                    mDB?.quotesCategoryDao()?.getAll()

        }
        mDbWorkerThread.postTask(task)*/
//        FetchAllCategory(quoteCategoryDao).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        return  allCategory;

    }


//    private class FetchAllCategory(val quoteCategoryDao: QuotesCategoryDao) : AsyncTask<Void, Void, Void>() {
//
//        override fun doInBackground(vararg p0: Void?): Void? {
//            allCategory= quoteCategoryDao.getAll()
//        }
//
//    }
}