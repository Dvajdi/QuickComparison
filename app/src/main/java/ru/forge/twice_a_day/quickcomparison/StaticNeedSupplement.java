package ru.forge.twice_a_day.quickcomparison;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;


public class StaticNeedSupplement {

    public static double rounded(double d,int flag){
        int parameter=1;
        long k;
        double res;
        for (int i = 0; i <flag ; i++) {
            parameter*=10;
        }
        d=d*parameter;
        d=Math.round(d);
        k=(long)d;
        res=(double)(k)/parameter;
        return res;
    }

    public static void ScaleLongStringsInTextView(TextView tv) {
        final TextView textView=tv;

        tv.addTextChangedListener(new TextWatcher() {
            float scale=1;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                scale = s.length() >= 7 ? 0.8f :1;
                textView.setTextScaleX(scale);
            }
        });
    }
}
