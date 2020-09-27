package com.example.base_common_lib.ui.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 作者：G_JS
 * 时间：
 */
public class My_Circle_View extends View {
    public My_Circle_View(Context context) {
        super(context);
    }

    public My_Circle_View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public My_Circle_View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //    重写draw方法
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        //    定义画笔
        Paint paint;
//        实例化画笔对象
        paint = new Paint();
//        给画笔设置颜色
        paint.setColor(Color.RED);
//        设置画笔属性
//        paint.setStyle(Paint.Style.FILL);//画笔属性是实心圆
        paint.setStyle(Paint.Style.STROKE);//画笔属性是空心圆
        paint.setStrokeWidth(8);//设置画笔粗细

        /*四个参数：
                参数一：圆心的x坐标
                参数二：圆心的y坐标
                参数三：圆的半径
                参数四：定义好的画笔
                */
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, (getWidth() / 2)-20, paint);

    }


}
