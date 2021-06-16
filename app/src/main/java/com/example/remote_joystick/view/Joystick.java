package com.example.remote_joystick.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

abstract public class Joystick extends View {


    public Joystick(Context context) {
        super(context);
    }

    public Joystick(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Joystick(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    abstract public void onChange(double aileron, double elevator);

    @Override
    public void onSizeChanged(int x, int y, int oldX, int oldY) {

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return false;
    }
    @Override
    public void onDraw(Canvas canvas) {

    }

}
