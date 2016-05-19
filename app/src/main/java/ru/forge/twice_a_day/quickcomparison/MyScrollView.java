package ru.forge.twice_a_day.quickcomparison;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by twice on 19.05.16.
 */
public class MyScrollView extends ScrollView {
    float y,moveY;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("watcher","Scroll_INTERCEPT  = "+ev.getX());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

       /* requestDisallowInterceptTouchEvent(true);


        if(ev.getAction()==MotionEvent.ACTION_DOWN){y=ev.getY();}
        if(ev.getAction()==MotionEvent.ACTION_MOVE){moveY=Math.abs(ev.getY()-y);}
        if(moveY>100){requestDisallowInterceptTouchEvent(false);return true;}
        Log.d("watcher","Scroll_Dispatch  = "+ev.getX());*/

        return super.dispatchTouchEvent(ev);
    }
}
