package ru.forge.twice_a_day.quickcomparison.controller;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import ru.forge.twice_a_day.quickcomparison.R;
import ru.forge.twice_a_day.quickcomparison.util.StaticNeedSupplement;
import ru.forge.twice_a_day.quickcomparison.views.MainActivity;
import ru.forge.twice_a_day.quickcomparison.views.RowFragment;

public class MainHandler  extends Handler {
  View v;
  TextView tv_res,tv_res_economy;
  RowFragment rf;
  double res,economy,minRes,economyPercent,resWithoutUnit;

  @Override
  public void handleMessage(Message msg) {
    super.handleMessage(msg);
    try {
      minRes = (double) msg.obj;
      rf = MainActivity.rowFragments.get(msg.what);
      v = rf.getView();
      v = v != null ? rf.getView().findViewById(R.id.doprow) : null;
      if (v != null) {
        tv_res = (TextView) v.findViewById(R.id.tv_dop_result);
        tv_res_economy = (TextView) v.findViewById(R.id.tv_dop_economy);

        StaticNeedSupplement.ScaleLongStringsInTextView(tv_res);
        res = rf.getRes();

        resWithoutUnit=rf.getResWithoutUnit();
        if(res==Double.MAX_VALUE){
          tv_res.setText(String.format(Locale.ROOT,AppRes.RES_STR,"0",AppRes.MES_RUB));tv_res_economy.setText("");}
        else{
          String strRes;

          if((resWithoutUnit>0)&&(resWithoutUnit<1/Math.pow(10,MainActivity.koef))){
            strRes=AppRes.AROUND_0;
          }else{
            strRes=StaticNeedSupplement.formatter(resWithoutUnit,MainActivity.koef);
          }

          tv_res.setText(String.format(Locale.ROOT, AppRes.RES_STR, strRes, AppRes.MES_RUB));
          economyPercent = StaticNeedSupplement.rounded((res / minRes - 1) * 100, 2);
          economy = (res - minRes)*MainActivity.goalQuantity*(MainActivity.goalUnitValue);

          if ((economy >= 0)) {
            if (economy == 0) {
              tv_res_economy.setText(AppRes.BEST_RESULT);
            } else {
              String strEconomy;

              if(economy<1/Math.pow(10,MainActivity.koef)){
                strEconomy= AppRes.AROUND_0;
              }else{
                strEconomy= StaticNeedSupplement.formatter(economy,MainActivity.koef);
              }

              tv_res_economy.setText(String.format(Locale.ROOT, AppRes.ECONOMY_STR, strEconomy, StaticNeedSupplement.formatter(economyPercent,MainActivity.koef), "%",StaticNeedSupplement.formatter(MainActivity.goalQuantity,MainActivity.koef),MainActivity.goalUnit));
            }
          }
        }
        rf.setCardColor(msg.arg1);
      }
    }
    catch(NullPointerException | IndexOutOfBoundsException e){e.printStackTrace();}
  }
}
