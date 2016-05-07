package com.zhy.magicviewpager.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.zhy.magicviewpager.R;
import com.zhy.magicviewpager.transformer.AlphaPageTransformer;
import com.zhy.magicviewpager.transformer.BasePageTransformer;
import com.zhy.magicviewpager.transformer.NonPageTransformer;
import com.zhy.magicviewpager.transformer.RotateDownPageTransformer;
import com.zhy.magicviewpager.transformer.RotateUpPageTransformer;
import com.zhy.magicviewpager.transformer.RotateYTransformer;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

/**
 * usage: 当ViewPager中Fragment需要纵向滑动 同时需要加入transform时 使用该类
 * author: kHRYSTAL
 * create time: 16/5/7
 * update time:
 * email: 723526676@qq.com
 */
@SuppressWarnings("ResourceType")
public class MagicViewPager extends FrameLayout implements OnPageChangeListener{


    private int mPagerMargin = 0;
    private int mPaddingLeft = 130;
    private int mPaddingRight = 130;
    private int mPageLimit = 3;
    @TransformType.TransformTypeChecker private int mTransformType = TransformType.NON_PAGETRANSFORMER;


    private Hacky mPager;
    boolean mNeedRedraw = false;
    private Point mCenter = new Point();
    private Point mInitialTouch = new Point();
    private BasePageTransformer mPageTransformer;
    private OnPageChangeListener mListener;


    public MagicViewPager(Context context) {
        this(context,null,0);
    }

    public MagicViewPager(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    @SuppressWarnings("ResourceType")
    public MagicViewPager(Context context,AttributeSet attrs,int defStyle){
        super(context, attrs,defStyle);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.MagicViewPager);
        mPageLimit = a.getInteger(
                R.styleable.MagicViewPager_offscreenPageLimit, mPageLimit);
        mPagerMargin = a.getInteger(R.styleable.MagicViewPager_pagerMargin,
                mPagerMargin);
        mPaddingLeft = a.getInteger(R.styleable.MagicViewPager_paddingLeft,
                mPaddingLeft);
        mPaddingRight = a.getInteger(R.styleable.MagicViewPager_paddingRight,
                mPaddingRight);
        mTransformType = a.getInteger(R.styleable.MagicViewPager_transformType,TransformType.NON_PAGETRANSFORMER);
        a.recycle();
        View.inflate(context,R.layout.magic_view_pager,this);
        mPager = (Hacky) findViewById(R.id.real_page);
        mPager.setOffscreenPageLimit(mPageLimit);
        mPager.setPageMargin(mPagerMargin);
        mPager.setOnPageChangeListener(this);
        setDefaultTransform(mTransformType);
        init(context);
    }



    public void setTransformType(@TransformType.TransformTypeChecker int type){
        mTransformType = type;
        setDefaultTransform(type);
    }

    private void setDefaultTransform(@TransformType.TransformTypeChecker int type) {
        switch (type){
            case TransformType.ALPHA_PAGETRANSFORMER:
                mPageTransformer = new AlphaPageTransformer();
                break;
            case TransformType.ROATE_DOWN_PAGETRANSFORMER:
                mPageTransformer = new RotateDownPageTransformer();
                break;
            case TransformType.ROATE_UP_PAGETRANSFORMER:
                mPageTransformer = new RotateUpPageTransformer();
                break;
            case TransformType.ROTATE_Y_TRANSFORMER:
                mPageTransformer = new RotateYTransformer();
                break;
            case TransformType.SCALE_IN_TRANSFORMER:
                mPageTransformer = new ScaleInTransformer();
                break;
            default:
                mPageTransformer = null;
                break;
        }
        requestLayout();
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void init(Context context) {
        setClipChildren(false);
        if (Build.VERSION.SDK_INT >= 11)
            setLayerType(View.LAYER_TYPE_SOFTWARE,null);
    }

    public Hacky getViewPager(){
        return mPager;
    }

    public void setAdapter(PagerAdapter adapter){
        if (mPager != null)
            mPager.setAdapter(adapter);
        else throw new NullPointerException("MagicViewPager's Hacky is null");
    }

    public void setMagicOffscreenPageLimit(int pagerDis) {
        if (pagerDis < 3) {
            throw new IllegalArgumentException(
                    "the offscreen page limit must >3");
        }
        if (mPager!=null)
            mPager.setOffscreenPageLimit(pagerDis);
    }

    public void setMagicPagerMargin(int pagerMargin) {
        mPager.setPageMargin(pagerMargin);
    }

    public void setPageTransformer(BasePageTransformer transformer) {
        mPageTransformer = transformer;
    }

    public BasePageTransformer getPageTransformer() {
        return mPageTransformer;
    }

    public void setOnPageChangeListener(OnPageChangeListener listener){
        mListener = listener;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenter.x = w/2;
        mCenter.y = h/2;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mInitialTouch.x = (int)event.getX();
                mInitialTouch.y = (int)event.getY();
                break;
            default:
                event.offsetLocation(mCenter.x - mInitialTouch.x,mCenter.y - mInitialTouch.y);
                break;
        }
        return mPager.dispatchTouchEvent(event);
    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        final int scrollX = mPager.getScrollX();
        final int childCount = mPager.getChildCount();
        if (mPageTransformer!=null) {
            for (int i = 0; i < childCount; i++) {
                final View child = mPager.getChildAt(i);
                final ViewPager.LayoutParams lp = (ViewPager.LayoutParams) child.getLayoutParams();
                if (lp.isDecor)
                    continue;
                final float transformPos = (float) (child.getLeft() - scrollX) / child.getWidth();
                mPageTransformer.transformPage(child, transformPos);
            }
        }else {
            for (int i = 0; i < childCount; i++) {
                final View child = mPager.getChildAt(i);
                child.requestLayout();
            }
        }
        if (mNeedRedraw)
            invalidate();
        if (mListener != null)
            mListener.onPageScrolled(position,positionOffset,positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        if (mListener != null)
            mListener.onPageSelected(position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        mNeedRedraw = (state != ViewPager.SCROLL_STATE_IDLE);
        if (mListener != null)
            mListener.onPageScrollStateChanged(state);
    }


    public interface OnPageChangeListener{

        void onPageScrolled(int position,float positionOffset,int positionOffsetPixels);

        void onPageSelected(int position);

        void onPageScrollStateChanged(int state);
    }
}
