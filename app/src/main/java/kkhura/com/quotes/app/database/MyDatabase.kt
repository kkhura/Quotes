package kkhura.com.quotes.app.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration
import android.content.Context
import android.util.Log
import kkhura.com.quotes.app.quotesHome.dao.QuotesCategoryDao
import kkhura.com.quotes.app.quotesHome.model.QuoteModel
import kkhura.com.quotes.app.quotesHome.model.QuotesCategoryModel


@Database(entities = arrayOf(QuotesCategoryModel::class, QuoteModel::class), version = 2)
abstract class MyDatabase : RoomDatabase() {

    abstract fun quotesCategoryDao(): QuotesCategoryDao

    companion object {
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase? {
            if (INSTANCE == null) {
                synchronized(MyDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MyDatabase::class.java, "quotes.db").addMigrations(object : Migration(1, 2) {
                        override fun migrate(database: SupportSQLiteDatabase) {
                            Log.w("TAG", "migrate from version 1 to 2 ")
                        }
                    }).build()
                }

            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}