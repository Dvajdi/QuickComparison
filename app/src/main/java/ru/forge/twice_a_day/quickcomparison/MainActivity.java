package ru.forge.twice_a_day.quickcomparison;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    EditText et_price1, et_quantity1,et_price2, et_quantity2;
    TextView tv_price_unit1, tv_price_unit2,tv_economy1,tv_economy2;
    Button button_unit1,button_unit2,button_add,button_clear;
    Button iButton_delete1,iButton_delete2;


    LinearLayout rl_main;
    LinearLayout row2,doprow;

    static int firstId, secondId;

    static int i;
    boolean et_price1ChangedAndGreaterThanZero, et_quantity1ChangedAndGreaterThanZero;
    double quantity1, price1;
    double minRezult,secondMinResult;

    ArrayList<LinearLayout> allLinearLayouts;
    ArrayList<Integer> idDopLinearLayouts;
    ArrayList<TextView> textViewForClear;
    ArrayList<Double> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findMyViews();
        setContent();
        setListeners();

    }

    public void findMyViews() {
        et_price1 = (EditText) findViewById(R.id.et_price1);
        et_price1.setTag("price");
        et_quantity1 = (EditText) findViewById(R.id.et_quantity1);
        et_quantity1.setTag("quantity");
        tv_price_unit1 =(TextView)findViewById(R.id.tv_price_unit1);
        tv_price_unit1.setTag("price_unit");
        tv_economy1 =(TextView)findViewById(R.id.tv_economy1);
        button_unit1=(Button)findViewById(R.id.button_unit1);
        iButton_delete1=(Button)findViewById(R.id.button_delete1);

        et_price2 = (EditText) findViewById(R.id.et_price2);
        et_quantity2 = (EditText) findViewById(R.id.et_quantity2);
        tv_price_unit2 =(TextView)findViewById(R.id.tv_price_unit2);
        tv_economy2 =(TextView)findViewById(R.id.tv_economy2);
        button_unit2=(Button)findViewById(R.id.button_unit2);
        iButton_delete2=(Button)findViewById(R.id.button_delete2);
        button_add=(Button)findViewById(R.id.button_add);
        button_clear=(Button)findViewById(R.id.button_clear);
        rl_main = (LinearLayout) findViewById(R.id.rl_main);
        et_price2.setTag("price");
        et_quantity2.setTag("quantity");
        tv_price_unit2.setTag("price_unit");
        row2=(LinearLayout)findViewById(R.id.row2);
    }

    public void setContent() {
        results=new ArrayList<>();
        allLinearLayouts =new ArrayList<LinearLayout>();
        allLinearLayouts.add((LinearLayout)findViewById(R.id.row2));
        allLinearLayouts.add((LinearLayout)findViewById(R.id.row3));

        textViewForClear =new ArrayList<>();
        textViewForClear.add(et_price1);
        textViewForClear.add(et_quantity1);
        textViewForClear.add(tv_price_unit1);
        textViewForClear.add(tv_economy1);
        textViewForClear.add(et_price2);
        textViewForClear.add(et_quantity2);
        textViewForClear.add(tv_price_unit2);
        textViewForClear.add(tv_economy2);
    }

    private void removeRow() {
        LinearLayout delLinLay = (LinearLayout)findViewById(i);
        rl_main.removeView(delLinLay);
        i--;
    }

    private void makeNewRow() {
        i++;
        ViewGroup.LayoutParams lp = row2.getLayoutParams();
        LinearLayout llNew = (LinearLayout) LinearLayout.inflate(this, R.layout.row, null);
        llNew.setId(i);

        findMyViewsInNewRowAndSetListeners(llNew);

        allLinearLayouts.add(llNew);
        rl_main.addView(llNew, lp);
    }

    private void findMyViewsInNewRowAndSetListeners(LinearLayout llNew) {
        et_price1 = (EditText) llNew.findViewById(R.id.et_dop_price);
        et_quantity1 = (EditText) llNew.findViewById(R.id.et_dop_quantity);
        tv_price_unit1 =(TextView)llNew.findViewById(R.id.tv_dop_resalt);
        tv_economy1 =(TextView)llNew.findViewById(R.id.tv_dop_economy);
        button_unit1=(Button)llNew.findViewById(R.id.button_dop_unit);
        iButton_delete1=(Button)llNew.findViewById(R.id.button_dop_delete);

        et_price1.setId(Integer.valueOf(i+""+1));
        et_quantity1.setId(Integer.valueOf(i+""+2));
        tv_price_unit1.setId(Integer.valueOf(i + "" + 3));
        tv_economy1.setId(Integer.valueOf(i + "" + 4));
        button_unit1.setId(Integer.valueOf(i + "" + 5));
        iButton_delete1.setId(Integer.valueOf(i + "" + 6));

        et_price1.setTag("price");
        et_quantity1.setTag("quantity");
        tv_price_unit1.setTag("price_unit");

        et_quantity1.addTextChangedListener(watcher);
        et_price1.addTextChangedListener(watcher);

        button_add.setOnClickListener(onClickListener);
        button_clear.setOnClickListener(onClickListener);


    }

    public void setListeners() {
        et_quantity1.addTextChangedListener(watcher);
        et_price1.addTextChangedListener(watcher);

        et_quantity2.addTextChangedListener(watcher);
        et_price2.addTextChangedListener(watcher);

        button_add.setOnClickListener(onClickListener);
        button_clear.setOnClickListener(onClickListener);
    }

    private void go() {
        double []mins = new double[2];
        for(LinearLayout linearLayout: allLinearLayouts) {

            EditText et_quantity = (EditText) linearLayout.findViewWithTag("quantity");
            EditText et_price = (EditText) linearLayout.findViewWithTag("price");
            TextView tv_price_unit = (TextView) linearLayout.findViewWithTag("price_unit");

            try {
                quantity1 = Double.valueOf(et_quantity.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
                quantity1 = 0;
            }

            try {
                price1 = Double.valueOf(et_price.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
                price1 = 0;
            }

            if (quantity1 > 0 && price1 > 0) {
                int j = (int)((price1/quantity1)*100);
                double rez = (double)j/100;
                results.add(rez);
                mins=getMinimum(results);
                minRezult=mins[0];
                secondMinResult=mins[1];
                tv_price_unit.setText(rez + "");
            Log.d("priv","minRezult = "+minRezult);
            } else {
                tv_price_unit.setText("");
            }
        }
        paintBackGround(allLinearLayouts);
    }

    public static double [] getMinimum(ArrayList<Double> values1){
        ArrayList<Double>values = new ArrayList<>();
        values.addAll(values1);
        if (values.isEmpty()) return null;
        Double min = Double.MAX_VALUE;

        double [] mins=new double[2];

        for(Double value : values){
            if (min.compareTo(value)==1){
                min = value;
            }
        }
        mins[0]=min;

        for (int i = 0; i <values.size() ; i++) {
            if(values.get(i)==min){values.remove(i);}
        }

        min = Double.MAX_VALUE;
        for(Double value : values){
            if (min.compareTo(value)==1){
                min = value;
            }
        }
        mins[1]=min;

        return mins;
    }

    void paintBackGround(ArrayList<LinearLayout> allLinearLayouts) {
        double value = 0;

        for (LinearLayout oneOfLayouts : allLinearLayouts) {
            TextView tv = (TextView) oneOfLayouts.findViewWithTag("price_unit");

            try {
                value = Double.valueOf(tv.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
                value = Double.MAX_VALUE;
            }

            if (value == minRezult) {
                Log.d("priv2","minRezult = "+minRezult);
                oneOfLayouts.setBackgroundColor(Color.GREEN);
            }
            if (value == secondMinResult) {
                Log.d("priv2","secondMinResult = "+minRezult);
                oneOfLayouts.setBackgroundColor(Color.YELLOW);
            }
            if (value != minRezult || value != secondMinResult) {
                oneOfLayouts.setBackgroundColor(getResources().getColor(R.color.background_main_activity));
            }

        }
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

void clear(){
    for(LinearLayout oneLinear: allLinearLayouts){
        switch(oneLinear.getId()){
            case R.id.row2:break;
            case R.id.row3:break;
            default:
                rl_main.removeView(oneLinear);}};
    for(TextView textView:textViewForClear){textView.setText("");};

}

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        switch(v.getId()){
            case  R.id.button_add:  makeNewRow();break;
            case  R.id.button_clear: clear();break;
        }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) {
            makeNewRow();
        }
        if (item.getItemId() == R.id.remove) {
            removeRow();
        }
        return super.onOptionsItemSelected(item);
    }

}
