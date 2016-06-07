package ru.forge.twice_a_day.quickcomparison;

import ru.forge.twice_a_day.quickcomparison.about_units.Units;

/**
 * Created by twice on 25.05.16.
 */
public class Converter {
    Units masterUnit,slaveUnit;

    double value;

    public Converter(Units masterUnit, Units slaveUnit,double value) {
        this.masterUnit = masterUnit;
        this.slaveUnit = slaveUnit;
        this.value = value;
    }


    protected double getKoef(Units unit){
        double koef = 0;

        switch (unit){
            case kg:koef=1;break;
            case g:koef = 0.001;break;
            case t:koef = 1000;break;
        }
        return koef;
    }

    public double convert(){
        double conValue=0;
        conValue = value*getKoef(slaveUnit)/getKoef(masterUnit);
        return conValue;
    }

    public void setMasterUnit(Units masterUnit) {
        this.masterUnit = masterUnit;
    }

    public void setSlaveUnit(Units slaveUnit) {
        this.slaveUnit = slaveUnit;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
