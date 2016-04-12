package ru.forge.twice_a_day.quickcomparison;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by twice on 12.04.16.
 */




class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_MIN_DISTANCE = 50;
        private static final int SWIPE_MAX_OFF_PATH = 200;
        private static final int SWIPE_THRESHOLD_VELOCITY = 200;
        Fragment fragment;
        ArrayList fragments;

    public SwipeGestureDetector(Fragment fragment,ArrayList fragments) {
        this.fragment = fragment;
        this.fragments=fragments;
    }

    @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            try {

                float diffAbs = Math.abs(e1.getY() - e2.getY());
                float diff = e1.getX() - e2.getX();

                if (diffAbs > SWIPE_MAX_OFF_PATH)
                    return false;

                // Left swipe
                if (diff > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {


                    //onLeftSwipe();

                }
                // Right swipe
                else if (-diff > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {

                    onRightSwipe();

                }
            } catch (Exception e) {
                // Log.e("Home", "Error on gestures");
            }
            return false;
        }

    void onRightSwipe(){
        Log.d("poi","работает");
        removeMySelf();
    }


    void removeMySelf(){
        fragments.remove(fragment);
        fragment.getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
    }
}
