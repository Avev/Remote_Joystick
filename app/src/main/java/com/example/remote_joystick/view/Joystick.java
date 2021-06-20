package com.example.remote_joystick.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Joystick extends View {

    public OnJoystickChange onMove;
    private float x;
    private float y;
    private float radius;
    private Paint paint = new Paint();

    public Joystick(Context context) {
        super(context);
    }

    public Joystick(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Joystick(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        onMove.onChange(x,y);
        invalidate();
        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, paint);
    }

    @Override
    public void onSizeChanged(int x, int y, int oldX, int oldY) {

    }
}
