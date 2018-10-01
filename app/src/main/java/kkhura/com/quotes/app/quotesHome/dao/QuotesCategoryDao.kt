package kkhura.com.quotes.app.quotesHome.dao

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import kkhura.com.quotes.app.quotesHome.model.QuoteModel
import kkhura.com.quotes.app.quotesHome.model.QuotesCategoryModel


@Dao
interface QuotesCategoryDao {

    @get:Query("SELECT * from category")
    val listCategory: List<QuotesCategoryModel>

    @get:Query("SELECT * from quotes")
    val listQuote: List<QuoteModel>

    @Insert
    fun insert(category: QuotesCategoryModel)

    @Query("SELECT * FROM category")
    abstract fun getAll(): LiveData<List<QuotesCategoryModel>>

    @Query("SELECT * FROM quotes WHERE category_id = :id")
    abstract fun findQuoteId(id: Int): List<QuoteModel>
}