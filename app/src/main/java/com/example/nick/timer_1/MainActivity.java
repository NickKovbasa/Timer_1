package com.example.nick.timer_1;

import android.annotation.SuppressLint;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.annotation.Annotation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  AnimationDrawable anim;
  RelativeLayout layout;

    Button btn_start, btn_stop, btn_reset;

    Boolean work;

    int seconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = findViewById(R.id.id_btn_start);
        btn_stop = findViewById(R.id.id_btn_stop);
        btn_reset = findViewById(R.id.id_btn_reset);

        btn_start.setOnClickListener(this);
        btn_reset.setOnClickListener(this);
        btn_stop.setOnClickListener(this);

        layout = findViewById(R.id.layout_1);
        anim = (AnimationDrawable) layout.getBackground();

        anim.setEnterFadeDuration(10000);
        anim.setExitFadeDuration(2000);

        seconds = 0;

        work = false;

        onTime();

        anim.start();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.id_btn_start:

                work = true;

                if (anim != null && !anim.isRunning()){
                    anim.start();
                }
                break;

            case R.id.id_btn_stop:

                work = false;

                anim.stop();

                break;

            case R.id.id_btn_reset:

                work = false;
                seconds = 0;

                anim.stop();

                break;

        }

    }

    private void onTime() {

        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                final TextView textView = findViewById(R.id.text_1);
                int hours = seconds / 3600;
                int minutes = (seconds % 360) / 60;
                int sec  = seconds % 60;

                @SuppressLint("DefaultLocale") String format = String.format("%d:%02d:%02d", hours, minutes, sec);

                textView.setText(format);

                if (work){
                    seconds ++;
                }

                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (anim != null && !anim.isRunning())
            anim.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (anim != null && anim.isRunning())
            anim.stop();
    }
}
