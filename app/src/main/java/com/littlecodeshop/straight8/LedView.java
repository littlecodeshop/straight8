package com.littlecodeshop.straight8;

import android.view.View;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;

/**
 * Created by rribier on 01/12/2015.
 */
public class LedView extends View {

    //circle and text colors
    private int circleCol, labelCol;
    //label text
    private String circleText;
    //paint for drawing custom view
    private Paint circlePaint;
    //number of leds
    private int numberLeds;
    //the value to display
    private int value;



    public LedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //paint object for drawing in onDraw
        circlePaint = new Paint();
        //get the attributes specified in attrs.xml using the name we included
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.LedView, 0, 0);
        try {
            //get the text and colors specified using the names in attrs.xml
            circleText = a.getString(R.styleable.LedView_circleLabel);
            circleCol = a.getInteger(R.styleable.LedView_circleColor, 0);//0 is default
            labelCol = a.getInteger(R.styleable.LedView_labelColor, 0);
            numberLeds = a.getInteger(R.styleable.LedView_numberLeds,8);
            value = a.getInteger(R.styleable.LedView_value,170);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //draw the View


        int viewHeightHalf = this.getMeasuredHeight()/2;
        //get the radius as half of the width or height, whichever is smaller
        //subtract ten so that it has some space around it

        circlePaint.setStyle(Style.FILL);
        circlePaint.setAntiAlias(true);

        //set the paint color using the circle color specified
        circlePaint.setColor(circleCol);

        int widthofled = this.getMeasuredWidth() / this.numberLeds;
        int ledradius = widthofled/2;

        for (int i = 0; i < this.numberLeds ; i++) {
            if(((value>>((numberLeds-1)-i))&0x1)==1){
                circlePaint.setColor(circleCol);
            }
            else{
                circlePaint.setColor(labelCol);
            }
            //draw each led !
            canvas.drawCircle((widthofled/2)+(i*widthofled),viewHeightHalf,ledradius,circlePaint);
        }

        //set the text color using the color specified
        //circlePaint.setColor(labelCol);
        //set text properties
        //circlePaint.setTextAlign(Paint.Align.CENTER);
        //circlePaint.setTextSize(50);
        //draw the text using the string attribute and chosen properties
        //canvas.drawText(circleText, viewWidthHalf, viewHeightHalf, circlePaint);

    }

    public int getValue(){
        return this.value;
    }

    public void setValue(int newValue){
        this.value = newValue;
        //redraw the view
        this.invalidate();
        this.requestLayout(); //on a besoin de ca ??
    }

}
