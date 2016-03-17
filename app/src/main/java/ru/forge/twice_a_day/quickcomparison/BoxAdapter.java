package ru.forge.twice_a_day.quickcomparison;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
    ArrayList <MyRow>rows;
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
        if(v==null){v=inflater.inflate(R.layout.row_2,parent,false);}

        MyRow row = getRow(position);

        final Button btn_choose_unit = (Button)v.findViewById(R.id.button_dop_unit);
        final Button btn_dop_delete = (Button)v.findViewById(R.id.button_dop_delete);
        final EditText etPrice= (EditText) v.findViewById(R.id.et_dop_price);
        final EditText etQuantity= (EditText) v.findViewById(R.id.et_dop_quantity);

        ((TextView)v.findViewById(R.id.tv_dop_result)).setText(row.getResult() + "");
        ((TextView)v.findViewById(R.id.tv_dop_economy)).setText(row.getEconomy() + "");

        etPrice.addTextChangedListener(new PriceWatcher(position, rows,this));
        etQuantity.addTextChangedListener(new QuantityWatcher(position,rows,this));


        etPrice.setText(row.getPrice() + "");
        etQuantity.setText(row.getQuantity() + "");
        btn_choose_unit.setText(row.getUnit() + "");

        etPrice.setOnTouchListener(new ClearFieldListener(etPrice));
        etQuantity.setOnTouchListener(new ClearFieldListener(etQuantity));

        btn_choose_unit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("priv", "mmm");
            }
        });

        btn_dop_delete.setOnClickListener(new DelClickListener(rows,position,this));

        return v;
    }


    public MyRow getRow(int position){
        return (MyRow)getItem(position);
    }

    public ArrayList getRows() {return rows;}

}
