package com.bit.sfa.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by Sathiyaraja on 6/22/2018.
 */

public class FinacBtn extends android.support.v7.widget.AppCompatButton {
    public FinacBtn(Context context) {
        super(context);

        applyCustomFont(context);
    }

    public FinacBtn(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context);
    }

    public FinacBtn(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("fonts/Cuprum-Regular.ttf", context);
        setTypeface(customFont);
    }
}
