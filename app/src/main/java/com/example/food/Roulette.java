package com.example.food;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Roulette extends AppCompatActivity {
    private CircleManager circleManager;
    private RelativeLayout layoutRoulette;

    private Button btnRotate;

    private TextView tvResult;

    //private ArrayList<String> STRINGS;
    String[] list = {};
    private float initAngle = 0.0f;
    private int num_roulette = 0;
    View dialogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀바 없애기
        setContentView(R.layout.roulette);

        tvResult = findViewById(R.id.tvResult);
        btnRotate = findViewById(R.id.btnRotate);
        layoutRoulette = findViewById(R.id.layoutRoulette);
        Intent listintent= getIntent();
        list=listintent.getStringArrayExtra("list");
        num_roulette=list.length;
        circleManager = new CircleManager(Roulette.this, num_roulette);
        layoutRoulette.addView(circleManager);

        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotateLayout(layoutRoulette, num_roulette);
            }
        });

    }

    public void rotateLayout(final RelativeLayout layout, final int num) {
        final float fromAngle = getRandom(360) + 3600 + initAngle;
        // getRamdom 써보고 출력 값 확인해보자.

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getResult(fromAngle, num); // start when animation complete
            }
        }, 3000);

        RotateAnimation rotateAnimation = new RotateAnimation(initAngle, fromAngle,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        // 중심을 기준으로 이미지를 회전시키는 함수
        // initAngle : 회전을 시작하는 각도
        // fromAngle : 회전을 종료하는 각도

        // Animation.RELATIVE_TO_SELF 뷰자신의 크기를 기준으로 이동하는 타입
        // Animation.ABSOLUTE 절대 좌표
        // Animation.RELATIVE_TO_PARENT : 객체의 부모를 기준으로 특정 값
        // pivotXType : 객체가 회전할 중심의 x좌표의 타입
        // pivotXValue : 객체가 회전할 중심의 x좌표
        // pivotYType : 객체가 회전할 중심의 y좌표의 타입
        // pivotYValue : 객체가 회전할 중심의 y좌표
        rotateAnimation.setInterpolator(AnimationUtils.loadInterpolator(this, android.R.anim.accelerate_decelerate_interpolator));
        // 애니메이션의 속도
        // AnimationUtils.loadInterpolator 옵션
        //- 점점 빠르게 : accelerate_interpolator
        //- 점점 느리게 : decelerate_interpolator
        //- 위 둘을 동시에 : accelerate_decelerate_interpolator
        //- 시작위치에서 조금 뒤로 당겼다 이동 : anticipate_interpolator
        //- 도착위치를 조금 지나쳤다가 도착위치로 이동 : overshoot_interpolator
        //- 위 둘을 동시에 : anticipate_overshoot_interpolator
        //- 도착위치에서 튕김 : bounce_interpolator
        rotateAnimation.setDuration(3000); // 애니메이션 수행 시간
        rotateAnimation.setFillEnabled(true); // 애니메이션이 끝난 후 화면이 안 깜빡 거리기 위해 view를 유지한다.
        rotateAnimation.setFillAfter(true); // 애니메이션이 끝난후  원래 위치로 돌아가지 않고 종료시점에서 위치를 정지함.
        layout.startAnimation(rotateAnimation); // 애니메이션 시작

    }

    // Set numbers on roulette to random
    public ArrayList<String> setRandom(int maxNumber, int num) {
        ArrayList<String> strings = new ArrayList<>();

        double r = Math.random(); // 0~1사이의 난수를 반환

        for (int i = 0; i < num; i++) {
            int rand = (int) (r * maxNumber);
            strings.add(String.valueOf(rand));
            r = Math.random();
        }

        return strings;
    }

    // get Angle to random
    private int getRandom(int maxNumber) {
        double r = Math.random();
        return (int)(r * maxNumber);
    }

    private void getResult(float angle, int num_roulette) {
        String text = "";
        angle = angle % 360;
        float anglepoint = (360+270-angle) % 360;
        float save = 360 / num_roulette;
        Log.d("roulette", "getResult : " + angle);

        for(int i=0; i<num_roulette; i++){
            if ((anglepoint>=i*save) && (anglepoint<(i+1)*save)){
                //text = STRINGS.get(i);
                text=list[i];
                buildAlert(text);
                break;
            }
        }
        tvResult.setText("결과 : " + text);
    }

    // if you want use AlertDialog then use this
    private void buildAlert(String text) {
        android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(Roulette.this);
        dialogView=getLayoutInflater().inflate(R.layout.detail,null);
        mBuilder.setTitle(text);
        mBuilder.setNegativeButton("취소",null);
        mBuilder.setPositiveButton("확인",null);
        mBuilder.setView(dialogView);
        android.app.AlertDialog dialog = mBuilder.create();
        dialog.show();
    }

    public class CircleManager extends View {
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private int[] COLORS = {Color.parseColor("#F79D96"), Color.parseColor("#F7D14A"), Color.parseColor("#9DC0E5"),
                Color.parseColor("#AFD367"), Color.parseColor("#F8B45A"), Color.parseColor("#C3A1DB")};
        private int num;

        public CircleManager(Context context, int num) {
            super(context);
            this.num = num;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            int width = layoutRoulette.getWidth();
            int height = layoutRoulette.getHeight();
            int sweepAngle = 360 / num; // sweepAngle : Arc의 각도

            RectF rectF = new RectF(0, 0, width, height);
            Rect rect = new Rect(0, 0, width, height);

            int centerX = (rect.left + rect.right) / 2;
            int centerY = (rect.top + rect.bottom) / 2;
            int radius = (rect.right - rect.left) / 2;

            int temp = 0; // temp : Arc를 그릴 시작 각도

            for (int i = 0; i < num; i++) {
                paint.setColor(COLORS[i]);
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                //스타일
                //FILL    채우기만 하며 외곽선은 그리지 않는다.   (디폴트)
                //FILL_AND_STROKE   채우기도 하고 외곽선도 그린다.
                //STROKE    채우지는 않고 외곽선만 그린다.
                paint.setAntiAlias(true);
                //안티 알리아싱은 색상차가 뚜렷한 경계 부근에 중간색을 삽입하여 도형이나 글꼴이 주변 배경과 부드럽게 잘 어울리도록 하는 기법.
                paint.setTextAlign(Paint.Align.CENTER);
                // 글자 정령 CENTER이므로 가운데 정렬
                canvas.drawArc(rectF, temp, sweepAngle, true, paint);

                float medianAngle = (temp + (sweepAngle / 2f)) * (float) Math.PI / 180f;

                paint.setColor(Color.BLACK);
                paint.setTextSize(64);
                paint.setStyle(Paint.Style.FILL_AND_STROKE);

                float arcCenterX = (float) (centerX + (radius * Math.cos(medianAngle))); // Arc's center X
                float arcCenterY = (float) (centerY + (radius * Math.sin(medianAngle))); // Arc's center Y
                // 글씨를 적기위해 호의 중점을 찾는다.

                // put text at middle of Arc's center point and Circle's center point
                float textX = (centerX + arcCenterX) / 2;
                float textY = (centerY + arcCenterY) / 2;

                canvas.drawText(list[i], textX, textY, paint);
                temp += sweepAngle;
            }
        }
    }
}
