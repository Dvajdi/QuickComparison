package ru.forge.twice_a_day.quickcomparison;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by twice on 22.03.16.
 */
public class RawFragment extends Fragment {
    ArrayList fragments;
    EditText etPrice;
    EditText etQuantity;
    TextView tvResult;

    RawFragment(ArrayList fragments){
        this.fragments=fragments;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);

        View rootView=inflater.inflate(R.layout.row,container,false);
        etPrice = (EditText)rootView.findViewById(R.id.et_dop_price);
        etQuantity = (EditText)rootView.findViewById(R.id.et_dop_quantity);
        tvResult = (TextView)rootView.findViewById(R.id.tv_dop_result);

        ((Button) rootView.findViewById(R.id.button_dop_delete)).setOnClickListener(new DelRawListener(this,fragments));

        return rootView;
    }

    public void setTVResult(){
        double price = StaticDifferents.getDoubleFromEt(etPrice);
        double quantity = StaticDifferents.getDoubleFromEt(etQuantity);
        Log.d("dom","price = "+price);
        Log.d("dom","quantity = "+quantity);
//        if(quantity>0){tvResult.setText(String.valueOf(StaticDifferents.rounded((price/quantity),1)));}else{tvResult.setText("");}
      if(quantity>0){tvResult.setText("привет");}
    }



}
