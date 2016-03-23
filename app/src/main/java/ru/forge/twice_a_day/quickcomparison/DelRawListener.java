package ru.forge.twice_a_day.quickcomparison;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by twice on 22.03.16.
 */
public class DelRawListener implements View.OnClickListener {
    ArrayList<RawFragment> fragments;
    Fragment fragment;

    DelRawListener(Fragment fragment, ArrayList fragments) {
        this.fragment = fragment;
        this.fragments = fragments;
    }


    @Override
    public void onClick(View v) {
        fragment.getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        int number = 0;
        for (int i = 0; i < fragments.size(); i++) {
            if (fragments.get(i).equals(fragment)) {
                number = i;
            }
        }
        fragments.remove(number);
    }

}
