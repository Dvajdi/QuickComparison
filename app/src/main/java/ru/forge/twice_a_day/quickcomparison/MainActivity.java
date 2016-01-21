package ru.forge.twice_a_day.quickcomparison;

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

    ArrayList<LinearLayout> allDopLinearLayout;
    ArrayList<TextView> textViewForClear;


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
        et_quantity1 = (EditText) findViewById(R.id.et_quantity1);
        tv_price_unit1 =(TextView)findViewById(R.id.tv_price_unit1);
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



        row2=(LinearLayout)findViewById(R.id.row2);
    }

    public void setContent() {

        allDopLinearLayout=new ArrayList<LinearLayout>();
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

        Log.d("priv", "id = " + llNew.getId());
        Log.d("priv", "R.layout.row = " + R.layout.row);
        allDopLinearLayout.add(llNew);
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

        et_quantity1.addTextChangedListener(watcher);
        et_price1.addTextChangedListener(watcher);

        button_add.setOnClickListener(onClickListener);
        button_clear.setOnClickListener(onClickListener);


    }

    public void setListeners() {
        et_quantity1.addTextChangedListener(watcher);
        et_price1.addTextChangedListener(watcher);

        button_add.setOnClickListener(onClickListener);
        button_clear.setOnClickListener(onClickListener);
    }

    private void go() {

        try {
            quantity1 = Double.valueOf(et_quantity1.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            quantity1 = 0;
        }

        try {
            price1 = Double.valueOf(et_price1.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            price1 = 0;
        }
        Log.d("priv", price1 + "");
        Log.d("priv", quantity1 + "");

        if (quantity1 > 0 && price1 > 0) {
            tv_price_unit1.setText(price1 / quantity1 + "");

        } else {
            tv_price_unit1.setText("");
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
            Log.d("priv2","watcher работает");

            go();
        }
    };

void clear(){
    for(LinearLayout oneLinear:allDopLinearLayout){rl_main.removeView(oneLinear);};
    for(TextView textView:textViewForClear){textView.setText("");};

}

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        switch(v.getId()){
            case  R.id.button_add:  makeNewRow(); Log.d("priv", "Размер = " + allDopLinearLayout.size() + "");break;
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
