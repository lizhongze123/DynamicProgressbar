package com.example.lzz;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * 动态进度条和两端都是圆角的进度条
 *
 */
public class MainActivity extends AppCompatActivity {

    private TextView animationView; //动画控件
    private Button btn;
    private View viewBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.btn);

        viewBg = findViewById(R.id.view_bg);
        animationView = (TextView) findViewById(R.id.animationView);
        animationView.setBackgroundResource(R.drawable.myanim); //将帧动画资源文件作为View控件背景
        AnimationDrawable rocketAnimation =(AnimationDrawable) animationView.getBackground(); //获取背景并强转成为帧动画对象
        rocketAnimation.start();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
    }

    private int mCount = 0;
    private int mDis = 100;


    private Handler handle = new Handler() {
        public void handleMessage(android.os.Message msg) {
            animationView.setLayoutParams(lp);
        }
    };

    ViewGroup.LayoutParams lp;

    private void start(){
        mCount = 0;
        mDis = 100;
        new Thread() {
            public void run() {

                while (mCount <= 100 && mDis > 0) {
                    lp = animationView.getLayoutParams();
                    lp.width =  mCount * viewBg.getWidth() / 100;
                    mCount++;
                    mDis--;
                    handle.sendEmptyMessage(0);
                    SystemClock.sleep(100);
                }

            }
        }.start();
    }
}
