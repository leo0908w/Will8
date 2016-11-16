package com.org.iii.will08;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class GameView extends View {
    private Resources res;
    private Context context;
    private int viewW, viewH;
    private boolean isInit;
    private Bitmap bmpBall;
    private Matrix matrix;
    private float ballW, ballH, ballX, ballY, dx, dy;
    Timer timer;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

//        setBackgroundColor(Color.YELLOW);
        this.context = context;
        res = context.getResources();
        matrix = new Matrix();
        timer = new Timer();
    }

    private void init() {
        viewH = getHeight();
        viewW = getWidth();
        Log.v("will", viewW + ",, " + viewH);

        dx = 10;    dy = 10;
        bmpBall = BitmapFactory.decodeResource(res, R.drawable.b0);
        ballW = viewW / 12f;    ballH = ballW;
        bmpBall = resizeBmp(bmpBall, ballW, ballH);

        timer.schedule(new BallTask(), 1000, 60);

        isInit = true;
    }
    

    private class BallTask extends TimerTask {
        @Override
        public void run() {
            if (ballX < 0 || ballX + ballW > viewW) {
                dx *= -1;
            }
            if (ballY < 0 || ballY + ballH > viewH) {
                dy *= -1;
            }
            ballX += dx;    ballY += dy;
            postInvalidate();
        }
    }

    Bitmap resizeBmp(Bitmap src, float w, float h) {
        matrix.reset();
        matrix.postScale(ballW / bmpBall.getWidth(), ballH / bmpBall.getHeight());
        return Bitmap.createBitmap(
                bmpBall, 0, 0, bmpBall.getWidth(), bmpBall.getHeight(), matrix, false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!isInit) init();
        canvas.drawBitmap(bmpBall, ballX, ballY, null);
    }
}
