package ru.forge.twice_a_day.quickcomparison.helpers;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;


public class StaticNeedSupplement {

    synchronized public static double rounded(double d,int flag){
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

    synchronized public static void ScaleLongStringsInTextView(TextView tv) {
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

     synchronized public static double getDoubleFromET(EditText et){
        double value;
        String strValue =et.getText().toString();
        Log.d("myRun",strValue);
            try{
            value=Double.valueOf(strValue);
                Log.d("myRun",value+"");
            }catch(Exception e){value=0;}

        return value;
    }

    public static double returnSomeResult(){
        double value =5;
        Log.d("myRun",value+"");
        return value;
    }

    public static String formatter(double d){
        d=rounded(d,2);

        String s="";
        if(d==(int)d){
            int k= (int) d;
            s=String.valueOf(k);
        }else{
            DecimalFormat df = new DecimalFormat("################.#################");
            s =df.format(d);
        //    s=String.valueOf(d);
        }
        return s;
    }
}
