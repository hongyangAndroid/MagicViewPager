package com.zhy.magicviewpager.transformer;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

public class RotateYTransformer extends BasePageTransformer
{
    private static final float DEFAULT_MAX_ROTATE = 35f;
    private float mMaxRotate = DEFAULT_MAX_ROTATE;


    public RotateYTransformer()
    {
    }

    public RotateYTransformer(float maxRotate)
    {
        this(maxRotate, NonPageTransformer.INSTANCE);
    }

    public RotateYTransformer( ViewPager.PageTransformer pageTransformer)
    {
        this(DEFAULT_MAX_ROTATE, pageTransformer);
    }

    public RotateYTransformer(float maxRotate, ViewPager.PageTransformer pageTransformer)
    {
        mMaxRotate = maxRotate;
        mPageTransformer = pageTransformer;
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void pageTransform(View view, float position)
    {
        if (position < -1)
        { // [-Infinity,-1)
            // This page is way off-screen to the left.
//            view.setRotationY(-1 * mMaxRotate);
//            view.setPivotX(view.getWidth());
//            view.setPivotX(view.getWidth());
            ViewCompat.setRotationY(view,-1 * mMaxRotate);
            ViewCompat.setPivotX(view,view.getWidth());
            ViewCompat.setPivotX(view,view.getWidth());
        } else if (position <= 1)
        { // [-1,1]
            // Modify the default slide transition to shrink the page as well

//            view.setRotationY(position * mMaxRotate);
            ViewCompat.setRotationY(view,position * mMaxRotate);
            if (position < 0)//[0,-1]
            {
//                view.setPivotX(view.getWidth() * (DEFAULT_CENTER + DEFAULT_CENTER * (-position)));
//                view.setPivotX(view.getWidth());
                ViewCompat.setPivotX(view,view.getWidth() * (DEFAULT_CENTER + DEFAULT_CENTER * (-position)));
                ViewCompat.setPivotX(view,view.getWidth());
            } else//[1,0]
            {
//                view.setPivotX(view.getWidth() * DEFAULT_CENTER * (1 - position));
//                view.setPivotX(0);
                ViewCompat.setPivotX(view,view.getWidth() * DEFAULT_CENTER * (1 - position));
                ViewCompat.setPivotX(view,0);
            }

            // Scale the page down (between MIN_SCALE and 1)
        } else
        { // (1,+Infinity]
            // This page is way off-screen to the right.
//            view.setRotationY(1 * mMaxRotate);
//            view.setPivotX(0);
            ViewCompat.setRotationY(view, 1 * mMaxRotate);
            ViewCompat.setPivotY(view,0);
        }
    }
}
