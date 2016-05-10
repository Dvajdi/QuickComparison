package ru.forge.twice_a_day.quickcomparison;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * Created by twice on 10.05.16.
 */
public class MyHorizontalScrollView extends HorizontalScrollView{
    RawFragment f;
    float maxLength;

    float oldX,x,rez;




    public MyHorizontalScrollView(Context context) {
        super(context);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setFragment(RawFragment f){
        this.f=f;
    }

    public void setMaxLength(float maxLength){
        this.maxLength=maxLength;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(ev.getAction()==MotionEvent.ACTION_DOWN){oldX=ev.getX();}
        if(ev.getAction()==MotionEvent.ACTION_MOVE){
            rez=Math.abs(oldX-ev.getX());

            if(rez>400){
                f.removeMySelf();
            }
        }

        return super.onTouchEvent(ev);
    }
}
