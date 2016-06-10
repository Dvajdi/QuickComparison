package ru.forge.twice_a_day.quickcomparison;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import ru.forge.twice_a_day.quickcomparison.about_units.AllUnits;
import ru.forge.twice_a_day.quickcomparison.about_units.UnitsType;
import ru.forge.twice_a_day.quickcomparison.standart_helpers.StaticNeedSupplement;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private static String BEST_RESULT;
    private static String THREAD_NAME;
    private static String MES_RUB;
    private static String ECONOMY_STR;
    private static String RES_STR;
    private static ArrayList<RawFragment> rawFragments;
    private static OwnHandler h;
    private static Thread t;
    private static int COLOR_BEST;
    private static int COLOR_MAIN;

    private int j=1;


    static EditText etGoalQuantity;
    static double  goalQuantity;
    public static Button btnGoalUnit;
    static String goalUnit;
    static double goalUnitValue=1;


    public static MainTextWatcher textWatcher;


    static AllUnits allUnits;

    static UnitsType goalUnitType = UnitsType.all;
    static public boolean isFirstChange=true;
    static public boolean isChangeAll =false;
    static String goalUnitName;

    Runtime r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.material_activity_without_table);
        findMyViews();
        findObjects(savedInstanceState);
        setListeners();
        startThread();
    }

    private void findMyViews() {
        fab=(FloatingActionButton)findViewById(R.id.fab2);
        toolbar=(Toolbar)findViewById(R.id.tool_bar);
        etGoalQuantity = (EditText)findViewById(R.id.et_goal_quantity);
        btnGoalUnit = (Button)findViewById(R.id.btnGoalUnit);
    }
    private void findObjects(Bundle savedInstanceState){
        rawFragments=(ArrayList<RawFragment>) getLastCustomNonConfigurationInstance();
        if(rawFragments==null){rawFragments = new ArrayList<>();}else{Log.d("life","не ноль"); }
        if(savedInstanceState==null){setContent();}else{btnGoalUnit.setText(goalUnitName);}
    }
    private void setContent(){
        allUnits = new AllUnits(this);
        goalUnitName=allUnits.defaultUnit;
        h=new OwnHandler();

        COLOR_BEST=getResources().getColor(R.color.colorPrimary);
        COLOR_MAIN=getResources().getColor(R.color.colorVariant3);
        BEST_RESULT=getResources().getString(R.string.best_result);
        MES_RUB=getResources().getString(R.string.rub);
        ECONOMY_STR = getResources().getString(R.string.economyStr);
        RES_STR=getResources().getString(R.string.resStr);
        THREAD_NAME = getResources().getString(R.string.thread_name);

        createStartRows();
    }
    private void setListeners(){
        textWatcher = new MainTextWatcher(this);
        fab.setOnClickListener(this);
        btnGoalUnit.setOnClickListener(this);
        setSupportActionBar(toolbar);
        etGoalQuantity.addTextChangedListener(textWatcher);
        StaticNeedSupplement.ScaleLongStringsInTextView(etGoalQuantity);

        }

    private void createStartRows(){
        createRow(true);
        createRow(false);
        try{
        isFirstChange=true;
        isChangeAll=true;
        setUnits(allUnits.defaultUnit,1);}catch (NullPointerException e){
            Toast.makeText(this,allUnits.defaultUnit,Toast.LENGTH_SHORT).show();}
    }

    private void createRow(boolean isNotWhenStart){
        addNewFragment(isNotWhenStart);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.debug){doDebug();}
        if (item.getItemId() == R.id.clear_all) {
            clearAll();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return rawFragments;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.fab2:
                createRow(true);
                break;
            case R.id.btnGoalUnit:
                setGoalUnit();
                break;
        }
    }

    void setGoalUnit(){
        isFirstChange = true;
        Intent intent = new Intent(this,ListUnitsActivity.class);
        startActivityForResult(intent,5000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
        if(requestCode==5000){
            setUnits(data.getStringExtra("name"),data.getDoubleExtra("value",1));
        }}
        startThread();
        super.onActivityResult(requestCode, resultCode, data);
    }

    void setUnits(String unitName,double unitValue){
        btnGoalUnit.setText(unitName);
        goalUnitName=unitName;
        goalUnitValue = unitValue;
        if(isChangeAll){changeUnitsInFragments(goalUnitName,goalUnitValue);}
    }

    public static void changeUnitsInFragments(String s,double unitValue) {
        for (int i = 0; i < rawFragments.size(); i++) {
            rawFragments.get(i).getBtnUnit().setText(s);
            rawFragments.get(i).setRawUnit(s);
            rawFragments.get(i).setUnitValue(unitValue);
        }
            isChangeAll=false;
    }

    private void clearAll(){
        stopThread();
        clearFragments();
        createStartRows();
        startThread();

    }

    private void stopThread(){
        if(t!=null){try{if(t.isAlive()){t.join();}}catch(InterruptedException e){e.printStackTrace();}
        }
    }
    void startThread(){
        j++;
        t =new Thread(new OwnRunnable(),THREAD_NAME+j);
        t.start();
    }

    private void addNewFragment(boolean isNotFirstTwoRows) {
        RawFragment rf = new RawFragment();
        rf.setFragments(rawFragments,isNotFirstTwoRows);
        getSupportFragmentManager().beginTransaction().add(R.id.rl_main, rf).commit();
        rawFragments.add(rf);
    }
    private void clearFragments(){
        for (int i = 0; i <rawFragments.size() ; i++) {
            getSupportFragmentManager().beginTransaction().remove(rawFragments.get(i)).commit();
        }
        rawFragments.clear();
    }

    static class OwnHandler extends Handler{
        View v;
        TextView tv_res,tv_res_economy;
        RawFragment rf;
        double res,resPac,economy,minRes,economyPercent,resWithoutUnit;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                minRes = (double) msg.obj;
                rf = rawFragments.get(msg.what);
                v = rf.getView();
                v = v != null ? rf.getView().findViewById(R.id.doprow) : null;
                if (v != null) {
                    tv_res = (TextView) v.findViewById(R.id.tv_dop_result);
                    tv_res_economy = (TextView) v.findViewById(R.id.tv_dop_economy);

                    StaticNeedSupplement.ScaleLongStringsInTextView(tv_res);
                    res = rf.getRes();
                    resPac = rf.getResPac();
                    resWithoutUnit=rf.getResWithoutUnit();
                    if(res==Double.MAX_VALUE){tv_res.setText(String.format(Locale.ROOT,RES_STR,"0",MES_RUB));tv_res_economy.setText("");}
                    else{
                        tv_res.setText(String.format(Locale.ROOT, RES_STR, StaticNeedSupplement.formatter(resWithoutUnit), MES_RUB));

                        economyPercent = StaticNeedSupplement.rounded((res / minRes - 1) * 100, 2);
                        economy = (res - minRes)*goalQuantity*(goalUnitValue);
                        //Log.d("converter","economy = "+economy);

                        if ((economy >= 0)) {
                            if (economy == 0) {
                                tv_res_economy.setText(BEST_RESULT);
                            } else {
                                tv_res_economy.setText(String.format(Locale.ROOT, ECONOMY_STR, StaticNeedSupplement.formatter(economy), StaticNeedSupplement.formatter(economyPercent), "%",StaticNeedSupplement.formatter(goalQuantity),goalUnit));
                            }
                        }
                    }
                rf.setCardColor(msg.arg1);
            }
            }
            catch(NullPointerException | IndexOutOfBoundsException e){e.printStackTrace();}

        }
    }

    static class OwnRunnable implements Runnable {
        RawFragment rf;
        double res,min;
        int arg1,minIndex;

        @Override
        public void run() {

            setResult();
            min=findMin();
                for (int i = 0; i <rawFragments.size() ; i++) {
                    rf=rawFragments.get(i);
                        res=rf.getRes();
                            Log.d("color","min = "+min +" ; "+"res = "+res);
                            if (res ==min){arg1=COLOR_BEST;minIndex=i;}else{arg1=COLOR_MAIN;}
                            Message msg = h.obtainMessage(i, arg1, minIndex, min);
                        h.sendMessage(msg);
                    }
        }

        public double findMin(){
            double min=Double.MAX_VALUE-1000;
            double value;
            for (int i = 0; i <rawFragments.size(); i++) {
                value=rawFragments.get(i).getRes();
                if(value!=0){
                if(min>value){min=value;}}
            }
            return min;
        }
        void setResult(){
            RawFragment rf;
            EditText etPrice;
            EditText etQuantity;
            View v;
            String strPrice,strQuantity;
            double price,quantity,res,resWithoutUnit;

            goalQuantity=StaticNeedSupplement.getDoubleFromET(etGoalQuantity);
            if(goalQuantity==0){goalQuantity=1;}

            goalUnit = btnGoalUnit.getText().toString();

            for (int i = 0; i <rawFragments.size() ; i++) {
                rf = rawFragments.get(i);
                v=rf.getView();
                if(v!=null) {
                    etPrice = (EditText) v.findViewById(R.id.et_dop_price);
                    etQuantity = (EditText) v.findViewById(R.id.et_dop_quantity);

                    strPrice = etPrice.getText().toString();
                    strQuantity = etQuantity.getText().toString();

                    try {
                        price = Double.valueOf(strPrice);
                    } catch (Exception e) {
                        price = 0;
                    }
                    try {
                        quantity = Double.valueOf(strQuantity);
                    } catch (Exception e) {
                        quantity = 1;
                    }

                    if(price==0){
                        res=0;
                        resWithoutUnit=0;
                    }
                    else{
                        resWithoutUnit=price / quantity;
                        res = price / (quantity*rf.getUnitValue());
                        //if(res<0.00001&&res>0){res=0.00001;}
                       }

                    rf.setRes(res);
                    rf.setResWithoutUnit(resWithoutUnit);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        stopThread();
        super.onDestroy();

    }

    private void doDebug() {

    }


}
