package com.example.remote_joystick.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Joystick extends View {

    // field
    public OnJoystickChange onMove;
    private float baseX;
    private float baseY;
    private float x;
    private float y;
    private float radius;
    private float baseRadius;
    private Paint paint;
    private boolean isInit;

    // constructors
    public Joystick(Context context) {
        super(context);
    }

    public Joystick(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Joystick(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * initialize joystick field
     */
    private void initJoystick() {
        baseX = (float)getWidth() / 2;
        baseY = (float)getHeight() / 2;
        x = baseX;
        y = baseY;
        radius = 200;
        baseRadius = 400;
        paint = new Paint();
        isInit = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        int xPos = (int) event.getX();
        int yPos = (int) event.getY();
        int action = event.getAction();

        // if user moves the joystick
        if (action == MotionEvent.ACTION_MOVE) {
            float dev = (float)Math.sqrt(Math.pow(xPos - baseX, 2) +
                    Math.pow(yPos - baseY, 2));

            // if the circle is within the bigger circles boundaries
            if (dev < baseRadius - radius) {
                x = xPos;
                y = yPos;
            }

            // if not put the circle in the closest valid place
            else {
                float ratio = radius / dev;
                x=baseX + (xPos - baseX)*ratio;
                y=baseY + (yPos - baseY)*ratio;
            }
            if (MainActivity.isConnected) {
                onMove.onChange((x - baseX) / radius, (y - baseY) / radius);
            }

            // if the user is not released the finger from screen
        } else if (action == MotionEvent.ACTION_UP) {
            x = baseX;
            y = baseY;
        }
        invalidate();
        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!isInit) {
            initJoystick();
        }
        paint.reset();

        // draw the bigger base circle
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(baseX, baseY, baseRadius, paint);

        // draw the inner circle (the joystick)
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x, y, radius, paint);
        invalidate();
    }

    @Override
    public void onSizeChanged(int x, int y, int oldX, int oldY) {
        super.onSizeChanged(x, y, oldX, oldX);
    }
}
