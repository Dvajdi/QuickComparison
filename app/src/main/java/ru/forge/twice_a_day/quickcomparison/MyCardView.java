package ru.forge.twice_a_day.quickcomparison;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by twice on 19.05.16.
 */
public class MyCardView extends CardView {
    RawFragment rf;
    ScrollView scrollView;
    float x,moveX;
    float y,moveY;

    public void setRf(RawFragment rf) {
        this.rf = rf;
    }

    public void setScrollView(ScrollView scrollView) {
        this.scrollView = scrollView;
    }

    public MyCardView(Context context) {
        super(context);
    }

    public MyCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        scrollView.requestDisallowInterceptTouchEvent(true);
        if(ev.getAction()==MotionEvent.ACTION_DOWN){x=ev.getX();y=ev.getY();}
        if(ev.getAction()==MotionEvent.ACTION_MOVE){moveX=Math.abs(ev.getX()-x);moveY=Math.abs(ev.getY()-y);}
        Log.d("watcher","x  = "+moveX+" ; "+" y = "+Math.abs(moveY));
        if(moveX>300){rf.removeMySelf();return super.dispatchTouchEvent(ev);}
        if(moveX>30||moveY<70){/*scrollView.requestDisallowInterceptTouchEvent(true);*/
            Log.d("watcher","запретил INTERCEPT");
            scrollView.requestDisallowInterceptTouchEvent(true);
            }else{scrollView.requestDisallowInterceptTouchEvent(false);}

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return super.onInterceptTouchEvent(ev);
    }
}
