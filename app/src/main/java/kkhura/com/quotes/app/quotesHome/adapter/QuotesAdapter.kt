package kkhura.com.quotes.app.quotesHome.adapter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Environment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kkhura.com.quotes.app.R
import kkhura.com.quotes.app.quotesHome.model.QuoteModel
import kkhura.com.quotes.app.utility.Utils
import kotlinx.android.synthetic.main.row_quote.view.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


class QuotesAdapter(val items: ArrayList<QuoteModel>?, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_quote, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val get = items!!.get(position)
        holder.tvQuote.text = get.quote
        holder.tvCopy.setOnClickListener({
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText(context.getText(R.string.app_name), holder.tvQuote.text.trim())
            clipboard.primaryClip = clip
            Toast.makeText(context, context.getText(R.string.text_copied), Toast.LENGTH_SHORT).show()
        })
        holder.tvSave.setOnClickListener({
            storeImage(Utils.getBitmapFromView(holder.cardQuote))
        })
        holder.tvShare.setOnClickListener({
            val shareBody = holder.tvQuote.text
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareBody)
                type = "text/plain"
            }
            context.startActivity(Intent.createChooser(sendIntent, context.resources.getText(R.string.share_using)))
        })
    }

    override fun getItemCount() = items!!.size

    private fun storeImage(image: Bitmap) {
        val timeStamp = System.currentTimeMillis()
        val filename = "image_$timeStamp.jpg"
        try {
            val path = Environment.getExternalStorageDirectory().toString()
            val pictureFile = File(path + File.separator + context.getString(R.string.app_name) + File.separator, filename)
            if (!pictureFile.exists()) {
                pictureFile.createNewFile()
            }

            val fos = FileOutputStream(pictureFile)
            image.compress(Bitmap.CompressFormat.PNG, 90, fos)
            fos.close()
            Toast.makeText(context, context.getText(R.string.saved_as_image), Toast.LENGTH_SHORT).show()
        } catch (e: FileNotFoundException) {
        } catch (e: IOException) {
        }

    }

}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvQuote = view.tvQuote
    var cardQuote = view.cardQuote
    var tvSave = view.tvSave
    var tvCopy = view.tvCopy
    var tvEdit = view.tvEdit
    var tvShare = view.tvShare

}

interface OnItemClicked {
    fun itemClicked(postion: Int)
}