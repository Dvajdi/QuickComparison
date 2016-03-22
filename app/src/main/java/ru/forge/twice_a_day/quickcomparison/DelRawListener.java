package ru.forge.twice_a_day.quickcomparison;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

/**
 * Created by twice on 22.03.16.
 */
public class DelRawListener implements View.OnClickListener {
    Context ctx;
    public DelRawListener(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public void onClick(View v) {
       // v.getRootView().setBackgroundColor(Color.RED);
        Toast.makeText(ctx,"слышу",Toast.LENGTH_SHORT).show();
    }
}
