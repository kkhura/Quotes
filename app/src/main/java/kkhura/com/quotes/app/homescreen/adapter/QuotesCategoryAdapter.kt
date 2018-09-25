package kkhura.com.quotes.app.homescreen.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kkhura.com.quotes.app.R
import kkhura.com.quotes.app.customview.CustomFontTextView
import kkhura.com.quotes.app.quotesHome.model.QuotesCategoryModel
import kotlinx.android.synthetic.main.row_category.view.*

class QuotesCategoryAdapter(val items: ArrayList<QuotesCategoryModel>, val context: Context, var listner: OnItemClicked) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_category, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtCategory.setText(items.get(position).name)
        holder.txtCategory.setOnClickListener(View.OnClickListener { listner.itemClicked(position) })
    }

    override fun getItemCount() = items.size
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val txtCategory = view.txtCategory

}

interface OnItemClicked {
    fun itemClicked(postion: Int)
}