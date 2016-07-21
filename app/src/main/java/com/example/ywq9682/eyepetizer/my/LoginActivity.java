package com.example.ywq9682.eyepetizer.my;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ywq9682.eyepetizer.R;
import com.example.ywq9682.eyepetizer.main.MyApp;

//import cn.bmob.v3.BmobUser;
//import cn.bmob.v3.c.V;
//import cn.bmob.v3.listener.SaveListener;

/**
 * Created by dllo on 16/7/20.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    private Button login, register, registerTwo;
    private EditText password, userName, passwordRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        password = (EditText) findViewById(R.id.password);
        registerTwo = (Button) findViewById(R.id.register_two);
        userName = (EditText) findViewById(R.id.username);
        passwordRegister = (EditText) findViewById(R.id.password_two);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        registerTwo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login://登录按钮
//                if (password.getText().toString().isEmpty() || userName.getText().toString().isEmpty()) {
//                    Toast.makeText(this, "你是不是傻?", Toast.LENGTH_SHORT).show();
//                } else {
//                    //login();
//                }
                break;
            case R.id.register://注册一下
//                password.setVisibility(View.GONE);//密码
//                passwordRegister.setVisibility(View.VISIBLE);//验证吗
//                login.setVisibility(View.GONE);//登录
//                registerTwo.setVisibility(View.VISIBLE);//注册
                break;
            case R.id.register_two://注册按钮
//                password.setVisibility(View.VISIBLE);
//                passwordRegister.setVisibility(View.GONE);
//                login.setVisibility(View.VISIBLE);
//                registerTwo.setVisibility(View.GONE);
                //  register();
                break;
        }
    }

    private void register() {
//        BmobUser bmobUser = new BmobUser();
//        bmobUser.setUsername(userName.getText().toString());
//        bmobUser.setPassword(passwordRegister.getText().toString());
//        bmobUser.signUp(MyApp.context, new SaveListener() {
//            @Override
//            public void onSuccess() {
//                Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
//                finish();
//            }
//
//            @Override
//            public void onFailure(int i, String s) {
//                Toast.makeText(LoginActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    private void login() {
//        BmobUser bmobUser = new BmobUser();
//        bmobUser.setUsername(userName.getText().toString());
//        bmobUser.setPassword(password.getText().toString());
//        bmobUser.login(MyApp.context, new SaveListener() {
//            @Override
//            public void onSuccess() {
//                Toast.makeText(LoginActivity.this, "成功咯", Toast.LENGTH_SHORT).show();
//                finish();
//            }
//
//            @Override
//            public void onFailure(int i, String s) {
//                Toast.makeText(LoginActivity.this, "失败了", Toast.LENGTH_SHORT).show();
//            }
//        });

//    }


    }
}