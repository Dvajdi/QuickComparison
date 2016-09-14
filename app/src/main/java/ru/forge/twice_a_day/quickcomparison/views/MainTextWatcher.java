package ru.forge.twice_a_day.quickcomparison.views;

import android.text.Editable;
import android.text.TextWatcher;

public class MainTextWatcher implements TextWatcher {
    MainActivity activity;

    public MainTextWatcher(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        activity.startThread();
    }
}
