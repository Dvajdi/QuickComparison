package ru.forge.twice_a_day.quickcomparison.controller;

import android.content.Context;
import ru.forge.twice_a_day.quickcomparison.R;

public class AppRes {
  public static int COLOR_BEST;
  public static int COLOR_MAIN;
  public static String BEST_RESULT;
  public static String MES_RUB;
  public static String ECONOMY_STR;
  public static String RES_STR;
  public static String THREAD_NAME;
  public static String AROUND_0;

  public static void loadAppRes(Context act){
    COLOR_BEST = act.getResources().getColor(R.color.colorPrimary);
    COLOR_MAIN = act.getResources().getColor(R.color.colorVariant3);
    BEST_RESULT = act.getResources().getString(R.string.best_result);
    MES_RUB = act.getResources().getString(R.string.rub);
    ECONOMY_STR = act.getResources().getString(R.string.economyStr);
    RES_STR = act.getResources().getString(R.string.resStr);
    THREAD_NAME = act.getResources().getString(R.string.thread_name);
    AROUND_0 = act.getResources().getString(R.string.around_0);
  }
}
