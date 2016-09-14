package ru.forge.twice_a_day.quickcomparison.views;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class FragmentCardView extends CardView {
  private RowFragment rf;
  private ScrollView scrollView;
  private float x;
  private float moveX;
  private float y;
  private float moveY;
  float width,hight;

  public FragmentCardView(Context context) {
    super(context);
  }
  public FragmentCardView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }
  public FragmentCardView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public void setRf(RowFragment rf) {
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

  @Override
  public boolean dispatchTouchEvent(MotionEvent me) {
        scrollView.requestDisallowInterceptTouchEvent(true);
        if(me.getAction()==MotionEvent.ACTION_DOWN){x=me.getX();y=me.getY();}
        if(me.getAction()==MotionEvent.ACTION_MOVE){moveX=me.getX()-x;moveY=Math.abs(me.getY()-y);}
        if(moveX>(width/3)){rf.removeMySelf();return super.dispatchTouchEvent(me);}
        if(moveX<-(width/3)){rf.removeMySelfLeft();return super.dispatchTouchEvent(me);}
        if(moveX>(width/10)||moveY<(hight/10)){
          scrollView.requestDisallowInterceptTouchEvent(true);
        }else{
          scrollView.requestDisallowInterceptTouchEvent(false);
        }
        return super.dispatchTouchEvent(me);
    }

}
