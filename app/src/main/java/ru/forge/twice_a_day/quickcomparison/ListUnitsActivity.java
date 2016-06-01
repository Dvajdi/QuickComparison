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


public class ListUnitsActivity extends Activity {
    public Context ctx;
    ListView listViewWeight,listViewCapacity;
    Button btnED;
    String [] unitsWeight;
    String [] unitsCapacity;
    String [] unitsWeightValues;
    String [] unitsCapacityValues;

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
        unitsWeightValues=getResources().getStringArray(R.array.unit_weight_value);
        unitsCapacityValues=getResources().getStringArray(R.array.unit_capacity_value);

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
                intent.putExtra("name",unitsWeightValues[position]);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        listViewCapacity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("name",unitsCapacityValues[position]);
                Log.d("fragments",""+RESULT_OK);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
