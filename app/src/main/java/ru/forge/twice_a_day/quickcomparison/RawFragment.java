package ru.forge.twice_a_day.quickcomparison;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by twice on 22.03.16.
 */
public class RawFragment extends Fragment {
    RawFragment(){
        String TAG;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.row,container,false);
        EditText etPrice = (EditText)rootView.findViewById(R.id.et_dop_price);
        EditText etQuantity = (EditText)rootView.findViewById(R.id.et_dop_quantity);

        ((Button)rootView.findViewById(R.id.button_dop_delete)).setOnClickListener(new DeleteRawListener(rootView));

        return rootView;
    }


    class DeleteRawListener implements View.OnClickListener{
        View rootview;
        DeleteRawListener(View rootview){
            this.rootview = rootview;
        };
        @Override
        public void onClick(View v) {
            rootview.setTag("toDel");
        }
    }



}
