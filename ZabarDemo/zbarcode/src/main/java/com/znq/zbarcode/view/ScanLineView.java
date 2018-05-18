package com.znq.zbarcode.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.znq.zbarcode.R;

/**
 * desc:扫描线的滑动,打算用来替换平移动画
 * Author: znq
 * Date: 2016-11-09 11:25
 */
public class ScanLineView extends View {
    private static final String TAG = "ScanLineView";
    private Context mContext;
    /**
     * 画笔对象的引用
     */
    private Paint mPaint;

    private Rect mRect ;
    private static final long ANIMATION_DELAY =10L;

    private boolean isFirst = true;
    /**
     * 线条的最顶端位置
     */
    private int slideTop;

    /**
     * 线条的最底端位置
     */
    private int slideBottom;

    /**
     * 线条每次刷新移动的距离
     */
    private static final int SPEEN_DISTANCE = 10;

    /**
     * 线的宽度
     */
    private static int MIDDLE_LINE_WIDTH;

    /**
     * 线与扫描框左右的间隙
     */
    private static int MIDDLE_LINE_PADDING;


    public ScanLineView(Context context) {
        super(context);
        init(context);
    }

    public ScanLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScanLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext =context;
        MIDDLE_LINE_PADDING = dip2px(context, 20.0F);
        MIDDLE_LINE_WIDTH = dip2px(context, 2.0F);
        Log.e(TAG, "init: "+getWidth()+":"+getHeight() );
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG); // 开启反锯齿
        getMeasuredWidth();
    }

    public void getRect(Rect rect){
        if(rect==null){
            Log.e(TAG, "getRect: rect is null" );
        }
        Log.e(TAG, "getRect: "+mRect.left);
        mRect = rect;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mRect =new Rect(0,0, getMeasuredWidth(), getMeasuredHeight());

    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (mRect == null) {
            return; // not ready yet, early draw before done configuring
        }
        // 绘制扫描线
        drawScanningLine(canvas, mRect);
        // 只刷新扫描框的内容，其他地方不刷新
        postInvalidateDelayed(ANIMATION_DELAY, mRect.left, mRect.top,
                mRect.right, mRect.bottom);
    }

    /**
     * 绘制扫描线
     *
     * @param canvas 画布
     * @param frame  扫描框
     */
    private void drawScanningLine(Canvas canvas, Rect frame) {

        // 初始化中间线滑动的最上边和最下边
        if (isFirst) {
            isFirst = false;
            slideTop = frame.top;
            slideBottom = frame.bottom;
        }

        // 绘制中间的线,每次刷新界面，中间的线往下移动SPEEN_DISTANCE
        slideTop += SPEEN_DISTANCE;
        if (slideTop >= slideBottom) {
            slideTop = frame.top;
        }

        // 从图片资源画扫描线
        Rect lineRect = new Rect();
        lineRect.left = frame.left + MIDDLE_LINE_PADDING;
        lineRect.right = frame.right - MIDDLE_LINE_PADDING;
        lineRect.top = slideTop;
        lineRect.bottom = (slideTop + MIDDLE_LINE_WIDTH);
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.scan_line), null,
                mRect, mPaint);

        Log.e(TAG, "drawScanningLine: "+"" );

    }

    /**
     * dp转px
     *
     * @param context 上下文
     * @param dipValue dp值
     * @return px值
     */
    public int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}
