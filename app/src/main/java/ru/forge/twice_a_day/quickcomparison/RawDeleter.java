package ru.forge.twice_a_day.quickcomparison;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by twice on 26.04.16.
 */
public class RawDeleter implements View.OnTouchListener{
    RawFragment rf;
    View v,parent;
    float top, bottom,left,right;
    float x,y,oldX,oldY;
    int priznak;

    public RawDeleter(RawFragment rf) {
        this.rf = rf;

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
        if(top>=left&&y<bottom){
                float razn =x-oldX;
            if(razn>((right-left)*2/3)){

                rf.removeMySelf();

            }
        }
        v.requestFocus();
        return true;
    }
}
