package ru.forge.twice_a_day.quickcomparison;

import java.util.ArrayList;

/**
 * Created by twice on 23.03.16.
 */
public class myRunnable implements Runnable{
    ArrayList<RawFragment> fragments;

    public myRunnable(ArrayList<RawFragment> fragments) {
        this.fragments = fragments;
    }

    @Override
    public void run() {
        for (int i = 0; i <fragments.size() ; i++) {
            RawFragment rf = fragments.get(i);
            rf.setTVResult();
        }
    }
}
