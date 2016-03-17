package ru.forge.twice_a_day.quickcomparison;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by twice on 17.03.16.
 */
public class PriceWatcher implements TextWatcher {
    int position;
    ArrayList<MyRow> rows;
    BoxAdapter ba;

    public PriceWatcher(int position, ArrayList<MyRow> rows,BoxAdapter ba) {
        this.position = position;
        this.rows = rows;
        this.ba=ba;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

        String ss = String.valueOf(s);
        if (ss.equals("")) {
        } else {
            try{
            rows.get(position).setPrice(Double.valueOf(ss));}catch(IndexOutOfBoundsException e){Log.d("dom","нек = "+ rows.size());}
            rows.get(position).setResult();
            ba.notifyDataSetChanged();
        }
    }
}