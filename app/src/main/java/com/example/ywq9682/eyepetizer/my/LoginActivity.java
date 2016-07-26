package com.example.ywq9682.eyepetizer.my;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.main.MyApp;
import com.example.ywq9682.eyepetizer.welcome.Users;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import info.hoang8f.widget.FButton;

//import cn.bmob.v3.BmobUser;
//import cn.bmob.v3.c.V;
//import cn.bmob.v3.listener.SaveListener;

/**
 * Created by dllo on 16/7/20.
 */
public class LoginActivity extends Activity implements View.OnClickListener {
    private Button register, registerTwo;
    private EditText password, userName, passwordRegister;
    private FButton login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (FButton) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        password = (EditText) findViewById(R.id.password);
        registerTwo = (Button) findViewById(R.id.register_two);
        userName = (EditText) findViewById(R.id.username);
        login.setButtonColor(getResources().getColor(R.color.fbutton_color_concrete));
        login.setShadowColor(getResources().getColor(R.color.fbutton_color_asbestos));
        login.setShadowEnabled(true);
        login.setShadowHeight(5);
        login.setCornerRadius(5);
        passwordRegister = (EditText) findViewById(R.id.password_two);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        registerTwo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login://登录按钮
                if (password.getText().toString().isEmpty() || userName.getText().toString().isEmpty()) {
                    Toast.makeText(this, "你是不是傻?", Toast.LENGTH_SHORT).show();
                } else {
                    login();
                }
                break;
            case R.id.register://注册一下
                password.setVisibility(View.GONE);//密码
                passwordRegister.setVisibility(View.VISIBLE);//验证吗
                login.setVisibility(View.GONE);//登录
                registerTwo.setVisibility(View.VISIBLE);//注册
                break;
            case R.id.register_two://注册按钮
//                password.setVisibility(View.VISIBLE);
//                passwordRegister.setVisibility(View.GONE);
//                login.setVisibility(View.VISIBLE);
//                registerTwo.setVisibility(View.GONE);
                register();
                break;
        }
    }

    private void register() {
        BmobUser bmobUser = new BmobUser();
        bmobUser.setUsername(userName.getText().toString());
        bmobUser.setPassword(passwordRegister.getText().toString());
        bmobUser.signUp(MyApp.context, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent("com.example.ywq9682.eyepetizer.ILKL");
                sendBroadcast(intent);
                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(LoginActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void login() {
        final BmobUser bmobUser = new BmobUser();
        bmobUser.setUsername(userName.getText().toString());
        bmobUser.setPassword(password.getText().toString());
        bmobUser.login(MyApp.context, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(LoginActivity.this, "成功咯", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent("com.example.ywq9682.eyepetizer.ILK");
                sendBroadcast(intent1);
                final Users users = BmobUser.getCurrentUser(LoginActivity.this, Users.class);
                BmobQuery<Users> usersBmobQuery = new BmobQuery<Users>();
                usersBmobQuery.addWhereEqualTo("username", bmobUser.getUsername());
                usersBmobQuery.findObjects(LoginActivity.this, new FindListener<Users>() {
                    @Override
                    public void onSuccess(List<Users> list) {
                        Intent intent2 = new Intent("com.example.ywq9682.eyepetizer.ILKLL");
                        sendBroadcast(intent2);

                        // SingleLiteOrm.getSingleLiteOrm().getLiteOrm().insert(users);
                    }
                    @Override
                    public void onError(int i, String s) {
                    }
                });

                finish();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(LoginActivity.this, "失败了", Toast.LENGTH_SHORT).show();

            }
        });

    }


}
