package ru.forge.twice_a_day.quickcomparison;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by twice on 04.05.16.
 */
public class MyOnTouchListener implements View.OnTouchListener {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {
            Log.d("touch","down");return true;}

        if(event.getAction()==MotionEvent.ACTION_CANCEL)
        {Log.d("touch","cancel");return true;}

        if(event.getAction()==MotionEvent.ACTION_UP)
        {Log.d("touch","pointerUP");return true;}



        return false;
    }
}
