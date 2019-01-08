package kkhura.com.quotes.app.quotesHome.fragment


import android.Manifest
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kkhura.com.quotes.app.R
import kkhura.com.quotes.app.customview.CustomFontTextView
import kkhura.com.quotes.app.utility.BaseActivity
import kkhura.com.quotes.app.utility.BaseFragment
import kkhura.com.quotes.app.utility.Utils
import me.priyesh.chroma.ChromaDialog
import me.priyesh.chroma.ColorMode
import me.priyesh.chroma.ColorSelectListener
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


private const val QUOTE: String = "QUOTE"
private const val CATEGORY_TITLE: String = "CATEGORY_TITLE"
private const val PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1001

/**
 * A simple [Fragment] subclass.
 * Use the [EditQuoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class EditQuoteFragment : BaseFragment(), ColorSelectListener {

    private var quote: String = ""
    private var quoteTitle = ""
    private lateinit var tvQuote: CustomFontTextView
    private lateinit var cardQuote: androidx.cardview.widget.CardView
    private var textColorChange: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            quote = it.getString(QUOTE)
            quoteTitle = it.getString(CATEGORY_TITLE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_edit_quote, container, false)
    }

    override fun onResume() {
        (activity as BaseActivity).setToolBar(quoteTitle, true)
        super.onResume()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tvQuote = view!!.findViewById<CustomFontTextView>(R.id.tvQuote)
        cardQuote = view!!.findViewById(R.id.cardQuote)
        tvQuote.setText(quote)
        view!!.findViewById<CustomFontTextView>(R.id.tvTextSize).setOnClickListener({
            showPopUpMenuTypeSize(view!!.findViewById<CustomFontTextView>(R.id.tvTextSize))
        })
        view!!.findViewById<CustomFontTextView>(R.id.tvTextColor).setOnClickListener({
            textColorChange = true
            showColorPicker()
        })
        view!!.findViewById<CustomFontTextView>(R.id.tvTypeFace).setOnClickListener({
            showPopUpMenuTypeFace(view!!.findViewById<CustomFontTextView>(R.id.tvTypeFace))
        })

        view!!.findViewById<CustomFontTextView>(R.id.tvBackground).setOnClickListener({
            textColorChange = false
            showColorPicker()
        })
        view!!.findViewById<CustomFontTextView>(R.id.tvShare).setOnClickListener({
            val shareBody = tvQuote.text
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareBody)
                type = "text/plain"
            }
            startActivity(Intent.createChooser(sendIntent, resources.getText(R.string.share_using)))
        })

        view!!.findViewById<CustomFontTextView>(R.id.tvSave).setOnClickListener({
            /*(activity as BaseActivity).*/
            if (checkPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                storeImage(Utils.getBitmapFromView(cardQuote))
            } else {
                onPermissionNotGranted()
            }
        })
    }

    override fun onPermissionGranted(permission: String) {
        storeImage(Utils.getBitmapFromView(cardQuote))
    }

    override fun onPermissionNotGranted() {
        Toast.makeText(context, getString(R.string.permission_required), Toast.LENGTH_SHORT).show()
    }


    private fun storeImage(image: Bitmap) {
        val timeStamp = System.currentTimeMillis()
        val filename = "image_$timeStamp.jpg"
        try {
            val path = Environment.getExternalStorageDirectory().toString()
            val dirFile = File(path + File.separator + getString(R.string.app_name))
            if (!dirFile.exists()) {
                dirFile.mkdirs()
            }
            val pictureFile = File(dirFile, filename)

            val fos = FileOutputStream(pictureFile)
            image.compress(Bitmap.CompressFormat.PNG, 90, fos)
            fos.close()
            Toast.makeText(context, context!!.getText(R.string.saved_as_image), Toast.LENGTH_SHORT).show()
        } catch (e: FileNotFoundException) {
        } catch (e: IOException) {
        }
    }

    private fun showPopUpMenuTypeFace(view: View) {
        var popup: PopupMenu? = null;
        popup = PopupMenu(context, view)
        val menu = popup.menu
        menu.add("OpenSans-Regular")
        menu.add("OpenSans-Bold")
        menu.add("OpenSans-Italic")
        menu.add("OpenSans-Light")
        menu.add("OpenSans-Semibold")
        menu.add("AlexBrush-Regular")
        menu.add("Amatic-Bold")
        menu.add("AmaticSC-Regular")
        menu.add("Lato-Italic")
        menu.add("Lato-Light")
        menu.add("Roboto-Italic")
        menu.add("Roboto-Medium")

        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

            changeFont(item!!.title.toString())
            true
        })

        popup.show()
    }

    private fun showPopUpMenuTypeSize(view: View) {
        var popup: PopupMenu? = null;
        popup = PopupMenu(context, view)
        popup.menuInflater.inflate(R.menu.menu_text_size, popup.menu)

        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

            if (item != null) {
                if (item.title.equals("12")) {
                    tvQuote.setTextSize(12F)
                } else if (item.title.equals("14")) {
                    tvQuote.setTextSize(14F)
                } else if (item.title.equals("16")) {
                    tvQuote.setTextSize(16F)
                } else if (item.title.equals("18")) {
                    tvQuote.setTextSize(18F)
                } else if (item.title.equals("20")) {
                    tvQuote.setTextSize(20F)
                } else if (item.title.equals("22")) {
                    tvQuote.setTextSize(22F)
                } else if (item.title.equals("24")) {
                    tvQuote.setTextSize(24F)
                } else if (item.title.equals("26")) {
                    tvQuote.setTextSize(26F)
                }
            }

            true
        })

        popup.show()
    }


    private fun changeFont(title: String) {
        var am: AssetManager = context!!.getApplicationContext().getAssets()
        tvQuote.setTypeface(Typeface.createFromAsset(am, "sans" + File.separator + title + ".ttf"))

    }


    private fun showColorPicker() {
        ChromaDialog.Builder()
                .initialColor(Color.BLACK)
                .colorMode(ColorMode.RGB)
                .onColorSelected(this)
                .create().show(fragmentManager, getString(R.string.color_choose))
    }

    override fun onColorSelected(color: Int) {
        if (textColorChange) {
            tvQuote.setTextColor(color)
        } else {
            cardQuote.setBackgroundColor(color)
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param quoteTitle
         * @param quote
         * @return A new instance of fragment EditQuoteFragment.
         */
        @JvmStatic
        fun newInstance(quoteTitle: String, quote: String) =
                EditQuoteFragment().apply {
                    arguments = Bundle().apply {
                        putString(CATEGORY_TITLE, quoteTitle)
                        putString(QUOTE, quote)
                    }
                }
    }
}