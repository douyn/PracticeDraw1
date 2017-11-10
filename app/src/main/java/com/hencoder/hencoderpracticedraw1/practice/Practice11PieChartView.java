package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Practice11PieChartView extends View {

    private static final int Radius = 200;

    List<DataModel> datas;

    Paint mPaint;

    int mWidth;
    int mHeight;

    public Practice11PieChartView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint();

        loadData();
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    int startDegree = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图

        canvas.translate(mWidth / 2, mHeight / 2);

        for (int i = 0; i < datas.size(); i ++) {

            DataModel dataModel = datas.get(i);
            int degree = (int) (dataModel.percent * 360);

            // 画扇形
            float left = -200;
            float top = -200;
            float right = 200;
            float bottom = 200;

            mPaint.setColor(dataModel.color);
            mPaint.setStyle(Paint.Style.FILL);
            RectF rectF = new RectF(left, top, right, bottom);

            if (dataModel.percent == MAX_PERCENT) {
                canvas.save();
                canvas.translate(-10, -10);
            }

            canvas.drawArc(rectF, startDegree, degree - 2, true, mPaint);

            int halfAngle = (startDegree + degree / 2);

            float lineStartX = (Radius * (float)Math.cos((float)(halfAngle / 180 * Math.PI)));
            float lineStartY = (Radius * (float)Math.sin((float)(halfAngle / 180 * Math.PI)));
            float lineEndX = ((Radius + 50) * (float)Math.cos((float)(halfAngle / 180 * Math.PI)));
            float lineEndY = ((Radius + 50) * (float)Math.sin((float)(halfAngle / 180 * Math.PI)));

            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(Color.WHITE);
            canvas.drawLine(lineStartX, lineStartY, lineEndX, lineEndY, mPaint);

//            if (halfAngle > 90 && halfAngle < 270) {
//                canvas.drawLine(lineEndX, lineEndY, lineEndX - 50, lineEndY, mPaint);
//                mPaint.setTextSize(15);
//                mPaint.setTextAlign(Paint.Align.RIGHT);
//                canvas.drawText(dataModel.name, lineEndX - 50, lineEndY, mPaint);
//            } else {
//                canvas.drawLine(lineEndX, lineEndY, lineEndX + 50, lineEndY, mPaint);
//                mPaint.setTextSize(15);
//                mPaint.setTextAlign(Paint.Align.LEFT);
//                canvas.drawText(dataModel.name, lineEndX + 50, lineEndY, mPaint);
//            }

            if (dataModel.percent == MAX_PERCENT) {
                canvas.restore();
            }

            startDegree = startDegree + degree;
        }


    }

    class DataModel{
        public String name;
        public float percent;
        public int color;

        public DataModel(String name, float percent, int color){
            this.name = name;
            this.percent = percent;
            this.color = color;
        }
    }

    float MAX_PERCENT = Float.MIN_VALUE;

    private void loadData() {
        datas = new ArrayList<>();
        datas.add(new DataModel("Oronro", 0.2f, Color.RED));
        datas.add(new DataModel("Kitti", 0.1f, Color.YELLOW));
        datas.add(new DataModel("Apple", 0.1f, Color.BLUE));
        datas.add(new DataModel("Barbcu", 0.4f, Color.GRAY));
        datas.add(new DataModel("Diablo", 0.1f, Color.GREEN));
        datas.add(new DataModel("Elert", 0.1f, Color.CYAN));


        for (DataModel bean : datas) {
            MAX_PERCENT = Math.max(bean.percent, MAX_PERCENT);
        }
    }
}
