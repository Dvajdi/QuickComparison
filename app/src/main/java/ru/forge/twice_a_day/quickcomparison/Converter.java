package ru.forge.twice_a_day.quickcomparison;


import android.util.Log;

/**
 * Created by twice on 25.05.16.
 */
public class Converter {
   double masterUnitValue, slaveUnitValue;
    double value;

    public Converter(double masterUnitValue, double slaveUnitValue,double value) {
        this.masterUnitValue = masterUnitValue;
        this.slaveUnitValue = slaveUnitValue;
        this.value = value;
    }


    protected double getKof(){
        double koef = masterUnitValue/slaveUnitValue;
        return koef;
    }

    private double convert(){
        double conValue=0;
        conValue = value*getKof();
            Log.d("convert","value = "+value+"; masterValue = "+masterUnitValue+"; slaveValue = "+slaveUnitValue);
        return conValue;
    }

    public void setMasterUnit(double masterUnitValue) {
        this.masterUnitValue = masterUnitValue;
    }

    public void setSlaveUnit(double slaveUnitValue) {
        this.slaveUnitValue = slaveUnitValue;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getConvertValue(){
        return convert();
    }
}
