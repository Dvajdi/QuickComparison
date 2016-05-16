package ru.forge.twice_a_day.quickcomparison;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by twice on 16.05.16.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyHolder> {


    ArrayList<Sale> sales;

    public RVAdapter(ArrayList<Sale> sales) {
        this.sales = sales;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.material_row_3,parent,false);
        MyHolder mh = new MyHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {


        double price=getDoubleFromET(holder.etPrice);
        double quantity=getDoubleFromET(holder.etQuantity);


        sales.get(position).result=price/quantity;
        sales.get(position).economy=price/quantity;


        holder.tvRes.setText(String.valueOf(sales.get(position).result));
        holder.tvEconomy.setText(String.valueOf(sales.get(position).economy));

    }

    double getDoubleFromET(EditText et){
        double d=0;
        try{d= Double.valueOf(et.getText().toString());}catch(NumberFormatException e){d=0;}
        return d;
    }

    @Override
    public int getItemCount() {
        return sales.size();
    }



    public static class MyHolder extends RecyclerView.ViewHolder {

        CardView cv;
        EditText etPrice,etQuantity;
        TextView tvRes,tvEconomy;
        Button btnUnit;

        public MyHolder(View itemView) {
            super(itemView);
            cv=(CardView)itemView.findViewById(R.id.doprow);
            etPrice=(EditText)itemView.findViewById(R.id.et_dop_price);
            etQuantity=(EditText)itemView.findViewById(R.id.et_dop_quantity);
            tvRes=(TextView)itemView.findViewById(R.id.tv_dop_result);
            tvEconomy=(TextView)itemView.findViewById(R.id.tv_dop_economy);
            btnUnit=(Button)itemView.findViewById(R.id.button_dop_unit);
        }


    }

}
