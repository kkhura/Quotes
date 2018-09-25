package kkhura.com.quotes.app.customview

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView
import kkhura.com.quotes.app.R

class CustomFontTextView : TextView {


    constructor(context: Context) : super(context) {
        applyCustomFont(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        applyCustomFont(context, attrs)
    }

    override fun isInEditMode(): Boolean {
        return true
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        applyCustomFont(context, attrs)
    }

    private fun applyCustomFont(context: Context, attrs: AttributeSet?) {
        val attributeArray = context.obtainStyledAttributes(attrs, R.styleable.customFontTextView)

        val fontName = attributeArray.getString(R.styleable.customFontTextView_fontName)
        val customFont = selectTypeface(context, fontName, fontName)
        typeface = customFont
        attributeArray.recycle()
    }

    fun selectTypeface(context: Context, fontName: String?, textStyle: String?): Typeface? {
        return if (textStyle == null) {
            FontCache.getTypeface("sans/OpenSans-Regular.ttf", context)
        } else FontCache.getTypeface("sans/OpenSans-Semibold.ttf", context)
        if(textStyle.equals(context.getString(R.string.font_bold))){
			FontCache.getTypeface("sans/OpenSans-Bold.ttf", context);
		} else if(textStyle.equals(context.getString(R.string.font_semibold))){
			return FontCache.getTypeface("sans/OpenSans-Semibold.ttf", context);
		} else if(textStyle.equals(context.getString(R.string.font_light))){
			return FontCache.getTypeface("sans/OpenSans-Light.ttf", context);
		} else if(textStyle.equals(context.getString(R.string.font_regular))){
			return FontCache.getTypeface("sans/OpenSans-Regular.ttf", context);
		}else if(textStyle.equals(context.getString(R.string.font_italic))){
			return FontCache.getTypeface("sans/OpenSans-Italic.ttf", context);
		}
    }

    companion object {

        val ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android"
    }


}
