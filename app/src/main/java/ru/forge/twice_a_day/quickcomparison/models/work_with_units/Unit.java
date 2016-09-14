package ru.forge.twice_a_day.quickcomparison.models.work_with_units;

/**
 * Created by twice on 02.06.16.
 */
public class Unit {
    String name;
    String shortName;
    double value;
    boolean isWeight;
    boolean isCapacity;

    public Unit(String name, String shortName, double value, boolean isWeight, boolean isCapacity) {
        this.name = name;
        this.shortName = shortName;
        this.value = value;
        this.isWeight = isWeight;
        this.isCapacity = isCapacity;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public double getValue() {
        return value;
    }

    public boolean isWeight() {
        return isWeight;
    }

    public boolean isCapacity() {
        return isCapacity;
    }
}
