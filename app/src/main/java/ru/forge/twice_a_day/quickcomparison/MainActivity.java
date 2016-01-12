package ru.forge.twice_a_day.quickcomparison;

import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    EditText et_price1, et_quantity1;
    TextView tv_number1, tv_price_unit1, tv_percent1;
    Spinner spinner_u1;
    LinearLayout rl_main;
    LinearLayout row2;
    int [] forId;

    static int i;
    boolean et_price1ChangedAndGreaterThanZero, et_quantity1ChangedAndGreaterThanZero;
    double quantity1, price1;
    ArrayList<LinearLayout> collectionOfLinearLayot;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findMyViews();
        setContent();
        setListeners();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void findMyViews() {
        et_price1 = (EditText) findViewById(R.id.et_price1);
        et_quantity1 = (EditText) findViewById(R.id.et_quantity1);
        tv_number1 = (TextView) findViewById(R.id.tv_number1);
        tv_price_unit1 = (TextView) findViewById(R.id.tv_price_unit1);
        tv_percent1 = (TextView) findViewById(R.id.tv_percent1);
        spinner_u1 = (Spinner) findViewById(R.id.spinner_u1);
        rl_main = (LinearLayout) findViewById(R.id.rl_main);
        row2 = (LinearLayout) findViewById(R.id.row2);
    }

    public void setContent() {
        collectionOfLinearLayot = new ArrayList<>();

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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://ru.forge.twice_a_day.quickcomparison/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://ru.forge.twice_a_day.quickcomparison/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
