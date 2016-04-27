package ru.forge.twice_a_day.quickcomparison;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private static String BEST_RESULT;
    private static String MES_RUB;
    private static String ECONOMY_STR;
    private static String RES_STR;
    private static ArrayList<RawFragment> rawFragments;
    private static boolean isStopped;
    private static OwnHandler h;
    private static Thread t;
    private static int COLOR_BEST;
    private static int COLOR_MAIN;

    int i=1,j=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.material_activity_without_table);
        findMyViews();
        setListeners();

        rawFragments=(ArrayList<RawFragment>) getLastCustomNonConfigurationInstance();
        if(rawFragments==null){rawFragments = new ArrayList<>();}else{Log.d("life","не ноль");}
        if(savedInstanceState==null){setContent();}

        startThread();

    }

    private void findMyViews() {
        fab=(FloatingActionButton)findViewById(R.id.fab2);
        toolbar=(Toolbar)findViewById(R.id.tool_bar);
    }
    private void setContent(){
        createStartRows();
        h=new OwnHandler();
        COLOR_BEST=getResources().getColor(R.color.colorVariant3);
        COLOR_MAIN=getResources().getColor(R.color.colorPrimary);
        BEST_RESULT=getResources().getString(R.string.best_result);
        MES_RUB=getResources().getString(R.string.rub);
        ECONOMY_STR = getResources().getString(R.string.economyStr);
        RES_STR=getResources().getString(R.string.resStr);
    }
    private void setListeners(){
        fab.setOnClickListener(this);
        setSupportActionBar(toolbar);

    }

    private void createStartRows(){
        createRow(true);
        createRow(false);
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

            default:
                Toast.makeText(this,"отстань",Toast.LENGTH_SHORT).show();
        }
    }




    private void clearAll(){
        stopThread();
        clearFragments();
        createStartRows();
        startThread();
    }

    void stopThread(){
        isStopped=true;
        if(t!=null){try{if(t.isAlive()){t.join();}}catch(InterruptedException e){e.printStackTrace();}
        }
    }
    void startThread(){isStopped=false;
        j++;
        t =new Thread(new OwnRunnable(),"РАСЧЕТЫ"+j);
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
        double res,economy,minRes,economyPercent;

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

                    Log.d("res","res = "+res);

                    if(res==Double.MAX_VALUE){tv_res.setText("");tv_res_economy.setText("");}
                    else{

                        tv_res.setText(String.format(Locale.ROOT, RES_STR, res, MES_RUB));
                        economyPercent = StaticNeedSupplement.rounded((res / minRes - 1) * 100, 2);
                        economy = StaticNeedSupplement.rounded(res - minRes, 2);

                        if ((economy >= 0)) {
                            if (economy == 0) {
                                tv_res_economy.setText(BEST_RESULT);
                            } else {
                                tv_res_economy.setText(String.format(Locale.ROOT, ECONOMY_STR, economy, economyPercent, "%"));
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
        Double result;
        int arg1,minIndex;

        @Override
        public void run() {
            setResult();
            min=findMin();
                for (int i = 0; i <rawFragments.size() ; i++) {
                    rf=rawFragments.get(i);
                        res=rf.getRes();
                            if (res ==min){arg1=COLOR_BEST;minIndex=i;}else{arg1=COLOR_MAIN;}
                            result=min;
                            Message msg = h.obtainMessage(i, arg1, minIndex, result);
                        h.sendMessage(msg);
                    }
        }

        public double findMin(){
            double min=Double.MAX_VALUE;
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
            double price,quantity,res;

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
                        res=Double.MAX_VALUE;
                    }
                    else{
                        res = StaticNeedSupplement.rounded(price / quantity,2);
                       }
                    rf.setRes(res);
                    Log.d("res","res = "+res);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        stopThread();
        Log.d("life","onDestroy");
        super.onDestroy();

    }

    private void doDebug() {
        if(i%2==0){
            isStopped=true;}else{isStopped=false;
            startThread();
        }
        Log.d("iii","i = "+i%2);
        i++;

        /*for (int i = 0; i <rawFragments.size() ; i++) {
            Log.d("qwe","color "+i+" "+rawFragments.get(i).cardColor);
        }*/
    }

}
