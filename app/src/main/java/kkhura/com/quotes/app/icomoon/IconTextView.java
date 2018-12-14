package kkhura.com.quotes.app.icomoon;

import android.content.Context;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;


/**
 * Created by xmb2nc on 10-04-2016.
 */
public class IconTextView extends AppCompatTextView {

    public IconTextView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        setTypeface(IcoMoonUtil.getTypeFace(getContext()));
    }

    public IconTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        setTypeface(IcoMoonUtil.getTypeFace(getContext()));
    }


    public IconTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        setTypeface(IcoMoonUtil.getTypeFace(getContext()));
    }

}

