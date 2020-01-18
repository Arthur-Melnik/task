package com.fourksoft.test.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import com.fourksoft.test.model.DataModel;

import java.util.ArrayList;
import java.util.List;

public class ChartView extends View {

    int colonCount = 1;
    int colonPadding = 5;
    int colonWidth;
    Rect rect = new Rect();
    Paint paint = new Paint();
    private DataModel data;
    private List<Integer> animatedValues;

    public ChartView(Context context, DataModel data) {
        super(context);

        this.data = data;
        colonCount = data.getValues().size();
        animateDataChange();
    }

    public void setDataModel(DataModel newData) {
        this.data = newData;
        colonCount = data.getValues().size();
        animateDataChange();
    }

    private void animateDataChange() {
        animatedValues = new ArrayList<>();

        for (int i = 0; i < data.getValues().size(); i++) {
            animatedValues.add(0);
            createAnimator(i, data.getValues().get(i)).start();

        }
    }

    private ValueAnimator createAnimator(final int position, int value) {

        ValueAnimator animator = ValueAnimator.ofInt(0, value);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                animatedValues.set(position, (int) valueAnimator.getAnimatedValue());
            }
        });

        return animator;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawRGB(255, 255, 255);
        canvas.translate(colonPadding * 4, 0);

        colonWidth = (getWidth() - colonPadding * 8) / colonCount;

        int i = colonCount;

        for (Integer value : animatedValues) {

            paint.setColor(Color.LTGRAY);
            rect.set((colonPadding) + colonWidth * (i - 1), colonPadding, colonWidth * i - colonPadding, getHeight() - colonPadding);
            canvas.drawRect(rect, paint);

            paint.setColor(data.getColor());
            rect.set(colonPadding + colonWidth * (i - 1), colonPadding + (getHeight() / 100) * (100 - value), colonWidth * i - colonPadding, getHeight() - colonPadding);
            canvas.drawRect(rect, paint);
            i--;
        }

        invalidate();
    }

    public DataModel getCurrentDataModel() {
        return data;
    }
}
