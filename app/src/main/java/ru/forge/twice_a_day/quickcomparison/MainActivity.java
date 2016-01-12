package ru.forge.twice_a_day.quickcomparison;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText et_price1,et_quantity1;
    TextView tv_number1,tv_price_unit1,tv_percent1;
    Spinner spinner_u1;

    boolean et_price1ChangedAndGreaterThanZero,et_quantity1ChangedAndGreaterThanZero;
    double quantity1,price1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findMyViews();
        setListeners();
    }

    public void findMyViews(){
        et_price1=(EditText)findViewById(R.id.et_price1);
        et_quantity1=(EditText)findViewById(R.id.et_quantity1);
        tv_number1=(TextView)findViewById(R.id.tv_number1);
        tv_price_unit1=(TextView)findViewById(R.id.tv_price_unit1);
        tv_percent1=(TextView)findViewById(R.id.tv_percent1);
        spinner_u1=(Spinner)findViewById(R.id.spinner_u1);

    }

    public void setContent(){

    }

    public void setListeners(){
        et_quantity1.addTextChangedListener(watcher);
        et_price1.addTextChangedListener(watcher);
    }

    private void go(){

        try{
            quantity1= Double.valueOf(et_quantity1.getText().toString());
        }catch(Exception e){e.printStackTrace();quantity1=0;}

        try{
            price1= Double.valueOf(et_price1.getText().toString());
        }catch(Exception e){e.printStackTrace();price1=0;}
            Log.d("priv",price1+"");
            Log.d("priv",quantity1+"");

        if(quantity1>0&&price1>0){
        tv_price_unit1.setText(price1/quantity1+"");}else{tv_price_unit1.setText("");}

    }

TextWatcher watcher = new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

            go();


    }
};

}
