package com.example.div;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RankActivity extends BaseActivity {
    TextView name[], score[];
    List<User> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        name = new TextView[5];
        score = new TextView[5];
        name[0] = (TextView) findViewById(R.id.name1);
        name[1] = (TextView) findViewById(R.id.name2);
        name[2] = (TextView) findViewById(R.id.name3);
        name[3] = (TextView) findViewById(R.id.name4);
        name[4] = (TextView) findViewById(R.id.name5);
        score[0] = (TextView) findViewById(R.id.score1);
        score[1] = (TextView) findViewById(R.id.score2);
        score[2] = (TextView) findViewById(R.id.score3);
        score[3] = (TextView) findViewById(R.id.score4);
        score[4] = (TextView) findViewById(R.id.score5);
        new Thread() {
            @Override
            public void run() {
                list = SqlOpertion.getTop5UserScores();
                if (list != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < list.size(); i++) {
                                name[i].setText(list.get(i).userName);
                                score[i].setText(list.get(i).score + "");
                            }
                        }
                    });

                }

            }
        }.start();

    }

}
