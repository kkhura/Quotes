package kkhura.com.quotes.app.quotesHome.adapter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Environment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kkhura.com.quotes.app.R.*
import kkhura.com.quotes.app.quotesHome.model.QuoteModel
import kkhura.com.quotes.app.utility.Utils
import kotlinx.android.synthetic.main.row_quote.view.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


class QuotesAdapter(val items: ArrayList<QuoteModel>?, val context: Context, var listner: OnItemClicked) : androidx.recyclerview.widget.RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(layout.row_quote, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val get = items!!.get(position)
        holder.tvQuote.text = get.quote
        holder.tvCopy.setOnClickListener({
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText(context.getText(string.app_name), holder.tvQuote.text.trim())
            clipboard.primaryClip = clip
            Toast.makeText(context, context.getText(string.text_copied), Toast.LENGTH_SHORT).show()
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
            context.startActivity(Intent.createChooser(sendIntent, context.resources.getText(string.share_using)))
        })
        holder.tvEdit.setOnClickListener({ listner.itemClicked(position, holder.tvQuote.text.toString()) });
    }

    override fun getItemCount() = items!!.size

    private fun storeImage(image: Bitmap) {
        val timeStamp = System.currentTimeMillis()
        val filename = "image_$timeStamp.jpg"
        try {
            val path = Environment.getExternalStorageDirectory().toString()
            val dirFile = File(path + File.separator + context.getString(string.app_name))
            if (!dirFile.exists()) {
                dirFile.mkdirs()
            }
            val pictureFile = File(dirFile, filename)

            val fos = FileOutputStream(pictureFile)
            image.compress(Bitmap.CompressFormat.PNG, 90, fos)
            fos.close()
            Toast.makeText(context, context.getText(string.saved_as_image), Toast.LENGTH_SHORT).show()
        } catch (e: FileNotFoundException) {
        } catch (e: IOException) {
        }
    }
}

class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    val tvQuote = view.tvQuote
    var cardQuote = view.cardQuote
    var tvSave = view.tvSave
    var tvCopy = view.tvCopy
    var tvEdit = view.tvEdit
    var tvShare = view.tvShare
}

interface OnItemClicked {
    fun itemClicked(postion: Int, text: String)
}