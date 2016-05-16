package ru.forge.twice_a_day.quickcomparison;

/**
 * Created by twice on 16.05.16.
 */
public class Sale {
    public double price;
    public double quantity;
    public double result;
    public double economy;
    double bestResult;

    public Sale(double price, double quantity) {
        this.price = price;
        this.quantity = quantity;

        if(price>0&&quantity>0) {
            calculateRes();
            calculateEconomy();
        }else{result=0;}
    }

    public void calculateRes(){
      result = price/quantity;


    }

    public void setBestResult(double bestResult) {
        this.bestResult = bestResult;
    }

    public void calculateEconomy(){
        economy = (result-bestResult);

    }
}
