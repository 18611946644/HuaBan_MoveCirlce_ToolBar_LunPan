package com.bwie.lx02_huaban.movepeople.myview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.bwie.lx02_huaban.R;

/**
 * date:2018/11/3
 * author:张自力(DELL)
 * function: 自定义View  移动的人儿
 */

public class MyView extends View {

    //定义两个变量确定初始位置
    public float PX;
    public float PY;
    public float X;
    public float Y;
    private int mHeight;
    private int mWidth;
    private boolean mOnBall;
    private Paint mPaint;

    public MyView(Context context) {
        this(context,null);
        //设置初始值 宽高
        PX = 200;
        PY = 300;
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();//初始化
    }

    //1 初始化数据
    private void initView() {
        //定义画笔
        mPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取屏幕宽高
        mHeight = this.getHeight();
        mWidth = this.getWidth();
        //获取屏幕中心
        //初始位置在中心
        X = mWidth/2;
        Y = mHeight/2;
        //在屏幕中心点画图
    }

    //重写onDrow方法
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //找到原图
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.haha);
        canvas.drawBitmap(bitmap,X,Y,mPaint);

        //判断回收
        if(bitmap.isRecycled()){
            //如果是可循环的
            bitmap.recycle();
        }
    }

    //手势监听器

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //得到按下的坐标
                int downX = (int)event.getX();
                int downY = (int)event.getY();
                
                //定义一个方法  判断是否在图片上
                mOnBall = isOnBall(downX, downY);

                break;

            case MotionEvent.ACTION_MOVE:
               // if(mOnBall){
                    X = event.getX();
                    Y = event.getY();
                    postInvalidate();
              //  }
                break;

            case MotionEvent.ACTION_UP:

                break;
        }

        return true;
    }

    private boolean isOnBall(int downX, int downY) {
//勾股定理的绝对值
        double sqrt = Math.sqrt((downX - X) * (downX - X) + (downY - Y) * (downY - Y));
        //判断绝对值是否小于等于宽的一般  大于等于高的一半
        if(sqrt < Math.sqrt((200/2) * (200/2) + (300/2) * (300/2))){
            return true;
        }

        return false;
    }
}
