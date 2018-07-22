package com.example.sharm.speedtestnetcircularprogressbar;

import android.animation.ValueAnimator;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.dinuscxj.progressbar.CircleProgressBar;

public class MainActivity extends AppCompatActivity {

    private CircleProgressBar mCircleProgressBar;
    private CircleProgressBar mCircleProgressBarShadowHide;
    private ImageView imageViewNeedle;
    private TextView textViewCurrentDbCPB;
    private TextView [] textViewCPBLabels = new TextView[9];
    private int [] textViewCPBLabelValues = {0, 20, 30, 50, 60, 70, 90, 100, 120};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
//        mCircleProgressBar.setProgress(90);
//        mCircleProgressBarShadowHide.setProgress(90);
//        imageViewNeedle.setRotation((float) (2.19166667*90) );
//        mCircleProgressBar.setProgressFormatter(new CircleProgressBar.ProgressFormatter() {
//            @Override
//            public CharSequence format(int progress, int max) {
//                return String.format(DEFAULT_PATTERN, (int) ((float) progress / (float) max * 100));
//            }
//        });
        ValueAnimator animator = ValueAnimator.ofInt(0, 121);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
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
                System.out.println("Rotation progress: "+(float) (2.19166667*codeToGaugeValue(progress)));
            }
        });
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(10000);
        animator.start();
    }

    private double codeToGaugeValue(float codeValue) {
        return (138.9473684-1.57894737*codeValue);
    }

    private double gaugeToCodeValue(float gaugeValue) {
        return (87.9999999-gaugeValue*0.63333333);
    }
}
