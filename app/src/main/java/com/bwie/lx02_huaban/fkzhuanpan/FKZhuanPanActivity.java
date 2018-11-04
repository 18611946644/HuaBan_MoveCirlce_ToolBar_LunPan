package com.bwie.lx02_huaban.fkzhuanpan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.bwie.lx02_huaban.R;
import com.bwie.lx02_huaban.fkzhuanpan.myview.MyView;

public class FKZhuanPanActivity extends AppCompatActivity {

    private LinearLayout mFLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fkzhuan_pan);
       // MyView myView = new MyView(this);
        MyView fmyView = findViewById(R.id.fmyview);
        mFLinearLayout = findViewById(R.id.Flinearlayout);
       // mFLinearLayout.addView(fmyView);
    }
}
