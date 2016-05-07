package com.zhy.magicviewpager.widget;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * usage: MagicViewPager的Transform类型
 * author: kHRYSTAL
 * create time: 16/5/7
 * update time:
 * email: 723526676@qq.com
 */
public class TransformType {
    /**
     * 多少种Transform类型
     */
    public static final int DEFAULT_TYPE_COUNT = 6;


    @TransformTypeChecker
    public static final int NON_PAGETRANSFORMER = 0;

    @TransformTypeChecker
    public static final int ALPHA_PAGETRANSFORMER = 1;

    @TransformTypeChecker
    public static final int ROATE_DOWN_PAGETRANSFORMER = 2;

    @TransformTypeChecker
    public static final int ROATE_UP_PAGETRANSFORMER = 3;

    @TransformTypeChecker
    public static final int ROTATE_Y_TRANSFORMER = 4;

    @TransformTypeChecker
    public static final int SCALE_IN_TRANSFORMER = 5;

    @IntDef({NON_PAGETRANSFORMER,ALPHA_PAGETRANSFORMER,ROATE_DOWN_PAGETRANSFORMER,ROATE_UP_PAGETRANSFORMER,
                ROTATE_Y_TRANSFORMER,SCALE_IN_TRANSFORMER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TransformTypeChecker{

    }
}
