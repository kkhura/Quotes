package kkhura.com.quotes.app.quotesHome.Reprository

import android.app.Application
import android.arch.lifecycle.LiveData
import kkhura.com.quotes.app.database.DatabaseCopier
import kkhura.com.quotes.app.database.MyDatabase
import kkhura.com.quotes.app.quotesHome.dao.QuotesCategoryDao
import kkhura.com.quotes.app.quotesHome.model.QuotesCategoryModel

class QuotesCatResprository {
    private var quoteCategoryDao: QuotesCategoryDao
    private var allCategory: LiveData<List<QuotesCategoryModel>>

    constructor(application: Application) {
        var myDatabase: MyDatabase = DatabaseCopier.getInstance(application)!!.roomDatabase
        quoteCategoryDao = myDatabase.quotesCategoryDao()
        allCategory = quoteCategoryDao.getQuoteCategoryList()
    }

    fun getQuoteCategoryList(): LiveData<List<QuotesCategoryModel>> {
        return allCategory

    }
}