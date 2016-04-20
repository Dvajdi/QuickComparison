package ru.forge.twice_a_day.quickcomparison;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    FloatingActionButton fab;
    Toolbar toolbar;
    android.support.v4.app.FragmentTransaction ft;
    LinearLayout rl_main;
    static String MES_ECONOMY,MES_ECONOMY_PERCENT,MES_PERCENT,BEST_RESULT,MES_RUB;;

    static ArrayList <MyRow>rows;
    static ArrayList<RawFragment> rawFragments;
    static boolean isStopped;
    static OwnHandler h;
    static Thread t;
    static int COLOR_BEST,COLOR_MAIN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //setContentView(R.layout.material_activity);
        setContentView(R.layout.material_activity_without_table);
        findMyViews();
        setListeners();
        isStopped=false;
        if(rawFragments==null){rawFragments = new ArrayList<>();}
        if(savedInstanceState==null){setContent();}
        h=new OwnHandler();

        t =new Thread(new OwnRunnable());
        t.start();

    }

    public void findMyViews() {
        fab=(FloatingActionButton)findViewById(R.id.fab2);
        toolbar=(Toolbar)findViewById(R.id.tool_bar);
        rl_main = (LinearLayout) findViewById(R.id.rl_main);
    }
    private void setListeners(){
        fab.setOnClickListener(this);
        setSupportActionBar(toolbar);
    }

    void createRow(){
        rows.add(new MyRow("ед."));
        addNewFragment();

    }

    void createStartRows(){
        createRow();
        createRow();


    }

    void setContent(){
        rows = new ArrayList<>();
        createStartRows();
        COLOR_BEST=getResources().getColor(R.color.colorAccent);
        COLOR_MAIN=getResources().getColor(R.color.colorPrimary);
        MES_ECONOMY =getResources().getString(R.string.economy);
        MES_ECONOMY_PERCENT =getResources().getString(R.string.economy_percent);
        MES_PERCENT =getResources().getString(R.string.percent);
        BEST_RESULT=getResources().getString(R.string.best_result);
        MES_RUB=getResources().getString(R.string.rub);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.clear_all) {
            clearAll();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.fab2:
                createRow();
                break;
            default:
                Toast.makeText(this,"отстань",Toast.LENGTH_SHORT).show();
        }
    }
    protected void clearAll(){
        clearFragments();
        rows.clear();
        createStartRows();
    }

    private void addNewFragment() {
        RawFragment rf = new RawFragment();
        ft=getSupportFragmentManager().beginTransaction();
        rf.setFragments(rawFragments);
        ft.add(R.id.rl_main, rf);
        ft.commit();
        rawFragments.add(rf);
    }

    private void clearFragments(){
        for (int i = 0; i <rawFragments.size() ; i++) {
            ft=getSupportFragmentManager().beginTransaction();
            ft.remove(rawFragments.get(i));
            ft.commit();
        }
        rawFragments.clear();
    }




    static class OwnHandler extends Handler{
        View v;
        TextView tv_res,tv_res_economy;
        RawFragment rf;
        double res,economy,minRes,economyPercent;
        int minIndex;
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                minRes=rawFragments.get(msg.arg2).getRes();

                rf = rawFragments.get(msg.what);
                v = rf.getView().findViewById(R.id.doprow);
                if (v != null) {
                    tv_res = (TextView) v.findViewById(R.id.tv_dop_result);
                    res=(Double)msg.obj;
                    tv_res.setText(String.valueOf(res)+" "+MES_RUB);

                    tv_res_economy=(TextView)v.findViewById(R.id.tv_dop_economy);
                    economyPercent=StaticDifferents.rounded((res/minRes-1)*100,2);
                    economy=StaticDifferents.rounded(res-minRes,2);
                    if((economy>=0)&&(res>0)) {
                        if (economy == 0) {
                            tv_res_economy.setText(BEST_RESULT);
                        } else {
                            tv_res_economy.setText(MES_ECONOMY +" "+ String.valueOf(economy) + MES_ECONOMY_PERCENT +"  "+ String.valueOf(economyPercent) + MES_PERCENT);
                        }
                    }
                }
               // v.setBackgroundColor(msg.arg1);
                ((CardView)v).setCardBackgroundColor(msg.arg1);

            }
            catch(IndexOutOfBoundsException e){e.printStackTrace();}
            catch(NullPointerException e){e.printStackTrace();}

        }
    }
    static class OwnRunnable implements Runnable {
        RawFragment rf;
        EditText etPrice;
        EditText etQuantity;
        View v;
        String strPrice,strQuantity;
        double price,quantity,res;
        Double result;
        int arg1,minIndex;
        String str;
        @Override
        public void run() {
            while(!isStopped){
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
                        res = StaticDifferents.rounded(price / quantity,2);

                            rf.setRes(res);
                        if (res ==findMin(rawFragments)){arg1=COLOR_BEST;minIndex=i;}else{arg1=COLOR_MAIN;}

                            Sleeper.sleep(15);
                        //str = String.valueOf(res);
                        result=res;
                        Message msg = h.obtainMessage(i, arg1, minIndex, result);

                        h.sendMessage(msg);
                    }
                }
            }
        }

        public double findMin(ArrayList<RawFragment> rawFragments){
            double min=Double.MAX_VALUE;
            double value;
            for (int i = 0; i <rawFragments.size(); i++) {
                value=rawFragments.get(i).getRes();
                if(min>value&&value!=0){min=value;}
            }

            return min;
        }
    }

}
