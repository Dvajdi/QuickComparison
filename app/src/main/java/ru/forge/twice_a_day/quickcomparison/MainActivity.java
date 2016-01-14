package ru.forge.twice_a_day.quickcomparison;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    EditText et_price1, et_quantity1,et_price2, et_quantity2;
    TextView tv_price_unit1, tv_price_unit2;
    Button button_unit1,button_unit2,button_add,button_clear;
    Button iButton_delete1,iButton_delete2;

    LinearLayout rl_main;
    LinearLayout row2,doprow;

    static int firstId, secondId;

    static int i;
    boolean et_price1ChangedAndGreaterThanZero, et_quantity1ChangedAndGreaterThanZero;
    double quantity1, price1;




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
        et_price2 = (EditText) findViewById(R.id.et_price2);
        et_quantity2 = (EditText) findViewById(R.id.et_quantity2);
        tv_price_unit1 =(TextView)findViewById(R.id.tv_price_unit1);
        tv_price_unit2 =(TextView)findViewById(R.id.tv_price_unit2);
        button_unit1=(Button)findViewById(R.id.button_unit1);
        iButton_delete1=(Button)findViewById(R.id.button_delete1);
        button_unit2=(Button)findViewById(R.id.button_unit2);
        iButton_delete2=(Button)findViewById(R.id.button_delete2);
        button_add=(Button)findViewById(R.id.button_add);
        button_clear=(Button)findViewById(R.id.button_clear);
        rl_main = (LinearLayout) findViewById(R.id.rl_main);

        row2=(LinearLayout)findViewById(R.id.row2);
    }

    public void setContent() {


    }

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

        Log.d("priv", "id = " + llNew.getId());
        Log.d("priv", "R.layout.row = " + R.layout.row);

        rl_main.addView(llNew, lp);
    }

    public void setListeners() {
        et_quantity1.addTextChangedListener(watcher);
        et_price1.addTextChangedListener(watcher);
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
            makeNewRow();
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

            go();


        }
    };


}
