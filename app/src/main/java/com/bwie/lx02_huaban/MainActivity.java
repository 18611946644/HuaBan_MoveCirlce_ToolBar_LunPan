package com.bwie.lx02_huaban;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.lx02_huaban.fkzhuanpan.FKZhuanPanActivity;
import com.bwie.lx02_huaban.movecricle.MoveCircleActivity;
import com.bwie.lx02_huaban.movepeople.MovePeopleActivity;
import com.bwie.lx02_huaban.toolbar.ToolBarActivity;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 总主界面
 * */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    
    
    private ImageView mIv;
    private Button mMBtnColorR;
    private Button mBtnColorG;
    private Button mBtnShuaZi;
    private Button mBtnBaoCun;
    private Bitmap mBitmap;
    private Bitmap mZbmp;
    private Canvas mCanvas;
    private Paint mPaint;
    private int mStartX;
    private int mStartY;
    private FileOutputStream mFileOutputStream;
    private Button mBtnMoveCircle;
    private Button mBtnMoveRen;
    private Button mBtnDZhuanPan;
    private Button mBtnToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1 初始化控件
        getinit();
        //2 画笔  画板  原图等 基础初始化
        getinitH();
        //3 为mIV设置触发事件
        setmIVOnTouchEven();
        //5 底部按钮点击事件监听
        setBtnOnClickListener();
        //4 初始化画笔  设置为全局
        mPaint = new Paint();
        mPaint.setColor(Color.RED);//设置默认颜色
        mPaint.setStyle(Paint.Style.FILL);//填充
        mPaint.setStrokeWidth(5);//宽度
        mPaint.setStrokeCap(Paint.Cap.ROUND);//设置画笔转弯处的链接风格
        mPaint.setDither(true);//设置抖动效果
    }

    /**
     * //6 底部按钮点击事件
     * */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_colorr:
                //点击红色  改变为红色
                //为画笔设置
                mPaint.setColor(Color.RED);
                break;
            case R.id.btn_colorg:
                //点击绿色 改变颜色
                mPaint.setColor(Color.GREEN);
                break;
            case R.id.btn_shuazi:
                //点击刷子

                break;
            case R.id.btn_baocun:
                //点击保存  调用保存方法
                save(v);
                break;

            case  R.id.btn_movecircle:
                //点击移动的圆  跳转到另一个界面
                Intent intent = new Intent(MainActivity.this, MoveCircleActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_move_ren:
                //点击跳转移动的小人
                Intent intent1 = new Intent(MainActivity.this, MovePeopleActivity.class);
                startActivity(intent1);
                break;

            case R.id.btn_dzhuanpan:
                //点击跳转转盘
                Intent intent3 = new Intent(MainActivity.this, FKZhuanPanActivity.class);
                startActivity(intent3);
                break;

            case R.id.btn_toolbar:
                //点击跳转toolbar使用
                Intent intent2 = new Intent(MainActivity.this, ToolBarActivity.class);
                startActivity(intent2);
                break;
        }
    }


    /**
     * //5 底部按钮点击事件监听
     * */
    private void setBtnOnClickListener() {
        mMBtnColorR.setOnClickListener(this);
        mBtnColorG.setOnClickListener(this);
        mBtnShuaZi.setOnClickListener(this);
        mBtnBaoCun.setOnClickListener(this);
        mBtnMoveCircle.setOnClickListener(this);
        mBtnMoveRen.setOnClickListener(this);
        mBtnDZhuanPan.setOnClickListener(this);
        mBtnToolBar.setOnClickListener(this);
    }

    /**
     * //3 为mIV设置触发事件
     * */
    private void setmIVOnTouchEven() {
        mIv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){//Action动作
                    case MotionEvent.ACTION_DOWN://按下事件
                    
                        //1 得到按下的位置
                        mStartX = (int) event.getX();
                        mStartY = (int) event.getY();
                        //测试打印
                        //Log.i(TAG, "onTouch: ACTION_DOWN: "+mStartX+"    "+ mStartY);

                        break;

                    case MotionEvent.ACTION_MOVE://移动事件
                         //2 得到鼠标移动的坐标
                        int mMoveX = (int) event.getX();
                        int mMoveY = (int) event.getY();
                        //测试打印
                        Log.i(TAG, "onTouch: ACTION_DOWN: "+mMoveX+"    "+ mMoveY);

                        //画图
                        mCanvas.drawLine(mStartX,mStartY,mMoveX,mMoveY,mPaint);

                        //更新起始坐标
                        mStartX= (int) event.getX();
                        mStartY= (int) event.getY();
                        mIv.setImageBitmap(mZbmp);

                        break;

                    case MotionEvent.ACTION_UP://抬起事件

                        break;
                }
                //改为true 解决事件分发问题
                return true;
            }
        });
    }

    /**
     * 4 保存
     * */
    public void save(View view){

        //1 创建一个路径
        String srcpath = "sdcard/"+"imgs"+".jpg";
        //2 创建一个文件
        File file = new File(srcpath);
        //初始化一个文件输出流
        try {
            mFileOutputStream = new FileOutputStream(file);
            mZbmp.compress(Bitmap.CompressFormat.JPEG,100,mFileOutputStream);
            mFileOutputStream.flush();
            mFileOutputStream.close();
            Toast.makeText(this,"已被保存",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this,"保存失败",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    /**
     * //2 画笔  画板  原图等 基础初始化
     * */
    private void getinitH() {
        //1 获取原图
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        //2 创建一张白纸 并设置参数（宽高）
        mZbmp = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), mBitmap.getConfig());
        //3 初始化画板  并将白纸放在画板上
        mCanvas = new Canvas(mZbmp);
        //4 初始化画笔  设置为全局

        //为画笔设置

        //5 在画板上画画
        mCanvas.drawBitmap(mZbmp,new Matrix(),mPaint);
        mIv.setImageBitmap(mZbmp);
    }

    /**
     * //1 初始化控件
     * */
    private void getinit() {
        mIv = findViewById(R.id.iv);//原图
        mMBtnColorR = findViewById(R.id.btn_colorr);//红色
        mBtnColorG = findViewById(R.id.btn_colorg);//绿色
        mBtnShuaZi = findViewById(R.id.btn_shuazi);//刷子
        mBtnBaoCun = findViewById(R.id.btn_baocun);//保存
        mBtnMoveCircle = findViewById(R.id.btn_movecircle);//移动的圆的按钮
        mBtnMoveRen = findViewById(R.id.btn_move_ren);//移动的人
        mBtnDZhuanPan = findViewById(R.id.btn_dzhuanpan);//抽奖
        mBtnToolBar = findViewById(R.id.btn_toolbar);//toolBar使用
    }

}
