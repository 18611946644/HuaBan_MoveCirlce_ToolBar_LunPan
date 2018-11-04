package com.bwie.lx02_huaban.fkzhuanpan.myview;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.bwie.lx02_huaban.R;

/**
 * date:2018/11/4
 * author:张自力(DELL)
 * function: 自定义一个View 实现抽奖轮盘
 */

public class MyView extends View implements View.OnClickListener {
    private Paint mStartPaint;
    private Paint mPaint;
    private int mWidth;

    private int mPadding;
    private RectF mRectF;
    private RotateAnimation mRotateAnimation;
    //
    private boolean isStart = false;
    private String str="start";
    private String[] contents = new String[]{"笑傲江湖","倚天屠龙","天龙八部","一剪寒梅","有道云碧","风云再起"};

    //2 实现三个方法
    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //2.2 做初始化
        initPaint();
        //5 设置动画
        initAnim();
        //6 设置监听
        setOnClickListener(this);
    }

    /**
     * //5 设置动画
     * */
    private void initAnim() {
       //一View的中心点为旋转参考点
        mRotateAnimation = new RotateAnimation(
                0f,
                360f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);
        //设置动画
        for (int i = 0; i < 20; i++) {
            if(i<=20){
                mRotateAnimation.setRepeatCount(i);//无限旋转
                mRotateAnimation.setFillAfter(true);
            }
        }
    }

    /**
     * 2.2 初始化画笔
     * */
    private void initPaint() {
        mStartPaint = new Paint();
        //设置画笔
        mStartPaint.setStyle(Paint.Style.STROKE);//描边
        mStartPaint.setStrokeWidth(5);
        mStartPaint.setAntiAlias(true);//抗锯齿
        mStartPaint.setColor(Color.WHITE);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);//描边
        mPaint.setStrokeWidth(5);
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setColor(Color.WHITE);

    }

    /**
     * 3 重写测量方法
     * */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mPadding = 5;
        //3.3
        initRect();
    }

    /**
     * 3.3
     * */
    private void initRect() {
        //按照左上右下
        mRectF = new RectF(0, 0, mWidth, mWidth);
    }

    /**
     * 4 重写onDrow方法
     * */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //4.1 绘制圆
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(mWidth/2,mWidth/2,mWidth/2-mPadding,mPaint);

        //4.2 绘制六个椭圆
        mPaint.setStyle(Paint.Style.FILL);
        initArc(canvas);

        //4.3 绘制里面的小圆
        //1 使用文字start
       // mPaint.setColor(Color.RED);
        //mPaint.setStyle(Paint.Style.FILL);
        //canvas.drawCircle(mWidth/2,mWidth/2,50,mPaint);

        //2 使用图片


        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.start);
        canvas.drawBitmap(bitmap,getX()+mWidth/2-130,getY()+mWidth/2-130,mPaint);

        //4.4
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(24);
        Rect rect = new Rect();
        //需要定义一个字符串数组  在上面  全局
        mPaint.getTextBounds(str,0,str.length(),rect);
        int strWidth = rect.width();//文本的宽度
        int textHeight = rect.height();//文本的高度
        canvas.drawText(str,mWidth/2-25+25-strWidth/2,mWidth/2+textHeight/2,mPaint);

    }

    /**
     * 4.2 绘制六个椭圆  和对应的文字
     * */
    private void initArc(Canvas canvas) {
        //绘制椭圆
        for (int i = 0; i < 6; i++) {
            //需要创建一个该表颜色的方法
            mPaint.setColor(colors[i]);
            canvas.drawArc(mRectF,(i-1)*60+60,60,true,mPaint);
        }
        //绘制对应椭圆的文字
        for (int i = 0; i < 6; i++) {
            mPaint.setColor(Color.BLACK);
            Path path = new Path();
            path.addArc(mRectF,(i-1)*60+60,60);
            canvas.drawTextOnPath(contents[i],path,60,60,mPaint);
        }
    }

    public int[] colors = new int[]{
            Color.parseColor("#8EE5EE"),
            Color.parseColor("#FFD700"),
            Color.parseColor("#FFD39B"),
            Color.parseColor("#FF8247"),
            Color.parseColor("#FF34B3"),
            Color.parseColor("#F0E68C")
    };

    /**
     * 6 点击事件
     * */
    @Override
    public void onClick(View v) {
        //需要定义一个全局变量 isStart
       if(!isStart){
           isStart = true;
           mRotateAnimation.setDuration(1000);
           //不停顿
           mRotateAnimation.setInterpolator(new LinearInterpolator());
           startAnimation(mRotateAnimation);
       }else{
           isStart = false;
           //6.2
           stopAnim();
       }
    }

    /**
     * 6.2
     * */
    private void stopAnim() {
        clearAnimation();
    }
}
