package ru.forge.twice_a_day.quickcomparison;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;

/**
 * Created by twice on 25.05.16.
 */
public class ListUnits extends Activity {
    ListView listViewWeight,listViewCapacity;
    Button btnED;
    String [] unitsWeight;
    String [] unitsCapacity;

    ArrayAdapter <String>arrayAdapterWeight,arrayAdapterCapacity;
   // String[] unitsWeight = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
     //       "Костя", "Игорь", "Анна", "Денис", "Андрей" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.units_list);
        findMyViews();
        initValues();
    }


    void findMyViews(){
        listViewWeight = (ListView)findViewById(R.id.listViewWieght);
        listViewCapacity = (ListView)findViewById(R.id.listViewCapacity);
        btnED = (Button)findViewById(R.id.btnED);
    }

    void initValues(){

        unitsWeight=getResources().getStringArray(R.array.unitCapacity);
        unitsCapacity=getResources().getStringArray(R.array.unitCapacity);


        Log.d("list","length = "+unitsWeight.length);
        for (int i = 0; i <unitsWeight.length ; i++) {
            Log.d("list",unitsWeight[i]);
        }
        arrayAdapterWeight  = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,unitsWeight);
        arrayAdapterCapacity  = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,unitsCapacity);
        listViewCapacity.setAdapter(arrayAdapterCapacity);
        listViewWeight.setAdapter(arrayAdapterWeight);
    }
}
