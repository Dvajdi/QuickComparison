package ru.forge.twice_a_day.quickcomparison;

import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * Created by twice on 16.03.16.
 */
public class ClearFieldListener implements View.OnTouchListener {
    EditText et;

    public ClearFieldListener(EditText et) {
        this.et = et;
    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(et.getId()==v.getId()){et.setText("");}
        return false;
    }
}
