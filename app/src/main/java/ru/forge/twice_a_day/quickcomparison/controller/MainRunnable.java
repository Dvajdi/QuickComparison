package ru.forge.twice_a_day.quickcomparison.controller;

import android.os.Message;
import android.view.View;
import android.widget.EditText;

import ru.forge.twice_a_day.quickcomparison.R;
import ru.forge.twice_a_day.quickcomparison.util.FormatAdapter;
import ru.forge.twice_a_day.quickcomparison.util.StaticNeedSupplement;
import ru.forge.twice_a_day.quickcomparison.views.MainActivity;
import ru.forge.twice_a_day.quickcomparison.views.RowFragment;

/**
 * Created by Павел on 14.09.2016.
 */
public class MainRunnable implements Runnable{
  RowFragment rf;
  double res,min;
  int arg1,minIndex;

  @Override
  public void run() {
    setResult();
    min=findMin();
    for (int i = 0; i < rowFragments.size() ; i++) {
      rf= rowFragments.get(i);
      res=rf.getRes();
      if (res ==min){arg1=AppRes.COLOR_BEST;minIndex=i;}else{arg1=AppRes.COLOR_MAIN;}
      Message msg = MainActivity.h.obtainMessage(i, arg1, minIndex, min);
      h.sendMessage(msg);
    }
  }

  public double findMin(){
    double min=Double.MAX_VALUE-1000;
    double value;
    for (int i = 0; i < rowFragments.size(); i++) {
      value= rowFragments.get(i).getRes();
      if(value!=0 && value!=Double.MAX_VALUE){
        if(min>value){min=value;}}
    }
    return min;
  }
  void setResult(){
    boolean isCorrectQuantity;
    koef=0;
    RowFragment rf;
    EditText etPrice;
    EditText etQuantity;
    View v;
    String strPrice,strQuantity;
    double price,quantity=1,res,resWithoutUnit;

    goalQuantity= StaticNeedSupplement.getDoubleFromET(etGoalQuantity);
    if(goalQuantity==0){goalQuantity=1;}

    goalUnit = btnGoalUnit.getText().toString();

    for (int i = 0; i < rowFragments.size() ; i++) {
      rf = rowFragments.get(i);
      v=rf.getView();
      if(v!=null) {
        etPrice = (EditText) v.findViewById(R.id.et_dop_price);
        etQuantity = (EditText) v.findViewById(R.id.et_dop_quantity);
        strPrice = etPrice.getText().toString();
        strQuantity = etQuantity.getText().toString();
        int someInt = FormatAdapter.getKoef(strPrice);
        if(koef< someInt){koef=someInt;}

        try {
          price = Double.valueOf(strPrice);
        } catch (Exception e) {
          price = 0;
        }

        isCorrectQuantity=true;

        if(strQuantity.equals("0")||strQuantity.equals("0.")||strQuantity.equals("0,")){isCorrectQuantity=false;}
        else{
          if(strQuantity.equals("")){
            quantity=1;
          }else{if(quantity==0){quantity=1;}else{quantity=Double.valueOf(strQuantity);}}}

        if(price==0||!isCorrectQuantity){
          res=Double.MAX_VALUE;
          resWithoutUnit=0;
        }
        else{
          resWithoutUnit=price / quantity;
          res = price / (quantity*rf.getUnitValue());
        }
        rf.setRes(res);
        rf.setResWithoutUnit(resWithoutUnit);
      }
    }
  }
}
