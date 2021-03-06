package kkhura.com.quotes.app.homescreen.adapter

import android.content.Context
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kkhura.com.quotes.app.R
import kkhura.com.quotes.app.quotesHome.model.QuotesCategoryModel
import kotlinx.android.synthetic.main.row_category.view.*
import java.lang.Error

class QuotesCategoryAdapter(val items: ArrayList<QuotesCategoryModel>, val context: Context, var listner: OnItemClicked, var isGrid: Boolean) : androidx.recyclerview.widget.RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(if (isGrid) R.layout.grid_row else R.layout.row_category, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtCategory.text = items.get(position).name
        val mDrawableName = "category" + items.get(position)._id
        val resID = context.resources.getIdentifier(mDrawableName, "drawable", context.packageName)

        holder.ivProfile.setImageResource(resID)
        holder.cardView.setOnClickListener({ listner.itemClicked(position) })

        val mylambda :(String)->Unit  = {s:String->print(s)}
        var v:String = "TutorialsPoint.com"
        mylambda(v)

        var error:Error = Error()
        error.localizedMessage

    }

    override fun getItemCount() = items.size
}

class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    val txtCategory = view.txtCategory
    var ivProfile = view.iv_profile
    var cardView = view.findViewById<androidx.cardview.widget.CardView>(R.id.card_view)

}

interface OnItemClicked {
    fun itemClicked(postion: Int)
}