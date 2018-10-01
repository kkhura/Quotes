package kkhura.com.quotes.app.quotesHome.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kkhura.com.quotes.app.quotesHome.Reprository.QuotesCatResprository
import kkhura.com.quotes.app.quotesHome.model.QuotesCategoryModel

public class QuoteCategoryViewModel : AndroidViewModel {
    private var quotesCatResprository: QuotesCatResprository
    private var quoteCategoryList: LiveData<List<QuotesCategoryModel>>

    constructor(application: Application) : super(application) {
        quotesCatResprository = QuotesCatResprository(application)
        quoteCategoryList = quotesCatResprository.getAll()
    }

    fun getQuoteCategoryList(): LiveData<List<QuotesCategoryModel>> {
        return quoteCategoryList;
    }

}