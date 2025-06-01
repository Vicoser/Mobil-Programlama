package com.example.kodlamacarki;
import android.content.Intent;
import android.media.MediaPlayer;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class CustomWheelView extends View {
    public interface OnSpinCompleteListener {
        void onSpinComplete(String kazandiginPuan);
    }
    private OnSpinCompleteListener listener;

    public void setOnSpinCompleteListener(OnSpinCompleteListener listener) {
        this.listener = listener;
    }

    private MediaPlayer tickSound;

    private Bitmap wheelBitmap;
    private float centerX, centerY;
    private float startAngle = 0;
    private float lastTouchAngle;
    private float touchStartAngle;
    private long touchStartTime;
    private float velocity = 0;
    private boolean isDragging = false;
    private boolean isFlinging = false;
    private long flingStartTime = 0;
    private static final long MIN_FLING_DURATION = 3000;
    private Runnable flinger;

    public CustomWheelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        wheelBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cark_puanli);
        tickSound = MediaPlayer.create(getContext(), R.raw.tick);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        centerX = getWidth() / 2f;
        centerY = getHeight() / 2f;


        float scale = 0.4f;

        int scaledWidth = (int) (wheelBitmap.getWidth() * scale);
        int scaledHeight = (int) (wheelBitmap.getHeight() * scale);

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(wheelBitmap, scaledWidth, scaledHeight, true);


        canvas.save();
        canvas.rotate(startAngle, centerX, centerY);


        float left = centerX - scaledWidth / 2f;
        float top = centerY - scaledHeight / 2f;

        canvas.drawBitmap(scaledBitmap, left, top, null);
        canvas.restore();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX() - centerX;
        float y = event.getY() - centerY;
        float angle = (float) Math.toDegrees(Math.atan2(y, x));
        if (isFlinging) {
            return false; // çark dönerken hiçbir dokunuş işlenmesin
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (CarkHakYonetimi.hakGetir(getContext()) <= 0) {
                    Toast.makeText(getContext(), "Bugünlük çevirme hakkın bitti!", Toast.LENGTH_SHORT).show();
                    return false;
                }
                lastTouchAngle = angle;
                touchStartAngle = angle;
                touchStartTime = System.currentTimeMillis();
                isDragging = true;
                return true;


            case MotionEvent.ACTION_MOVE:
                if (isDragging) {
                    float delta = angle - lastTouchAngle;
                    startAngle += delta;
                    lastTouchAngle = angle;
                    invalidate();
                }
                return true;

            case MotionEvent.ACTION_UP:
                isDragging = false;

                long elapsed = System.currentTimeMillis() - touchStartTime;
                float angleDelta = angle - touchStartAngle;

                if (elapsed > 0) {
                    float velocityPerMs = angleDelta / elapsed;
                    velocity = velocityPerMs * 1300;
                    velocity *= 2f; // momentum katsayısı
                }

                isFlinging = true;
                flingStartTime = System.currentTimeMillis();
                startFling();
                return true;
        }
        return super.onTouchEvent(event);
    }

    private void startFling() {

        if (flinger != null) removeCallbacks(flinger);

        flinger = new Runnable() {
            @Override
            public void run() {
                long elapsed = System.currentTimeMillis() - flingStartTime;
                if (!tickSound.isPlaying()) {
                    tickSound.start();
                }


                if (Math.abs(velocity) > 0.2f || elapsed < MIN_FLING_DURATION) {
                    startAngle += velocity;
                    velocity *= 0.95f; // yavaşlama oranı
                    invalidate();
                    postDelayed(this, 16);
                } else {
                    isFlinging = false;
                    velocity = 0;
                    removeCallbacks(this);
                    determineSelectedSlice();
                }
            }
        };
        post(flinger);
    }

    private void determineSelectedSlice() {
        float normalizedAngle = (360 - (startAngle % 360)) % 360;
        float correctedAngle = (normalizedAngle + 90 + 30) % 360;
        int index = (int) (correctedAngle / 60);

        String[] puanlar = {"100", "50", "200", "100", "50", "200"};
        if (index >= 0 && index < puanlar.length) {
            String kazandiginPuan = puanlar[index];

            if (listener != null) {
                listener.onSpinComplete(kazandiginPuan);
            } else {
                // hak azalt + geçiş
                CarkHakYonetimi.hakAzalt(getContext());
                Intent intent = new Intent(getContext(), SoruActivity.class);
                intent.putExtra("puan", kazandiginPuan);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
            }
        }

    }





}
