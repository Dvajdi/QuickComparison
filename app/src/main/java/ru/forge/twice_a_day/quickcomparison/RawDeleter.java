package ru.forge.twice_a_day.quickcomparison;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by twice on 26.04.16.
 */
public class RawDeleter implements View.OnTouchListener{
    RawFragment rf;
    View v,parent;
    float top, bottom,left,right;
    float x,y,oldX,oldY;
    int priznak;
    InputMethodManager imm;
    Activity act;

    public RawDeleter(RawFragment rf) {
        this.rf = rf;
        act=rf.getActivity();

    }
    void setCoordinates(){
        v =rf.getView();
        if(v!=null){
        if(v.getId()==R.id.doprow){priznak=1;}else{parent=(View)v.getParent().getParent();v=parent;}
        top=(v!=null)?v.getTop():0;
        bottom =(v!=null)?v.getBottom():0;
        left=(v!=null)?v.getLeft():0;
        right=(v!=null)?v.getRight():0;}

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        setCoordinates();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                oldX=event.getX();
                oldY=event.getY();

                if(v.getClass().equals(android.support.v7.widget.AppCompatEditText.class)){
                    InputMethodManager imm = (InputMethodManager) act.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,1);
                    //imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,0);

        }

                Log.d("zxc","oldX= "+oldX);
                Log.d("zxc","oldY= "+oldY);
                break;
            case MotionEvent.ACTION_CANCEL:
                x=event.getX();
                y=event.getY();
                Log.d("zxc","x= "+x);
                Log.d("zxc","y= "+y);

                break;

            case MotionEvent.ACTION_UP:
                x=event.getX();
                y=event.getY();
                Log.d("zxc","x= "+x);
                Log.d("zxc","y= "+y);
                break;
        }

                float razn =x-oldX;
            if(razn>((right-left)*0.5)){

                rf.removeMySelf();
                InputMethodManager imm = (InputMethodManager) rf.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(),0);

            }

        v.requestFocus();
        return true;
    }


}
