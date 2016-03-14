package ru.forge.twice_a_day.quickcomparison;

/**
 * Created by twice on 14.03.16.
 */
public class MyRow {
    double price;
    double quantity;
    String unit;
    double result;
    double economy;

    MyRow(String unit){
        price=0;
        quantity=0;
        this.unit=unit;

    }

    public void setPrice(double price) {
        this.price = price;
        operation();
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
        operation();
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setEconomy(double economy) {
        this.economy = economy;
    }

    public double getPrice() {
        return price;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public double getResult() {
        return result;
    }

    public double getEconomy() {
        return economy;
    }

    void operation(){
        try{

        result=price/quantity;}catch(Exception e){result=0;}
    }

}
