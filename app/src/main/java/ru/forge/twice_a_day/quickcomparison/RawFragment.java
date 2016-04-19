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

    public void setFragments(ArrayList fragments) {
        this.fragments = fragments;
        ctx=getActivity();
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
        View rootView=inflater.inflate(R.layout.material_row_2, container, false);
        //View rootView=inflater.inflate(R.layout.row, container, false);
        findViewsInFragment(rootView);
        return rootView;
    }

    void findViewsInFragment(View rootView){
       // LinearLayout linLay= ((LinearLayout)rootView);
        cv= ((CardView) rootView);

        etPrice=(EditText)rootView.findViewById(R.id.et_dop_price);
        etPrice=(EditText)rootView.findViewById(R.id.et_dop_price);
        etQuantity=(EditText)rootView.findViewById(R.id.et_dop_quantity);
        setSwipeListener(cv);
        //setSwipeListener(linLay);
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

    void removeMySelf(){
            fragments.remove(this);
            this.getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}
