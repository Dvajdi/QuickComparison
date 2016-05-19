package ru.forge.twice_a_day.quickcomparison;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.CardView;
import java.util.ArrayList;

/**
 * Created by twice on 22.03.16.
 */
public class RawFragment extends Fragment implements TextWatcher,Animation.AnimationListener{
    ArrayList fragments;
    EditText etPrice;
    EditText etQuantity;
    TextView tvResult,tv_dop_economy;
    double res;

    Activity ctx;
    MyCardView cv;
    TextInputLayout etLay1,etLay2;
    LinearLayout layout,lay2;
    boolean isNotWhenStart;
    int cardColor;
    Button btn;
    View rootView;
    Animation anim;


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
        cv.setRf(this);
        cv.setScrollView((ScrollView) getActivity().findViewById(R.id.scrollView));
        if(isNotWhenStart){etPrice.requestFocus();}
        if(cardColor!=0){cv.setCardBackgroundColor(cardColor);}


        return rootView;
    }

    void findViewsInFragment(View rootView){
        cv=(MyCardView) rootView;
        etPrice=(EditText)rootView.findViewById(R.id.et_dop_price);
        etQuantity=(EditText)rootView.findViewById(R.id.et_dop_quantity);

        tvResult=(TextView)rootView.findViewById(R.id.tv_dop_result);
        tv_dop_economy=(TextView)rootView.findViewById(R.id.tv_dop_economy);
        btn=(Button)rootView.findViewById(R.id.button_dop_unit);

        StaticNeedSupplement.ScaleLongStringsInTextView(etPrice);
        StaticNeedSupplement.ScaleLongStringsInTextView(etQuantity);

        etPrice.addTextChangedListener(this);
        etQuantity.addTextChangedListener(this);

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
        anim = AnimationUtils.loadAnimation(getContext(),R.anim.anim);
        anim.setAnimationListener(this);
        rootView.startAnimation(anim);
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
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        fragments.remove(this);
        if(this!=null){
        FragmentTransaction ft =getActivity().getSupportFragmentManager().beginTransaction();
        ft.remove(this).commit();}
        ((MainActivity)getActivity()).startThread();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
