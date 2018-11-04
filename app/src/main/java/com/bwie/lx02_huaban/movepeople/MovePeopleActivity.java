package com.bwie.lx02_huaban.movepeople;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.bwie.lx02_huaban.MainActivity;
import com.bwie.lx02_huaban.R;
import com.bwie.lx02_huaban.movepeople.myview.MyView;

public class MovePeopleActivity extends AppCompatActivity {

    private MyView mMyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_people);
        //1 控件初始化
        LinearLayout PeopleLV = findViewById(R.id.mpeoplelv);
        final MyView myView = new MyView(this);
        /*myView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                myView.PX = event.getX();
                myView.PY = event.getY();
                myView.invalidate();
                return true;
            }
        });*/
        PeopleLV.addView(myView);
    }

}
