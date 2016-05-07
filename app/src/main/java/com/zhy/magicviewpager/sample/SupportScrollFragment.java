package com.zhy.magicviewpager.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.magicviewpager.transformer.AlphaPageTransformer;
import com.zhy.magicviewpager.transformer.RotateDownPageTransformer;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;
import com.zhy.magicviewpager.widget.MagicViewPager;
import com.zhy.magicviewpager.widget.TransformType;

import java.util.ArrayList;

/**
 * usage:
 * author: kHRYSTAL
 * create time: 16/5/7
 * update time:
 * email: 723526676@qq.com
 */
public class SupportScrollFragment extends Fragment{

    public static final String TRANSFORM_TYPE = "transform_type";

    private ArrayList<String> list;
    private MagicViewPager mViewPager;
    private SimplePagerAdapter mAdapter;
    private static int mTransformType;


    public static Fragment getInstance(Bundle bundle) {
        SupportScrollFragment fragment = new SupportScrollFragment();
        mTransformType = bundle.getInt(TRANSFORM_TYPE);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.support_scroll_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        mViewPager = (MagicViewPager)view.findViewById(R.id.viewpager);
        list = new ArrayList<>();
        mAdapter = new SimplePagerAdapter(getChildFragmentManager());

        mViewPager.setAdapter(mAdapter);
        mViewPager.setMagicPagerMargin(20);
        setTransForm(mTransformType);
    }

    private void setTransForm(int type) {
        Log.e("SupportFragment",""+type);
        switch (type){
            case 0:
                mViewPager.setTransformType(TransformType.NON_PAGETRANSFORMER);
                break;
            case 1:
                mViewPager.setTransformType(TransformType.ALPHA_PAGETRANSFORMER);
                break;
            case 2:
                mViewPager.setTransformType(TransformType.ROATE_DOWN_PAGETRANSFORMER);
                break;
            case 3:
                mViewPager.setTransformType(TransformType.ROATE_UP_PAGETRANSFORMER);
                break;
            case 4:
                mViewPager.setTransformType(TransformType.ROTATE_Y_TRANSFORMER);
                break;
            case 5:
                mViewPager.setTransformType(TransformType.SCALE_IN_TRANSFORMER);
                break;
            case 6:
                mViewPager.setPageTransformer(new RotateDownPageTransformer(new AlphaPageTransformer()));
                break;
            case 7:
                mViewPager.setPageTransformer(new RotateDownPageTransformer(new AlphaPageTransformer(new ScaleInTransformer())));

                break;
        }
    }

    public class SimplePagerAdapter  extends FragmentPagerAdapter {


        public SimplePagerAdapter(FragmentManager fm) {
            super(fm);
            Log.e("SimplePagerAdapter","init");
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        }

        @Override
        public Fragment getItem(int position) {
            return ScrollFragment.getInstance();
        }
    }
}
