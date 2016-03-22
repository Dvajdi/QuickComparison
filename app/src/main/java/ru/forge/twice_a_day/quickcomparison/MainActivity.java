package ru.forge.twice_a_day.quickcomparison;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
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
    Bundle save;

    static ArrayList <MyRow>rows;
    ArrayList<RawFragment> rawFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findMyViews();

        rawFragments = new ArrayList<>();

        if(savedInstanceState==null){setContent();}
        else {button_add.setOnClickListener(this);
            button_clear.setOnClickListener(this);}

    }

    public void findMyViews() {
        button_add=(Button)findViewById(R.id.button_add);
        button_clear=(Button)findViewById(R.id.button_clear);
        rl_main = (LinearLayout) findViewById(R.id.rl_main);

    }

    void createRow(){
        rows.add(new MyRow("ед."));
        addNewFragment();

    }
    void createStartRows(){
        createRow();
        createRow();
        Log.d("priv", "пересоздался");
    }

    void setContent(){
        rows = new ArrayList<>();
        createStartRows();
        button_add.setOnClickListener(this);
        button_clear.setOnClickListener(this);
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
                Log.d("priv", "add кликается");
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
        RawFragment rf = new RawFragment();
        ft=getSupportFragmentManager().beginTransaction();
        ft.add(R.id.rl_main, rf, "del");
        ft.commit();
       /* Button btnDel =rf.getBtnDel();
        btnDel.setOnClickListener(this);*/
        rawFragments.add(rf);
    }

    private void clearFragments(){
        for (int i = 0; i <rawFragments.size() ; i++) {
            ft=getSupportFragmentManager().beginTransaction();
            ft.remove(rawFragments.get(i));
            ft.commit();
        }

    }


}
