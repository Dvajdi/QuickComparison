package ru.forge.twice_a_day.quickcomparison;

import java.util.ArrayList;

/**
 * Created by Pavel M. on 08.06.16.
 */
public class ThreadChangeUnit implements Runnable {
    ArrayList<RawFragment>rawFragments;

    public ThreadChangeUnit(ArrayList<RawFragment> rawFragments) {
        this.rawFragments = rawFragments;
        run();
    }

    @Override
    public void run() {
        for (int i = 0; i < rawFragments.size(); i++) {

        }
    }
}
