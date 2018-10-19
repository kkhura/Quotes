package kkhura.com.quotes.app.quotesHome.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "category")
data class QuotesCategoryModel(@PrimaryKey(autoGenerate = true) var _id: Int?,
                               @ColumnInfo(name = "name") var name: String,@ColumnInfo(name = "status") var status: Int)