package ru.forge.twice_a_day.quickcomparison;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.CardView;
import java.util.ArrayList;

/**
 * Created by twice on 22.03.16.
 */
public class RawFragment extends Fragment implements ScrollViewListener{
    ArrayList fragments;
    EditText etPrice;
    EditText etQuantity;
    TextView tvResult;
    double res;
    MyScroll myScroll;
    Activity ctx;
    CardView cv;
    LinearLayout layout;
    boolean isNotWhenStart;
    int cardColor;

    public void setFragments(ArrayList fragments,boolean isNotWhenStart) {
        this.fragments = fragments;
        ctx=getActivity();
        this.isNotWhenStart=isNotWhenStart;
    }

    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;

    public void setSwipeListener(View v) {
        if (v == null)
            return;
        gestureDetector = new GestureDetector(ctx, new SwipeGestureDetector(this,fragments));
        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };
        v.setOnTouchListener(gestureListener);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);
        View rootView=inflater.inflate(R.layout.material_row_3, container, false);
        findViewsInFragment(rootView);
        if(isNotWhenStart){etPrice.requestFocus();}
        return rootView;
    }

    void findViewsInFragment(View rootView){
        cv= ((CardView) rootView);
        layout= ((LinearLayout) cv.findViewById(R.id.layout));
        etPrice=(EditText)rootView.findViewById(R.id.et_dop_price);
        etQuantity=(EditText)rootView.findViewById(R.id.et_dop_quantity);
        setSwipeListener(cv);
        setSwipeListener(layout);
        setSwipeListener(etPrice);
        setSwipeListener(etQuantity);

    }

    public double getRes() {
        return res;
    }

    public void setRes(double res) {
        this.res = res;
    }

    @Override
    public void onScrollChanged(MyScroll myScroll, int x, int y, int oldX, int oldY) {
        if(x==0){
            //removeMySelf();
        }
    }

    public void setCardColor(int color){
        if(color!=cardColor){cardColor=color;cv.setCardBackgroundColor(color);
        Log.d("paint","перерисовался");}
    }
}
