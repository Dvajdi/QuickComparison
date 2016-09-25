package ru.forge.twice_a_day.quickcomparison.views;

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
import android.widget.ScrollView;
import android.widget.TextView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.Locale;

import ru.forge.twice_a_day.quickcomparison.R;
import ru.forge.twice_a_day.quickcomparison.controller.AppRes;
import ru.forge.twice_a_day.quickcomparison.controller.MainHandler;
import ru.forge.twice_a_day.quickcomparison.controller.MainRunnable;
import ru.forge.twice_a_day.quickcomparison.models.work_with_units.AllUnits;
import ru.forge.twice_a_day.quickcomparison.util.StaticNeedSupplement;
import ru.forge.twice_a_day.quickcomparison.util.FormatAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private FloatingActionButton fab;
    private Toolbar toolbar;
    public static ArrayList<RowFragment> rowFragments;
    public static MainHandler h;
    private static Thread t;

    private int j=1;
    public static NumberEditText etGoalQuantity;
    public static double  goalQuantity;
    public static Button btnGoalUnit;
    public static String goalUnit;
    public static double goalUnitValue=1;

    public static MainTextWatcher textWatcher;
    static AllUnits allUnits;
    static public boolean isFirstChange=true;
    static public boolean isChangeAll =false;
    static String goalUnitName;
    public static int koef=0;
    ScrollView sv;

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
        sv=(ScrollView)findViewById(R.id.scrollView);
        fab=(FloatingActionButton)findViewById(R.id.fab2);
        toolbar=(Toolbar)findViewById(R.id.tool_bar);
        etGoalQuantity = (NumberEditText)findViewById(R.id.et_goal_quantity);
        btnGoalUnit = (Button)findViewById(R.id.btnGoalUnit);
    }
    private void findObjects(Bundle savedInstanceState){
        rowFragments =(ArrayList<RowFragment>) getLastCustomNonConfigurationInstance();
        if(rowFragments ==null){
          rowFragments = new ArrayList<>();}else{Log.d("life","не ноль"); }
        if(savedInstanceState==null){setContent();}else{btnGoalUnit.setText(goalUnitName);}
    }
    private void setContent(){
        AppRes.loadAppRes(this);
        allUnits = new AllUnits(this);
        goalUnitName=allUnits.defaultUnit;
        h=new MainHandler();
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
        setUnits(allUnits.defaultUnit,1);}catch (NullPointerException e){e.printStackTrace();}
    }

    private void createRow(boolean isNotWhenStart){
        addNewFragment(isNotWhenStart);
        sv.scrollTo(0,sv.getMaxScrollAmount());
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
    public Object onRetainCustomNonConfigurationInstance() {
        return rowFragments;
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
        for (int i = 0; i < rowFragments.size(); i++) {
            rowFragments.get(i).getBtnUnit().setText(s);
            rowFragments.get(i).setRawUnit(s);
            rowFragments.get(i).setUnitValue(unitValue);
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
        t =new Thread(new MainRunnable(),AppRes.THREAD_NAME+j);
        t.start();
    }

    private void addNewFragment(boolean isNotFirstTwoRows) {
        RowFragment rf = new RowFragment();
        rf.setFragments(rowFragments,isNotFirstTwoRows);
        getSupportFragmentManager().beginTransaction().add(R.id.rl_main, rf).commit();
        rowFragments.add(rf);

    }
    private void clearFragments(){
        for (int i = 0; i < rowFragments.size() ; i++) {
            getSupportFragmentManager().beginTransaction().remove(rowFragments.get(i)).commit();
        }
        rowFragments.clear();
    }

    @Override
    protected void onDestroy() {
        stopThread();
        super.onDestroy();

    }
}
