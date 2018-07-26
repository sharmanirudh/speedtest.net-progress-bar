package com.example.sharm.speedtestnetcircularprogressbar;

import android.animation.ValueAnimator;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dinuscxj.progressbar.CircleProgressBar;

public class MainActivity extends AppCompatActivity {

    private CircleProgressBar mCircleProgressBar;
    private CircleProgressBar mCircleProgressBarShadowHide;
    private ImageView imageViewNeedle;
    private TextView textViewCurrentDbCPB;
    private TextView [] textViewCPBLabels = new TextView[9];
    private int [] textViewCPBLabelValues = {0, 20, 30, 50, 60, 70, 90, 100, 120};
    RelativeLayout mRelativeLayoutGaugeCurrentDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRelativeLayoutGaugeCurrentDb = findViewById(R.id.relativeLayoutGaugeCurrentDb);
        imageViewNeedle = findViewById(R.id.imageViewNeedle);
        textViewCurrentDbCPB = findViewById(R.id.textViewCurrentDbCPB);
        textViewCPBLabels[0] = findViewById(R.id.textView0CPB);
        textViewCPBLabels[1] = findViewById(R.id.textView20CPB);
        textViewCPBLabels[2] = findViewById(R.id.textView30CPB);
        textViewCPBLabels[3] = findViewById(R.id.textView50CPB);
        textViewCPBLabels[4] = findViewById(R.id.textView60CPB);
        textViewCPBLabels[5] = findViewById(R.id.textView70CPB);
        textViewCPBLabels[6] = findViewById(R.id.textView90CPB);
        textViewCPBLabels[7] = findViewById(R.id.textView100CPB);
        textViewCPBLabels[8] = findViewById(R.id.textView120CPB);
        mCircleProgressBar = findViewById(R.id.my_cpb);
        mCircleProgressBarShadowHide = findViewById(R.id.my_cpb_shadow_hide);
        mCircleProgressBar.setMax(165);
        mCircleProgressBarShadowHide.setMax(165);
        mCircleProgressBar.setDrawBackgroundOutsideProgress(true);
        mCircleProgressBar.setProgressBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorBackground));
        mCircleProgressBar.setProgressStartColor(ContextCompat.getColor(MainActivity.this, R.color.colorCircularProgressBarBackground));
        mCircleProgressBar.setProgressEndColor(ContextCompat.getColor(MainActivity.this, R.color.colorCircularProgressBarBackground));
        mCircleProgressBar.setProgress(0);
//        mCircleProgressBar.setProgress(90);
//        mCircleProgressBarShadowHide.setProgress(90);
//        imageViewNeedle.setRotation((float) (2.19166667*90) );
//        mCircleProgressBar.setProgressFormatter(new CircleProgressBar.ProgressFormatter() {
//            @Override
//            public CharSequence format(int progress, int max) {
//                return String.format(DEFAULT_PATTERN, (int) ((float) progress / (float) max * 100));
//            }
//        });
//        mCircleProgressBar.setDrawBackgroundOutsideProgress(true);
//        mCircleProgressBar.setProgressBackgroundColor(R.color.colorBackground);
//        mCircleProgressBar.setProgressStartColor(R.color.colorCircularProgressBarBackground);
//        mCircleProgressBar.setProgressEndColor(R.color.colorCircularProgressBarBackground);
        /** starting animation **/
        mRelativeLayoutGaugeCurrentDb.setVisibility(View.INVISIBLE);
        imageViewNeedle.setVisibility(View.INVISIBLE);
        for (int i = 0; i < 9; i++) {
                textViewCPBLabels[i].setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorBackground));
        }
        ValueAnimator animatorGauge = ValueAnimator.ofInt(0, 121);
        animatorGauge.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int progress = (int) animation.getAnimatedValue();
                mCircleProgressBar.setProgress(progress);
            }
        });
        animatorGauge.setRepeatCount(0);
        animatorGauge.setDuration(1200);
        animatorGauge.start();

        ValueAnimator animatorText = ValueAnimator.ofInt(0, 9);
        animatorText.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int i = (int) animation.getAnimatedValue();
                if (i >=0 && i < 9)
                textViewCPBLabels[i].setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorActiveGaugeText));
                if (i > 0)
                    textViewCPBLabels[i-1].setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorNotActiveGaugeText));
                if (i == 9) {
                    imageViewNeedle.setVisibility(View.VISIBLE);
                    mRelativeLayoutGaugeCurrentDb.setVisibility(View.VISIBLE);
                    imageViewNeedle.setRotation(0);
                    mCircleProgressBar.setDrawBackgroundOutsideProgress(true);
                    mCircleProgressBar.setProgressBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorCircularProgressBarBackground));
                    mCircleProgressBar.setProgressStartColor(ContextCompat.getColor(MainActivity.this, R.color.colorTransparent));
                    mCircleProgressBar.setProgressEndColor(ContextCompat.getColor(MainActivity.this, R.color.colorTransparent));
                    mCircleProgressBar.setProgress(0);
                }
            }
        });
        animatorText.setRepeatCount(0);
        animatorText.setDuration(800);
        animatorText.setStartDelay(1500);
        animatorText.start();

        ValueAnimator animatorValueOnGauge = ValueAnimator.ofInt(0, 121);
        animatorValueOnGauge.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int progress = (int) animation.getAnimatedValue();
                mCircleProgressBar.setProgress(progress);
                mCircleProgressBarShadowHide.setProgress(progress);
                imageViewNeedle.setRotation((float) (2.19166667*progress) );
                textViewCurrentDbCPB.setText(String.format("%d", progress));
                for (int i = 0; i < 9; i++) {
                    if (textViewCPBLabelValues[i] < progress)
                        textViewCPBLabels[i].setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorActiveGaugeText));
                    else
                        textViewCPBLabels[i].setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorNotActiveGaugeText));
                }
            }
        });
        animatorValueOnGauge.setRepeatCount(ValueAnimator.INFINITE);
        animatorValueOnGauge.setDuration(2000);
        animatorValueOnGauge.setStartDelay(2400);
        animatorValueOnGauge.start();
    }
}
