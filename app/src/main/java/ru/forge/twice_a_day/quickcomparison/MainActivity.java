package ru.forge.twice_a_day.quickcomparison;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    android.support.v4.app.FragmentTransaction ft;
    Button button_add,button_clear;
    LinearLayout rl_main;

    static ArrayList <MyRow>rows;
    static ArrayList<RawFragment> rawFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findMyViews();
        setListeners();

        if(rawFragments==null){rawFragments = new ArrayList<>();}
        if(savedInstanceState==null){setContent();}


    }

    public void findMyViews() {
        button_add=(Button)findViewById(R.id.button_add);
        button_clear=(Button)findViewById(R.id.button_clear);
        rl_main = (LinearLayout) findViewById(R.id.rl_main);
    }
    private void setListeners(){
        button_add.setOnClickListener(this);
        button_clear.setOnClickListener(this);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) {

        }
        if (item.getItemId() == R.id.remove) {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_add:
                createRow();
                break;
            case R.id.button_clear:
                clearFragments();
                rows.clear();
                createStartRows();
                break;
            default:
                Toast.makeText(this,"отстань",Toast.LENGTH_SHORT).show();
        }
    }

    private void addNewFragment() {
        RawFragment rf = new RawFragment(rawFragments);
        ft=getSupportFragmentManager().beginTransaction();
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
        Log.d("dom", "кол-во фрагментов = " + rawFragments.size());
        rawFragments.clear();
    }



}
