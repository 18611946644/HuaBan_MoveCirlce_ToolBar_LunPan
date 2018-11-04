package com.bwie.lx02_huaban.toolbar;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bwie.lx02_huaban.MainActivity;
import com.bwie.lx02_huaban.R;

public class ToolBarActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mTbToolBar;
    private Button mBtnToolBar;


    /**
     * 步骤：
     1、在style中的Theme文件中去掉ActionBar，该为NoActionBar
     <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
     2、在Activity.xml布局中文件中使用ToolBar（V4）的控件
     3.在Activity代码中实例化ToolBar,设置属性
     4.menu:选中res-->new-->创建menu文件夹
     在menu中新建资源文件menu.xml
     在menu中定义item
     xmlns:app="http://schemas.android.com/apk/res-auto"
     <item
     android:id="@+id/srarch"
     android:icon="@drawable/ic_search"
     android:title="@string/search"
     app:showAsAction="ifRoom" />……
     5.加载menu:tb.inflateMenu(id);
     6.设置menu点击事件：tb.setOnMenuItemClickListener,根据itemid分发事件
     * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);
        //1 控件
        getinit();
        //2 Toolbar设置
        setToolBar();
    }

    /**
     * 4 点击事件
     * */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_toolbar://点击toolbar按钮
                Toast.makeText(ToolBarActivity.this,"点击按钮",Toast.LENGTH_SHORT).show();
                break;
        }
    }


    /**
     * //2 Toolbar设置
     * */
    private void setToolBar() {
        //设置图标
       // mTbToolBar.setNavigationIcon(R.drawable.ic_launcher_background);
        //设置Logo
        mTbToolBar.setLogo(R.mipmap.ic_launcher);
        //标题
        mTbToolBar.setTitle(R.string.app_name);
        //二级标题
        mTbToolBar.setSubtitle(R.string.app_name);
        //背景颜色
        mTbToolBar.setBackgroundColor(Color.GRAY);
        //字体颜色
        mTbToolBar.setTitleTextColor(Color.WHITE);
        //监听
       /* mBtnToolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ToolBarActivity.this,"点击按钮",Toast.LENGTH_SHORT).show();
            }
        });*/

        //加载menu
        mTbToolBar.inflateMenu(R.menu.menu);

        //添加点击事件
        mTbToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){

                    case R.id.search:
                        Toast.makeText(ToolBarActivity.this,"搜索",Toast.LENGTH_SHORT).show();
                        break;

                    /*case R.id.notification:
                        Toast.makeText(ToolBarActivity.this,"通知",Toast.LENGTH_SHORT).show();
                            break;*/

                }

                return false;
            }
        });
    }

    /**
     * //1 控件
     * */
    private void getinit() {
        mTbToolBar = findViewById(R.id.tb_toolbar);
        mBtnToolBar = findViewById(R.id.btn_toolbar);
    }

}
