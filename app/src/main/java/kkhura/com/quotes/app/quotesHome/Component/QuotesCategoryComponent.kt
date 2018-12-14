package kkhura.com.quotes.app.quotesHome.Component

import dagger.Component
import kkhura.com.quotes.app.homescreen.adapter.QuotesCategoryAdapter

@Component
interface QuotesCategoryComponent {

     fun getAdapter(): QuotesCategoryAdapter

}