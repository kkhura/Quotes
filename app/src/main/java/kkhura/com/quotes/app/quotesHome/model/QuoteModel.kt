package kkhura.com.quotes.app.quotesHome.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class QuoteModel(@ColumnInfo(name = "quote") var quote: String, @PrimaryKey(autoGenerate = true) var _id: Int?, @ColumnInfo(name = "category_id") var categoryId: Int, @ColumnInfo(name = "liked") var liked: Int,@ColumnInfo(name = "utp") var utp: String)