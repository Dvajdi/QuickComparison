package ru.forge.twice_a_day.quickcomparison;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
    MyHolder mh;

    EditText etPrice,etQuantity;
    TextView tvRes,tvEconomy;

    public RVAdapter(ArrayList<Sale> sales) {
        this.sales = sales;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.material_row_3,parent,false);
        mh = new MyHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        holder.etQuantity.setText(String.valueOf(sales.get(position).quantity));
        holder.etPrice.setText(String.valueOf(sales.get(position).price));
        holder.tvRes.setText(String.valueOf(sales.get(position).result));
        holder.tvEconomy.setText(String.valueOf(sales.get(position).economy));

        etPrice=holder.etPrice;
        etQuantity=holder.etQuantity;
        tvRes=holder.tvRes;
        tvEconomy=holder.tvEconomy;



        etPrice.addTextChangedListener(new PriceWatcher(tvRes,tvEconomy,etPrice,etQuantity,position));
        etQuantity.addTextChangedListener(new PriceWatcher(tvRes,tvEconomy,etPrice,etQuantity,position));



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


    public class PriceWatcher implements TextWatcher{
        double price,quantity,result;
        TextView tvRes,tvEconomy;
        EditText etPrice,etQuantity;
        int position;

        public PriceWatcher(TextView tvRes, TextView tvEconomy, EditText etPrice, EditText etQuantity,int position) {
            this.tvRes = tvRes;
            this.tvEconomy = tvEconomy;
            this.etPrice = etPrice;
            this.etQuantity = etQuantity;
            this.position=position;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            price = getDoubleFromET(etPrice);
            quantity=getDoubleFromET(etQuantity);

            /*sales.get(position).price=price;
            sales.get(position).quantity=quantity;
            sales.get(position).result=price/quantity;*/
            tvRes.setText(""+price/quantity);
            for(Sale sale:sales){Log.d("watcher","position = "+position +" : "+sale.price);}
        }
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
