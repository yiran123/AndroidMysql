package com.example.div;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A login screen that offers login via email/password.
 */
public class RegistActivity extends BaseActivity  {
    EditText user,pwd,pwd2,first,last;
    Button regist;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
//        Toast.makeText(this,JDBCUtils.getInstance().getConnection(this).toString(),Toast.LENGTH_LONG).show();
        user = (EditText) findViewById(R.id.user);
        pwd = (EditText) findViewById(R.id.pwd);
        pwd2 = (EditText) findViewById(R.id.pwd2);
        first = (EditText) findViewById(R.id.first);
        last = (EditText) findViewById(R.id.last);
        if(savedInstanceState!=null){
            user.setText(savedInstanceState.getString("user"));
            pwd.setText(savedInstanceState.getString("pwd"));
            pwd2.setText(savedInstanceState.getString("pwd2"));
            first.setText(savedInstanceState.getString("first"));
            last.setText(savedInstanceState.getString("last"));
        }
        regist = (Button) findViewById(R.id.regist);
        regist.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String userString = user.getText().toString();
                String pwdString = pwd.getText().toString();
                String pwdString2 = pwd2.getText().toString();
                if(TextUtils.isEmpty(userString)||TextUtils.isEmpty(pwdString)||TextUtils.isEmpty(pwdString2)){
                    Toast.makeText(RegistActivity.this,"you have not entry the username or password",Toast.LENGTH_LONG).show();
                    return;
                }
                if(!pwdString2.equals(pwdString)){
                    Toast.makeText(RegistActivity.this,"you have The password you entered two times is not consistent",Toast.LENGTH_LONG).show();
                    return;
                }
                final User user  = new User();
                user.userName = userString;
                user.password = pwdString;
                user.firstName = first.getText().toString();
                user.lastName = last.getText().toString();
                new Thread(){
                    @Override
                    public void run() {
                        final boolean b = SqlOpertion.regist(user);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(!b){
                                    Toast.makeText(RegistActivity.this,"already has this user name",Toast.LENGTH_LONG).show();
                                    return;
                                }
                                Intent intent = new Intent(RegistActivity.this,MainActivity.class);
                                intent.putExtra("user",user);
                                startActivity(intent);
                                Toast.makeText(RegistActivity.this,"Welcome "+user.userName,Toast.LENGTH_LONG).show();
                                RegistActivity.this.finish();
                            }
                        });

                    }
                }.start();


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

