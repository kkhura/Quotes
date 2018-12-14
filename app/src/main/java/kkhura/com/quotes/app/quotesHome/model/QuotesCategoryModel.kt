package kkhura.com.quotes.app.quotesHome.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class QuotesCategoryModel(@PrimaryKey(autoGenerate = true) var _id: Int?,
                               @ColumnInfo(name = "name") var name: String,@ColumnInfo(name = "status") var status: Int)