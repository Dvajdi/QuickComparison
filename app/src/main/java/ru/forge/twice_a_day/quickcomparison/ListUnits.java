package ru.forge.twice_a_day.quickcomparison;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;


public class ListUnits extends Activity {
    public Context ctx;
    ListView listViewWeight,listViewCapacity;
    Button btnED;
    String [] unitsWeight;
    String [] unitsCapacity;

    ArrayAdapter <String>arrayAdapterWeight,arrayAdapterCapacity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.units_list);
        findMyViews();
        initValues();
        setlisteners();
    }


    void findMyViews(){

        listViewWeight = (ListView)findViewById(R.id.listViewWieght);
        listViewCapacity = (ListView)findViewById(R.id.listViewCapacity);
        btnED = (Button)findViewById(R.id.btnED);

    }

    void initValues(){
        ctx=this;
        unitsWeight=getResources().getStringArray(R.array.unit_weight);
        unitsCapacity=getResources().getStringArray(R.array.unit_capacity);

        arrayAdapterWeight  = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,unitsWeight);
        arrayAdapterCapacity  = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,unitsCapacity);
        listViewCapacity.setAdapter(arrayAdapterCapacity);
        listViewWeight.setAdapter(arrayAdapterWeight);
    }

    void setlisteners(){
        listViewWeight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent();
                intent.putExtra("name",unitsWeight[position]);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        listViewCapacity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("name",unitsCapacity[position]);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
