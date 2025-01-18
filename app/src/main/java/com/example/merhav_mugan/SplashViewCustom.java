package com.example.merhav_mugan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

public class SplashViewCustom extends View {
    private Bitmap logoBitmap;
    private float rotationAngle = 0;
    private float scale = 1.0f;
    private boolean scalingUp = true;
    private float centerX, centerY;

    public SplashViewCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        logoBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo); // עדכן את שם הלוגו
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // צבע רקע
        canvas.drawColor(Color.WHITE);

        // חישוב מרכז המסך
        if (centerX == 0 || centerY == 0) {
            centerX = getWidth() / 2f;
            centerY = getHeight() / 2f;
        }

        // מטריצה לסיבוב והתקרבות
        Matrix matrix = new Matrix();
        matrix.postTranslate(-logoBitmap.getWidth() / 2f, -logoBitmap.getHeight() / 2f);
        matrix.postRotate(rotationAngle);
        matrix.postScale(scale, scale);
        matrix.postTranslate(centerX, centerY);

        // ציור הלוגו
        canvas.drawBitmap(logoBitmap, matrix, null);

        // עדכון זווית הסיבוב
        rotationAngle += 5;
        if (rotationAngle >= 360) {
            rotationAngle = 0;
        }

        // עדכון קנה המידה (התקרבות/התרחקות)
        if (scalingUp) {
            scale += 0.02f;
            if (scale >= 1.5f) {
                scalingUp = false;
            }
        } else {
            scale -= 0.02f;
            if (scale <= 0.8f) {
                scalingUp = true;
            }
        }

        // רענון התצוגה
        postInvalidateDelayed(30);
    }
}
