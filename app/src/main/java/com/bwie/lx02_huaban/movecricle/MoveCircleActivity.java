package com.bwie.lx02_huaban.movecricle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bwie.lx02_huaban.R;

public class MoveCircleActivity extends AppCompatActivity {

    private Paint mPaint;
    private float mStartX;
    private float mStartY;
    private int mX;
    private int mY;
    private LinearLayout mMyLayout;
    //1 创建一个内部类
    public class MyCircleView extends View {
        //圆的半径
        private int mR = 100;
        //圆的圆心的x坐标
        private float mRX = mR;
        //圆的圆心的Y坐标
        private float mRY = mR;
        //控制是否可以移动的变量 true的时候可以移动
        private boolean moveable;

        //实例化一个rect类
        // Rect类主要用于表示坐标系中的一块矩形区域，
        // 并可以对其做一些简单操作。
        // 这块矩形区域，需要用左上右下两个坐标点表示（left,top,right,bottom
        Rect rect = new Rect();

        //2 实现三个方法
        public MyCircleView(Context context) {
            this(context, null);
        }

        public MyCircleView(Context context, @Nullable AttributeSet attrs) {
            this(context, attrs, 0);
            initView();
        }

        public MyCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            //2.2画布  画笔等 初始化设置
            //initView();
        }

        /**
         * 3 重写测量方法
         */
        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            //得到屏幕的宽高
            int width = this.getWidth();
            int height = this.getHeight();
            //获取屏幕的正中心点         就是屏幕宽高1/2的坐标
            //注意这里的控件由于是match  match 属性所以这样可以得到屏幕中心点
            mX = width / 2;
            mY = height / 2;

        }


        /**
         * 4 重写OnDraw绘制方法
         */
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            //根据圆心的坐标来绘制圆的位置的，而圆心的坐标，我们触摸屏幕的时候被我们修改了
            canvas.drawCircle(mX, mY, mR, mPaint);
            rect.set((mX - mR), (mY - mR), (mX + mR), (mY + mR));
        }

        /**
         * 4   //要单点拖动，保证手指在圆上的时候才移动，我们需要判断触摸的位置
         * /使用触摸事件来实现小球的拖动
         * //理解:主要是根据圆心坐标(x,y)的变化来确定圆的新位置
         */
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            //使用switch 判断事件触发的类型
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN://鼠标放下
                    //得到鼠标放下的位置
                    mStartX = event.getX();
                    mStartY = event.getY();

                    // 4.3 判断 定义一个方法 看鼠标 是否按在了圆上
                    //Boolean onBall = isOnBall(mStartX,mStartY);
                    /*if(onBall){
                        Toast.makeText(getContext(),"按在圆上了",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(),"不在圆上",Toast.LENGTH_SHORT).show();
                    }*/
                    //*判断 (原点坐标)
                    /**
                     *  参数： mRx圆心X轴坐标
                     *        mR 圆的半径
                     *        mStartX 鼠标点击坐标的X坐标
                     *
                     * */
                    if (mStartX > mRX - mR && mStartX < mRX + mR && mStartY > mRY - mR && mStartY < mRY + mR) {
                        moveable = true;//可以移动
                        Toast.makeText(getContext(), "我按下了", Toast.LENGTH_LONG).show();
                    } else {
                        moveable = false;//设置圆不能移动
                    }
                    break;

                case MotionEvent.ACTION_MOVE://鼠标移动
                    //*首先判断圆是否能够移动
                    if (moveable) {
                        //重新设置一下圆心的位置， 把我们圆心的位置（pointX,pointY)设置成
                        // 当前触摸的位置（event.getX()，event.getY()）
                        //所以首先得到当前触摸的位置  赋值给圆心
                        mRX = event.getX();
                        mRY = event.getY();

                        mX = (int) mRX;
                        mY = (int) mRY;

                        //根据圆心坐标  重新绘制
                        invalidate();

                    }

                    float x1 = event.getX();
                    float y1 = event.getY();
                    if (x1 > rect.left && x1 < rect.right && y1 > rect.top && y1 < rect.bottom) {
                        //得到当前坐标 用event得到
                        this.mRX = event.getX();
                        this.mRY = event.getY();
                        //刷新当前视图
                        invalidate();//这个方法是主线程中刷新，
                        // postInvalidate();//这个方法是子线程中刷新
                    }
                    break;

                case MotionEvent.ACTION_UP://鼠标抬起

                    break;

                case MotionEvent.ACTION_CANCEL://取消
                    break;
            }

            return true;
        }

        /**
         * //2.2画布  画笔等 初始化设置
         */
        private void initView() {
            //1 初始化一个画笔
            mPaint = new Paint();
            //设置
            mPaint.setColor(Color.BLUE);
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.FILL);//填充
            mPaint.setStrokeWidth(5);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_circle);
        //1 找到布局管理器
        mMyLayout = findViewById(R.id.myLayout);

        //2 将自定义的View  存入到布局管理器中
        mMyLayout.addView(new MyCircleView(this));
    }

    /**
     *
     * 方法二  简单
     * */
    /*
    * public class BallsView extends View {

    private Paint mPaint;
    private int mHeight;
    private int mWidth;
    private int X;
    private int Y;
    private boolean mOnBall;

    //这三个方法是让你做初始化的业务逻辑
    //代码中使用自定义控件(new BallsView),自动回调此方法.
    public BallsView(Context context) {
        this(context, null);
    }

    //XML布局使用此自定义控件,自动回调此方法
    public BallsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        System.out.println("BallsView第二个构造方法");
    }

    //XML布局中使用此自定义控件,且带有样式时,自动回调
    public BallsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        System.out.println("BallsView第三个构造方法");
        intiView();
    }

    //自定义控件做个初始化的操作
    private void intiView() {
        mPaint = new Paint();
    }

    //测试里
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取当前控件的宽高
        mHeight = this.getHeight();
        mWidth = this.getWidth();
        //获取屏幕的正中心点
        Y = mHeight / 2;
        X = mWidth / 2;
    }

    private int mRadius = 50;

    //绘制,能在OnDrawer里面创建对象吗?决定不能
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置画笔的颜色
        mPaint.setColor(Color.GREEN);
        //画圆
        canvas.drawCircle(X, Y, mRadius, mPaint);
    }

    //手势监听器,可以得到用户手指在屏幕上滑动的坐标
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //二者区别,getX得到的是以自定义控件左上角为原点的X轴坐标,getRawX得到的是以屏幕左上角为原点的X轴坐标
                int downX = (int)event.getX();
                int downY = (int)event.getY();
//                event.getRawX();

                //进行判断,判断用户的手指是否按在了园内
                mOnBall = isOnBall(downX, downY);

                Toast.makeText(getContext(), "用户的手指是否点到圆内了"+mOnBall, Toast.LENGTH_SHORT).show();

                break;
            case MotionEvent.ACTION_MOVE:
                //只用用户点到圆内时,我才让圆动
                if (mOnBall){
                    X = (int) event.getX();
                    Y = (int) event.getY();
                    //回调OnDrawer方法
                    postInvalidate();
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }

    //,判断用户的手指是否按在了园内
    private boolean isOnBall(int downX, int downY) {
        //勾股定理的绝对值
        double sqrt = Math.sqrt((downX - X) * (downX - X) + (downY - Y) * (downY - Y));
        //判断绝对值是否小于等于半径
        if(sqrt <= mRadius){
            return true;
        }

        return false;
    }

    //基本不用
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}

    * */


}
