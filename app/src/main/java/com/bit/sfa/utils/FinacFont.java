package com.bit.sfa.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by Sathiyaraja on 4/28/2017.
 */

public class FinacFont extends android.support.v7.widget.AppCompatTextView {
    public FinacFont(Context context) {
        super(context);

        applyCustomFont(context);
    }

    public FinacFont(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context);
    }

    public FinacFont(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("fonts/Cuprum-Regular.ttf", context);
        setTypeface(customFont);
    }
}
