package kkhura.com.quotes.app.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration
import kkhura.com.quotes.app.quotesHome.dao.QuotesCategoryDao
import kkhura.com.quotes.app.quotesHome.model.QuoteModel
import kkhura.com.quotes.app.quotesHome.model.QuotesCategoryModel

@Database(entities = [(QuotesCategoryModel::class), (QuoteModel::class)], version = 2)
abstract class MyDatabase : RoomDatabase() {

    abstract fun quotesCategoryDao(): QuotesCategoryDao

    companion object {

        @JvmField
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
            }
        }

        /* fun destroyInstance() {
             INSTANCE = null
         }*/
    }


}