package com.zhy.magicviewpager.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * usage:
 * author: kHRYSTAL
 * create time: 16/5/7
 * update time:
 * email: 723526676@qq.com
 */
public class Hacky extends ViewPager{

    private boolean isLocked;

    public Hacky(Context context) {
        super(context);
        isLocked = false;
    }

    public Hacky(Context context, AttributeSet attrs) {
        super(context, attrs);
        isLocked = false;
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        if (!isLocked){
            try {
                return super.onInterceptHoverEvent(event);
            }catch (IllegalArgumentException e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return !isLocked && super.onTouchEvent(ev);
    }

    public void toggleLock(){
        isLocked = !isLocked;
    }

    public void setLocked(boolean isLocked){
        this.isLocked = isLocked;
    }

    public boolean isLocked(){
        return isLocked;
    }

}
