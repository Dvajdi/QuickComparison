package ru.forge.twice_a_day.quickcomparison;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import ru.forge.twice_a_day.quickcomparison.about_units.AllUnits;
import ru.forge.twice_a_day.quickcomparison.about_units.UnitsType;


public class ListUnitsActivity extends Activity {
    public Context ctx;
    ListView listViewWeight,listViewCapacity;
    Button btnED;
    String [] unitsWeight;
    String [] unitsCapacity;
    String [] unitsWeightValues;
    String [] unitsCapacityValues;

    ArrayAdapter <String>arrayAdapterWeight,arrayAdapterCapacity;
    static AllUnits allUnits;

    static boolean isFirstChange;

    UnitsType whatShow;

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
        isFirstChange=MainActivity.isFirstChange;

        if(isFirstChange){whatShow=UnitsType.all;}else{Log.d("show","else");whatShow=RawFragment.rawUnitType;}

        allUnits = MainActivity.allUnits;
        switch(whatShow){
            case all: Log.d("show","all");
                listViewCapacity.setVisibility(View.VISIBLE);
                listViewWeight.setVisibility(View.VISIBLE);
                break;
            case ed:Log.d("show","ed");break;
            case weight:Log.d("show","weight");
                listViewCapacity.setVisibility(View.GONE);
                listViewWeight.setVisibility(View.VISIBLE);
                break;
            case capacity:Log.d("show","capacity");
                listViewCapacity.setVisibility(View.VISIBLE);
                listViewWeight.setVisibility(View.GONE);
                break;
        }
        arrayAdapterWeight  = new ArrayAdapter<String>(this,R.layout.unit_raw, allUnits.units_names_weight);
        arrayAdapterCapacity  = new ArrayAdapter<String>(this,R.layout.unit_raw, allUnits.units_names_capacity);



        listViewCapacity.setAdapter(arrayAdapterCapacity);
        listViewWeight.setAdapter(arrayAdapterWeight);
    }

    void setlisteners(){
        listViewWeight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent();
                intent.putExtra("name", allUnits.weight_units[position].getShortName());
                intent.putExtra("value", allUnits.weight_units[position].getValue());
                setResult(RESULT_OK,intent);
                RawFragment.rawUnitType=UnitsType.weight;
                MainActivity.isFirstChange=false;
                finish();
            }
        });

        listViewCapacity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("name", allUnits.capacity_units[position].getShortName());
                intent.putExtra("value", allUnits.capacity_units[position].getValue());
                setResult(RESULT_OK,intent);
                MainActivity.isFirstChange=false;
                RawFragment.rawUnitType=UnitsType.capacity;
                finish();
            }
        });
    }
}
