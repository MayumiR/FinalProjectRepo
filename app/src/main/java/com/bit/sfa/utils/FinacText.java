package com.bit.sfa.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by Sathiyaraja on 6/22/2018.
 */

public class FinacText extends android.support.v7.widget.AppCompatEditText {
    public FinacText(Context context) {
        super(context);

        applyCustomFont(context);
    }

    public FinacText(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context);
    }

    public FinacText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("fonts/Cuprum-Regular.ttf", context);
        setTypeface(customFont);
    }
}
