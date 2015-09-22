package com.example.lixinyang.androidbanner_demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by lixinyang on 15/9/22.
 */
public class MyPagerIndicate extends ImageView {
    private Paint bgPaint;
    private int Radius=20;
    private Paint selectedPaint;
    private int currentindex;
    private float perc=0.1f;

    public void setcurrentindex(int currentindex)
    {
        this.currentindex = currentindex;

        this.currentindex %= 5;
    }


    public void Update(float perc)
    {
       this.perc=perc;
        invalidate();

    }

    public MyPagerIndicate(Context context) {
        super(context);
    }

    public MyPagerIndicate(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        bgPaint=new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setColor(Color.BLUE);
        selectedPaint=new Paint();
        selectedPaint.setAntiAlias(true);
        selectedPaint.setColor(Color.RED);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        for (int i=0;i<5;i++)
        {
            canvas.drawCircle(20+i*Radius*3,20,Radius,bgPaint);
        }
        canvas.drawCircle(20+(currentindex + this.perc) * Radius * 3,20,Radius,selectedPaint);
    }
}
