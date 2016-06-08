package ru.forge.twice_a_day.quickcomparison;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import java.util.ArrayList;

import ru.forge.twice_a_day.quickcomparison.about_units.UnitsType;
import ru.forge.twice_a_day.quickcomparison.helpers.StaticNeedSupplement;

public class RawFragment extends Fragment implements Animation.AnimationListener,View.OnClickListener{
    private ArrayList fragments;
    private EditText etPrice,etQuantity;
    private double res;
    private double resPac;

    public double getResWithoutUnit() {
        return resWithoutUnit;
    }

    public void setResWithoutUnit(double resWithoutUnit) {

        this.resWithoutUnit = resWithoutUnit;
    }

    private double resWithoutUnit;
    private MyCardView cv;
    private boolean isNotWhenStart;
    private int cardColor;
    private View rootView;
    MainActivity ctx;

    private Button btnUnit;
    boolean isFirstChange;

    public static UnitsType rawUnitType;
    String rawUnit;
    private double unitValue=1;

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
        btnUnit = (Button)rootView.findViewById(R.id.button_dop_unit);

        StaticNeedSupplement.ScaleLongStringsInTextView(etPrice);
        StaticNeedSupplement.ScaleLongStringsInTextView(etQuantity);

        btnUnit.setOnClickListener(this);
        btnUnit.setText(MainActivity.btnGoalUnit.getText());
        unitValue=MainActivity.goalUnitValue;
        btnUnit.addTextChangedListener(ctx.textWatcher);
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

    public String getRawUnit() {
        return rawUnit;
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

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(ctx,ListUnitsActivity.class);
       startActivityForResult(intent,fragments.indexOf(this));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String s = "";
        if(resultCode==ctx.RESULT_OK){
        if(requestCode==fragments.indexOf(this)){
            s= data.getStringExtra("name");
            btnUnit.setText(s);
            rawUnit = s;
            unitValue = data.getDoubleExtra("value",1);
            changeUnit(s);
           // etQuantity.setText(String.valueOf(new Converter(MainActivity.goalUnitValue,unitValue,StaticNeedSupplement.getDoubleFromET(etQuantity)).getConvertValue()));

        }
        ctx.startThread();}
    }

    private void changeUnit(String s) {
        if(MainActivity.isChangeAll){
            MainActivity.changeUnitsInFragments(s);
            MainActivity.btnGoalUnit.setText(s);
            MainActivity.goalUnitName =s;
            MainActivity.goalUnitValue = unitValue;
        }

    }



    public double getUnitValue() {
        return unitValue;
    }

    public static void setRawUnitType(UnitsType rawUnitType) {
        RawFragment.rawUnitType = rawUnitType;
    }

    public void setUnitValue(double unitValue) {
        this.unitValue = unitValue;
    }
    public void setRawUnit(String rawUnit) {
        this.rawUnit = rawUnit;
       // btnUnit.setText(rawUnit);
    }

    public Button getBtnUnit(){
        return btnUnit;
    }

    static class FragmentChangeUnitHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {

        }
    }
}
