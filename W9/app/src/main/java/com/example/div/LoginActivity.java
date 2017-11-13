package com.example.div;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity  {
    EditText user,pwd;
    Button login;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = (EditText) findViewById(R.id.user);
        pwd = (EditText) findViewById(R.id.pwd);


        if(savedInstanceState!=null){
            user.setText(savedInstanceState.getString("user"));
            pwd.setText(savedInstanceState.getString("pwd"));
        }
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                final String userString = user.getText().toString();
                final String pwdString = pwd.getText().toString();
                if(TextUtils.isEmpty(userString)||TextUtils.isEmpty(pwdString)){
                    Toast.makeText(LoginActivity.this,"you have not entry the username or password",Toast.LENGTH_LONG).show();
                    return;
                }
                new Thread(){
                    @Override
                    public void run() {
                        final User user1 = new User();
                        user1.userName=userString;
                        user1.password=pwdString;
                        final boolean b = SqlOpertion.login(userString,pwdString);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(!b){
                                    Toast.makeText(LoginActivity.this," user name or passowrd error",Toast.LENGTH_LONG).show();
                                    return;
                                }
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                intent.putExtra("user",user1);
                                startActivity(intent);
                                Toast.makeText(LoginActivity.this,"Welcome "+userString,Toast.LENGTH_LONG).show();
                                LoginActivity.this.finish();
                            }
                        });

                    }
                }.start();

            }
        });
        findViewById(R.id.regist).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();
                startActivity(new Intent(LoginActivity.this,RegistActivity.class));
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("user",user.getText().toString());
        outState.putString("pwd",pwd.getText().toString());
    }
}

