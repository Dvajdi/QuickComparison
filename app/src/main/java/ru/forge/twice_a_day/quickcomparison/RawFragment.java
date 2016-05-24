package ru.forge.twice_a_day.quickcomparison;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ScrollView;

import java.util.ArrayList;

public class RawFragment extends Fragment implements Animation.AnimationListener{
    private ArrayList fragments;
    private EditText etPrice,etQuantity;
    private double res,resPac;
    private MyCardView cv;
    private boolean isNotWhenStart;
    private int cardColor;
    private View rootView;
    MainActivity ctx;




    public void setFragments(ArrayList fragments,boolean isNotWhenStart) {
        this.fragments = fragments;
        this.isNotWhenStart=isNotWhenStart;
    }

     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);
        rootView=inflater.inflate(R.layout.material_row_3, container, false);
        findViewsInFragment(rootView);
        cv.setRf(this);
        cv.setScrollView((ScrollView) getActivity().findViewById(R.id.scrollView));
        if(isNotWhenStart){etPrice.requestFocus();}
        if(cardColor!=0){cv.setCardBackgroundColor(cardColor);}

        ctx = (MainActivity)getActivity();
        return rootView;
    }

    private void findViewsInFragment(View rootView){
        cv=(MyCardView) rootView;
        etPrice=(EditText)rootView.findViewById(R.id.et_dop_price);
        etQuantity=(EditText)rootView.findViewById(R.id.et_dop_quantity);

        StaticNeedSupplement.ScaleLongStringsInTextView(etPrice);
        StaticNeedSupplement.ScaleLongStringsInTextView(etQuantity);

        etPrice.addTextChangedListener(ctx.textWatcher);
        etQuantity.addTextChangedListener(ctx.textWatcher);

    }

    public double getRes() {
        return res;
    }

    public void setRes(double res) {
        this.res = res;
    }

    public void setResPac(double resPac) {
        this.resPac = resPac;
    }

    public double getResPac() {
        return resPac;
    }

    public void setCardColor(int color){
        if(color!=cardColor){cardColor=color;cv.setCardBackgroundColor(color);
        }
    }

    public void removeMySelf(){
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.anim);
        anim.setAnimationListener(this);
        rootView.startAnimation(anim);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }
    @Override
    public void onAnimationEnd(Animation animation) {
        fragments.remove(this);
        FragmentTransaction ft =ctx.getSupportFragmentManager().beginTransaction();
        ft.remove(this).commit();
        ctx.startThread();
    }
    @Override
    public void onAnimationRepeat(Animation animation) {
    }
}
