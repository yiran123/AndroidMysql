package com.example.div;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class BaseActivity extends AppCompatActivity {



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.login:
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.regist:
                startActivity(new Intent(this,RegistActivity.class));
                break;
            case R.id.rank:
                startActivity(new Intent(this,RankActivity.class));
                break;
            default :
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
