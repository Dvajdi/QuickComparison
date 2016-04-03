package ru.forge.twice_a_day.quickcomparison;

import android.util.Log;
import android.widget.EditText;

/**
 * Created by twice on 23.03.16.
 */
public class StaticDifferents {
    public static double getDoubleFromEt(EditText et){
        double d=0;
        try{
            d=Double.valueOf(et.getText().toString());
        }catch(Exception e){d=0;}
        return d;
    }

    public static double rounded(double d,int flag){
        int parameter=1;
        int k;
        double res;
        for (int i = 0; i <flag ; i++) {
            parameter*=10;
        }
        d=d*parameter;
        k=(int)d;
        res=(double)(k)/parameter;
        return res;
    }
}
