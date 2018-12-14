package kkhura.com.quotes.app.quotesHome.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
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