package ru.forge.twice_a_day.quickcomparison;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by twice on 16.03.16.
 */
public class DelClickListener implements View.OnClickListener{
    ArrayList<MyRow>rows;
    int position;
    BoxAdapter boxAdapter;

    public DelClickListener(ArrayList<MyRow>rows,int position,BoxAdapter boxAdapter) {
        this.position = position;
        this.rows=rows;
        this.boxAdapter=boxAdapter;
    }

    @Override
    public void onClick(View v) {
        rows.remove(position);
        boxAdapter.notifyDataSetChanged();
    }
}