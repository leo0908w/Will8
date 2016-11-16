package com.org.iii.will08;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class GameView extends View {
    private Resources res;
    private Context context;
    private int viewW, viewH;
    private boolean isInit;
    private Bitmap bmpBall;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

//        setBackgroundColor(Color.YELLOW);
        this.context = context;
        res = context.getResources();
    }

    private void init() {
        viewH = getHeight();
        viewW = getWidth();
        Log.v("will", viewW + ",, " + viewH);
        bmpBall = BitmapFactory.decodeResource(res, R.drawable.b0);
        isInit = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!isInit) init();
        canvas.drawBitmap(bmpBall, 0, 0, null);
    }
}
