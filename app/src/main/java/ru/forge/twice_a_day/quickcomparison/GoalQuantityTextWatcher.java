package ru.forge.twice_a_day.quickcomparison;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import ru.forge.twice_a_day.quickcomparison.helpers.StaticNeedSupplement;

/**
 * Created by twice on 24.05.16.
 */
public class GoalQuantityTextWatcher implements TextWatcher {
    EditText etGoalQuantity;
    double goalQuantity;

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        goalQuantity= StaticNeedSupplement.getDoubleFromET(etGoalQuantity);
    }

    public GoalQuantityTextWatcher(EditText etGoalQuantity) {
        this.etGoalQuantity = etGoalQuantity;
        etGoalQuantity.addTextChangedListener(this);
    }

    public double getGoalQuantity() {
        return goalQuantity;
    }
}
