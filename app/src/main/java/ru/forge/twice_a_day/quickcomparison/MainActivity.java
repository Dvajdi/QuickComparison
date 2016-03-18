package ru.forge.twice_a_day.quickcomparison;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    Button button_add,button_clear;
    LinearLayout rl_main;
    ListView listView;

    ArrayList <MyRow>rows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findMyViews();
        setContent();

    }

    public void findMyViews() {
        button_add=(Button)findViewById(R.id.button_add);
        button_clear=(Button)findViewById(R.id.button_clear);
        rl_main = (LinearLayout) findViewById(R.id.rl_main);
        listView=(ListView)findViewById(R.id.listView);

    }

    void createRow(){
        rows.add(new MyRow("ед."));
        Log.d("priv", "ага");

    }
    void createStartRows(){
        createRow();
        createRow();
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
                rows.add(new MyRow("ед"));

                break;
            case R.id.button_clear:
                rows.clear();
                createStartRows();

                break;
        }
    }
}
