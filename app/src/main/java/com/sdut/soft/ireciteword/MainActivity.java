package com.sdut.soft.ireciteword;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

import com.sdut.soft.ireciteword.bean.User;
import com.sdut.soft.ireciteword.user.UserService;
import com.sdut.soft.ireciteword.utils.Const;
import com.sdut.soft.ireciteword.utils.DBFileUtils;
import com.sdut.soft.ireciteword.utils.SettingsUtils;
import com.sdut.soft.ireciteword.utils.YouDaoAudioUriUtils;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
     MediaPlayer mediaPlayer;

     @BindView(R.id.et_user)
     EditText etUser;
     @BindView(R.id.et_pwd)
     EditText etPwd;
    UserService userService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initDB();

        initView();
    }

    private void initDB() {
        DBFileUtils.copyDBData(this);
    }

    private void initView() {
        userService = new UserService(this);
        if (checkIsLogin()) {
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
        }
    }

    private boolean checkIsLogin() {
        return userService.currentUser() != null;
    }

    @OnClick(R.id.btn_login)
    public void login() {
        User u = new User(etUser.getText().toString() ,etPwd.getText().toString());
        if (userService.login(u) != null) {
            Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
            userService.saveUser(u);
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this,"登录失败",Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btn_register)
    public void register() {
        String name = etUser.getText().toString();
        String password = etPwd.getText().toString();
        int perDay = Const.PER_DAY;

        User u = new User(null,name,password,0L,0L,perDay);
        UserService userService = new UserService(this);
        if (userService.login(u) != null) {
            Toast.makeText(this,"该用户已注册，请登录！！！",Toast.LENGTH_SHORT).show();
            return;
        }
        if (userService.register(u)) {
            Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"注册失败",Toast.LENGTH_SHORT).show();
        }
    }



    private void webView() {
        WebView webView = null;
        webView.getSettings().setJavaScriptEnabled(true);//getSettiongs()用于设置一些浏览器属性，这里让WebView支持JavaScript脚本
        webView.setWebViewClient(new WebViewClient());//当需要从一个网页跳转到另一个网页是，希望目标网页仍然在当前WebView显示，而不是打开浏览器
        webView.loadUrl("http://www.baidu.com");
    }


}
