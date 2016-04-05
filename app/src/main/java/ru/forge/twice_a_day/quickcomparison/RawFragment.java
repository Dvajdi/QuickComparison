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
public class RawFragment extends Fragment implements ScrollViewListener{
    ArrayList fragments;
    EditText etPrice;
    EditText etQuantity;
    TextView tvResult;
    double res;
    MyScroll myScroll;


    public void setFragments(ArrayList fragments) {
        this.fragments = fragments;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);

       // View rootView=inflater.inflate(R.layout.row, container, false);
        View rootView=inflater.inflate(R.layout.row_with_scroll, container, false);

        myScroll= ((MyScroll)rootView);
        myScroll.setScrollViewListener(this);
        ((Button) rootView.findViewById(R.id.button_dop_delete)).setOnClickListener(new DelRawListener(this, fragments));

        return rootView;
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

            Log.d("qwe","x = "+x);
            this.getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
            int number = 0;
            for (int i = 0; i < fragments.size(); i++) {
                if (fragments.get(i).equals(this)) {
                    number = i;
                }
            }
           try{
            fragments.remove(number);
               Log.d("qwe", "number = " + number);
               Log.d("qwe", "fragments = " + fragments.size());
               Log.d("qwe", "fragments = " + fragments.size());

           }catch(Exception e){}
        }
    }
}
