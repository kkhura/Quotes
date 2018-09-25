package kkhura.com.quotes.app.quotesHome.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "quotes")
data class QuoteModel(@ColumnInfo(name = "quote") var quote: String, @PrimaryKey(autoGenerate = true) var _id: Int?, @ColumnInfo(name = "category_id") var categoryId: Int, @ColumnInfo(name = "liked") var liked: Int,@ColumnInfo(name = "utp") var utp: String) {

}