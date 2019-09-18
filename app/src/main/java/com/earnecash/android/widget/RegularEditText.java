package com.earnecash.android.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;


public class RegularEditText extends AppCompatEditText {

    static Typeface normal = null;
    static Typeface bold = null;

    public RegularEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public RegularEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RegularEditText(Context context) {
        super(context);
        init();
    }

    public void setTypeface(Typeface tf, int style) {

        if (style == Typeface.NORMAL) {
            if (bold == null)
                bold = Typeface.createFromAsset(getContext().getAssets(), "font/regular.otf");
            super.setTypeface(bold, Typeface.NORMAL);
        }
    }

    public void init()
    {
        if(normal == null)
            normal = Typeface.createFromAsset(getContext().getAssets(),"font/regular.otf");
        setTypeface(normal);
    }
}
