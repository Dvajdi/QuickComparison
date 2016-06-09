package ru.forge.twice_a_day.quickcomparison;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;


public class MyCardView extends CardView {
    private RawFragment rf;
    private ScrollView scrollView;
    private float x;
    private float moveX;
    private float y;
    private float moveY;
    float width,hight;

    public void setRf(RawFragment rf) {
        this.rf = rf;
        setWidthAndHight();
    }
    private void setWidthAndHight(){
        width = rf.getActivity().getWindowManager().getDefaultDisplay().getWidth();
        hight = rf.getActivity().getWindowManager().getDefaultDisplay().getHeight();
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
        if(ev.getAction()==MotionEvent.ACTION_MOVE){moveX=ev.getX()-x;moveY=Math.abs(ev.getY()-y);}
        Log.d("watcher","x  = "+moveX+" ; "+" y = "+Math.abs(moveY));
        if(moveX>(width/3)){rf.removeMySelf();return super.dispatchTouchEvent(ev);}
        if(moveX<-(width/3)){rf.removeMySelfLeft();return super.dispatchTouchEvent(ev);}
        if(moveX>(width/10)||moveY<(hight/10)){
            Log.d("watcher","запретил INTERCEPT");
            scrollView.requestDisallowInterceptTouchEvent(true);
            }else{scrollView.requestDisallowInterceptTouchEvent(false);}

        return super.dispatchTouchEvent(ev);
    }


}
