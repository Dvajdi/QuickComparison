package ru.forge.twice_a_day.quickcomparison.about_units;

import android.app.Activity;
import android.widget.Toast;

import ru.forge.twice_a_day.quickcomparison.R;

/**
 * Created by twice on 02.06.16.
 */
public class AllUnits {
    Activity act;

    public Unit[] weight_units;
    public String [] units_names_weight;
    public String [] units_short_names_weight;
    public String [] units_value_weight_str;
    public double [] units_value_weight;

    public Unit[] capacity_units;
    public String [] units_names_capacity;
    public String [] units_short_names_capacity;
    public String [] units_value_capacity_str;
    public double [] units_value_capacity;

    public AllUnits(Activity act) {
        this.act = act;
        loadResources();
        initArrays();
        initUnits();
    }

    void loadResources(){
        units_names_weight=act.getResources().getStringArray(R.array.unit_weight_names);
        units_short_names_weight=act.getResources().getStringArray(R.array.unit_weight_short_names);
        units_value_weight_str =act.getResources().getStringArray(R.array.unit_weight_value);

        units_names_capacity=act.getResources().getStringArray(R.array.unit_capacity_names);
        units_short_names_capacity=act.getResources().getStringArray(R.array.unit_capacity_short_names);
        units_value_capacity_str =act.getResources().getStringArray(R.array.unit_capacity_values);

    }

    void initArrays(){
        weight_units = new Unit [units_names_weight.length];
        capacity_units = new Unit [units_names_capacity.length];

        units_value_weight=doubleArFromStringAr(units_value_weight_str);
        units_value_capacity=doubleArFromStringAr(units_value_weight_str);
    }

    void initUnits(){
        for (int i = 0; i < weight_units.length; i++) {
             weight_units[i]=new Unit(units_names_weight[i],units_short_names_weight[i], units_value_weight[i],true,false);
             capacity_units[i]=new Unit(units_names_capacity[i],units_short_names_capacity[i], units_value_capacity[i],false,true);
        }
    }

    private double[] doubleArFromStringAr(String [] str){
        double [] doubleValues = new double [str.length];
        for (int i = 0; i < doubleValues.length; i++) {
            try{
             doubleValues[i]= Double.valueOf(str[i]);}catch(Exception e){
                Toast.makeText(act,"проблемы с файлом ресурсов",Toast.LENGTH_SHORT).show();act.finish();}
        }
        return doubleValues;
    }

}
