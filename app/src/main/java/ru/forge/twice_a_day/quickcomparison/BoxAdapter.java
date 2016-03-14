package ru.forge.twice_a_day.quickcomparison;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by twice on 14.03.16.
 */
public class BoxAdapter extends BaseAdapter {

    private Context ctx;
    ArrayList rows;
    LayoutInflater inflater;

    BoxAdapter(Context ctx,ArrayList rows){
        this.ctx=ctx;
        inflater=(LayoutInflater)ctx.getSystemService(ctx.LAYOUT_INFLATER_SERVICE);
        this.rows=rows;
    }

    @Override
    public int getCount() {
        return rows.size();
    }

    @Override
    public Object getItem(int position) {
        return rows.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if(v==null){v=inflater.inflate(R.layout.row,parent,false);}

        MyRow row = getRow(position);

        ((EditText)v.findViewById(R.id.et_dop_price)).setText(row.getPrice()+"");
        ((EditText)v.findViewById(R.id.et_dop_quantity)).setText(row.getQuantity()+"");
        ((Button)v.findViewById(R.id.button_dop_unit)).setText(row.getUnit() + "");
        ((TextView)v.findViewById(R.id.tv_dop_result)).setText(row.getResult() + "");
        ((TextView)v.findViewById(R.id.tv_dop_economy)).setText(row.getEconomy() + "");


        return v;
    }

    public MyRow getRow(int position){
        return (MyRow)getItem(position);
    }

    public ArrayList getRows() {
        return rows;
    }
}
