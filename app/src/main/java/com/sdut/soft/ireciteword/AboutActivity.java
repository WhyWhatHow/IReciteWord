package com.sdut.soft.ireciteword;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.sdut.soft.ireciteword.activity.base.BaseActivity;

import static android.R.color.white;

public class AboutActivity extends BaseActivity { //  inherit BaseActivity can cancel the titleBar

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  remove window title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_info);

        setToolBar();

    }

    private void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("About");//设置主标题
        toolbar.setTitleTextColor(getResources().getColor(white));//设置主标题颜色

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        StatusBarUtil.setColor(this, R.color.color_main_blue);
        //  set status transparent
//        StatusBarUtil.setTransparent(this);
    }

    /**
     *  use back button
     *
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


