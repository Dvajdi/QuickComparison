package ru.forge.twice_a_day.quickcomparison.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import ru.forge.twice_a_day.quickcomparison.R;
import ru.forge.twice_a_day.quickcomparison.models.work_with_units.AllUnits;
import ru.forge.twice_a_day.quickcomparison.models.work_with_units.Unit;
import ru.forge.twice_a_day.quickcomparison.models.work_with_units.UnitsType;


public class ListUnitsActivity extends AppCompatActivity {
    public Context ctx;
    ListView listViewWeight,listViewCapacity;
    Button btnED;
    ArrayAdapter <String>arrayAdapterWeight,arrayAdapterCapacity;
    static AllUnits allUnits;
    static boolean isFirstChange;
    UnitsType whatShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.material_activity_list);
        findMyViews();
        initValues();
        setListeners();
        setSupportActionBar((Toolbar)findViewById(R.id.tool_bar));
    }

    void findMyViews(){
        listViewWeight = (ListView)findViewById(R.id.listViewWieght);
        listViewCapacity = (ListView)findViewById(R.id.listViewCapacity);
        btnED = (Button)findViewById(R.id.btnED);
    }

    void initValues(){
        ctx=this;
        isFirstChange=MainActivity.isFirstChange;
        if(isFirstChange){whatShow=UnitsType.all;}else{Log.d("show","else");whatShow= RowFragment.rawUnitType;}
        allUnits = MainActivity.allUnits;
        switch(whatShow){
            case all: Log.d("show","all");
                listViewCapacity.setVisibility(View.VISIBLE);
                listViewWeight.setVisibility(View.VISIBLE);
                btnED.setVisibility(View.VISIBLE);
                break;
            case ed:Log.d("show","ed");
                listViewCapacity.setVisibility(View.GONE);
                listViewWeight.setVisibility(View.GONE);
                btnED.setVisibility(View.VISIBLE);
                break;
            case weight:Log.d("show","weight");
                listViewCapacity.setVisibility(View.GONE);
                listViewWeight.setVisibility(View.VISIBLE);
                btnED.setVisibility(View.GONE);
                break;
            case capacity:Log.d("show","capacity");
                listViewCapacity.setVisibility(View.VISIBLE);
                listViewWeight.setVisibility(View.GONE);
                btnED.setVisibility(View.GONE);
                break;
        }
        arrayAdapterWeight  = new ArrayAdapter<>(this,R.layout.unit_raw, allUnits.units_names_weight);
        arrayAdapterCapacity  = new ArrayAdapter<>(this,R.layout.unit_raw, allUnits.units_names_capacity);

        listViewCapacity.setAdapter(arrayAdapterCapacity);
        listViewWeight.setAdapter(arrayAdapterWeight);
    }

    void setListeners(){
        btnED.setOnClickListener((v) -> returnResult(null,1,UnitsType.ed));
        listViewWeight.setOnItemClickListener((parent, view, position, id) ->
              returnResult(allUnits.weight_units,position,UnitsType.weight));
        listViewCapacity.setOnItemClickListener((parent, view, position, id) ->
            returnResult(allUnits.capacity_units,position,UnitsType.capacity));
    }

    protected void returnResult(Unit[] str, int position,UnitsType type){
        Intent intent=new Intent();
        if(str!=null){
        intent.putExtra("name",str[position].getShortName());
        intent.putExtra("value",str[position].getValue());}
        else{
            intent.putExtra("name",allUnits.defaultUnit);
            intent.putExtra("value",1);
        }
        setResult(RESULT_OK,intent);
       if(type!= RowFragment.rawUnitType){MainActivity.isChangeAll=true;}
        MainActivity.isFirstChange=false;
        RowFragment.rawUnitType=type;
        finish();
    }
}
