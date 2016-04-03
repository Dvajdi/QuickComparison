package ru.forge.twice_a_day.quickcomparison;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.HorizontalScrollView;

/**
 * Created by Павел on 03.04.2016.
 */
public class MyScroll extends HorizontalScrollView {
    EditText et;
    public MyScroll(Context context) {
        super(context);
    }

    public MyScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScroll(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        et=(EditText)findViewById(R.id.et_dop_price);
        scrollTo(et.getLeft(),0);
    }


}
