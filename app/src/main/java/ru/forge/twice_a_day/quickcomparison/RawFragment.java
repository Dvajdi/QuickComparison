package ru.forge.twice_a_day.quickcomparison;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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
public class RawFragment extends Fragment implements TextWatcher,View.OnTouchListener{
    ArrayList fragments;
    EditText etPrice;
    EditText etQuantity;
    TextView tvResult,tv_dop_economy;
    double res;

    Activity ctx;
    CardView cv;
    TextInputLayout etLay1,etLay2;
    LinearLayout layout,lay2;
    boolean isNotWhenStart;
    int cardColor;
    Button btn;
    View rootView;

    public void setFragments(ArrayList fragments,boolean isNotWhenStart) {
        this.fragments = fragments;
        ctx=getActivity();
        this.isNotWhenStart=isNotWhenStart;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);
        rootView=inflater.inflate(R.layout.material_row_3, container, false);
        findViewsInFragment(rootView);
        if(isNotWhenStart){etPrice.requestFocus();}
        if(cardColor!=0){cv.setCardBackgroundColor(cardColor);}
        return rootView;
    }

    void findViewsInFragment(View rootView){
        cv= ((CardView) rootView);
        layout= ((LinearLayout) cv.findViewById(R.id.layout));
        lay2= ((LinearLayout) cv.findViewById(R.id.lay2));
        etPrice=(EditText)rootView.findViewById(R.id.et_dop_price);
        etQuantity=(EditText)rootView.findViewById(R.id.et_dop_quantity);
        etLay1=(TextInputLayout)rootView.findViewById(R.id.etLay1);
        etLay2=(TextInputLayout)rootView.findViewById(R.id.etLay2);
        layout=(LinearLayout)rootView.findViewById(R.id.layout);
        tvResult=(TextView)rootView.findViewById(R.id.tv_dop_result);
        tv_dop_economy=(TextView)rootView.findViewById(R.id.tv_dop_economy);
        btn=(Button)rootView.findViewById(R.id.button_dop_unit);

        StaticNeedSupplement.ScaleLongStringsInTextView(etPrice);
        StaticNeedSupplement.ScaleLongStringsInTextView(etQuantity);

        etPrice.addTextChangedListener(this);
        etQuantity.addTextChangedListener(this);
        if(lay2==null){Log.d("touch","null");}
        cv.setOnTouchListener(this);
        lay2.setOnTouchListener(this);
        layout.setOnTouchListener(this);
        etLay1.setOnTouchListener(this);
        etLay2.setOnTouchListener(this);
        etQuantity.setOnTouchListener(this);
        etPrice.setOnTouchListener(this);
        tv_dop_economy.setOnTouchListener(this);
        tvResult.setOnTouchListener(this);

    }

    public double getRes() {
        return res;
    }

    public void setRes(double res) {
        this.res = res;
    }


    public void setCardColor(int color){
        if(color!=cardColor){cardColor=color;cv.setCardBackgroundColor(color);
        Log.d("paint","перерисовался");}
    }

    public void removeMySelf(){
        fragments.remove(this);
        this.getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        ((MainActivity)this.getActivity()).startThread();
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        ((MainActivity)getActivity()).startThread();

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(v.getId()==R.id.et_dop_price){


            if(event.getAction()==MotionEvent.ACTION_DOWN)
            {Log.d("touch","down");return true;}

            if(event.getAction()==MotionEvent.ACTION_CANCEL)
            {Log.d("touch","cancel");return true;}

            if(event.getAction()==MotionEvent.ACTION_UP)
            {Log.d("touch","pointerUP");return true;}}

        return false;
    }
}
