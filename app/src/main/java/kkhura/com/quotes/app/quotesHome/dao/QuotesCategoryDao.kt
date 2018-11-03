package kkhura.com.quotes.app.quotesHome.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import kkhura.com.quotes.app.quotesHome.model.QuoteModel
import kkhura.com.quotes.app.quotesHome.model.QuotesCategoryModel


@Dao
interface QuotesCategoryDao {

    @Insert
    fun insert(category: QuotesCategoryModel)

    @Query("SELECT * FROM category")
    fun getAllData(): List<QuotesCategoryModel>

    @Query("SELECT * FROM category")
    fun getQuoteCategoryList(): LiveData<List<QuotesCategoryModel>>

    @Query("SELECT * FROM quotes WHERE category_id = :id")
    fun getQuotes(id: Int): LiveData<List<QuoteModel>>
}