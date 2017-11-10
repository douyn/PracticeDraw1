package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Practice10HistogramView extends View {

    List<DataModel> datas;

    int mWidth;
    int mHeight;

    Paint mPaint;

    int width = 900;
    int height = 600;

    int widthSpec;
    int itemWidth;

    public Practice10HistogramView(Context context) {
        super(context);
        init();
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        loadData();
        mPaint = new Paint();
        widthSpec = width / (6 * datas.size() + 1);
        itemWidth = widthSpec * 5;
    }

    private void loadData() {
        datas = new ArrayList<>();
        datas.add(new DataModel("Oronro", 0.5f));
        datas.add(new DataModel("Kitti", 0.3f));
        datas.add(new DataModel("Apple", 0.8f));
        datas.add(new DataModel("Barbcu", 0.6f));
        datas.add(new DataModel("Crit", 0.8f));
        datas.add(new DataModel("Diablo", 0.7f));
        datas.add(new DataModel("Elert", 0.1f));
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图

        canvas.translate(mWidth / 2, mHeight / 2);

        // 画一个坐标系
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.WHITE);
        canvas.drawLine(-450, 300, 450, 300, mPaint);
        canvas.drawLine(-450, 300, -450, -300, mPaint);

        // 画各个部分的比例
        for (int i = 0; i < datas.size(); i++) {
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(Color.CYAN);

            float left = 6 * widthSpec * i + widthSpec - 450;
            float top = 300 - (height * (datas.get(i).percent));
            float right = left + itemWidth;
            float bottom = 300;

            Log.d("histogram", "第" + (i+1) + "个直方图: " + "[left] = " + left + ", [right] = " + right + ", [top] = " + top + ", [bottom] = " + bottom);

            RectF rectF = new RectF(left, top, right, bottom);
            canvas.drawRect(rectF, mPaint);

            mPaint.setColor(Color.WHITE);
            mPaint.setTextSize(30);
            mPaint.setAntiAlias(true);
            mPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(datas.get(i).name, left + 50, 300 + 40, mPaint);
        }
    }

    public void setData(List<DataModel> data){
        this.datas = data;
    }

    class DataModel{
        public String name;
        public float percent;

        public DataModel(String name, float percent){
            this.name = name;
            this.percent = percent;
        }
    }
}
