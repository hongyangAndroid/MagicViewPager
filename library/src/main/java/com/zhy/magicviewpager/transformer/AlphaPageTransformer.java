package com.zhy.magicviewpager.transformer;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

public class AlphaPageTransformer extends BasePageTransformer
{
    private static final float DEFAULT_MIN_ALPHA = 0.5f;
    private float mMinAlpha = DEFAULT_MIN_ALPHA;

    public AlphaPageTransformer()
    {
    }

    public AlphaPageTransformer(float minAlpha)
    {
        this(minAlpha, NonPageTransformer.INSTANCE);
    }

    public AlphaPageTransformer(ViewPager.PageTransformer pageTransformer)
    {
        this(DEFAULT_MIN_ALPHA, pageTransformer);
    }

    public AlphaPageTransformer(float minAlpha, ViewPager.PageTransformer pageTransformer)
    {
        mMinAlpha = minAlpha;
        mPageTransformer = pageTransformer;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void pageTransform(View view, float position)
    {
        if (position < -1)
        { // [-Infinity,-1)
//            view.setAlpha(mMinAlpha);
            ViewCompat.setAlpha(view,mMinAlpha);
        } else if (position <= 1)
        { // [-1,1]

            if (position < 0) //[0，-1]
            {           //[1,min]
//                view.setAlpha(mMinAlpha + (1 - mMinAlpha) * (1 + position));
                ViewCompat.setAlpha(view,mMinAlpha + (1 - mMinAlpha) * (1 + position));
            } else//[1，0]
            {
                //[min,1]
//                view.setAlpha(mMinAlpha + (1 - mMinAlpha) * (1 - position));
                ViewCompat.setAlpha(view,mMinAlpha + (1 - mMinAlpha) * (1 - position));
            }
        } else
        { // (1,+Infinity]
//            view.setAlpha(mMinAlpha);
            ViewCompat.setAlpha(view,mMinAlpha + (1 - mMinAlpha) * (1 - position));
        }
    }
}
