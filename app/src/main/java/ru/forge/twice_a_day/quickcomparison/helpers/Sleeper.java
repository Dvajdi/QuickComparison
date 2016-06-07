package ru.forge.twice_a_day.quickcomparison.helpers;

import java.util.concurrent.TimeUnit;


public class Sleeper {
    public static void sleep(long time){
        try{
            TimeUnit.MILLISECONDS.sleep(time);
        }catch(InterruptedException e){e.printStackTrace();}
    }
}
