package com.zhy.magicviewpager.sample;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zhy.magicviewpager.transformer.AlphaPageTransformer;
import com.zhy.magicviewpager.transformer.NonPageTransformer;
import com.zhy.magicviewpager.transformer.RotateDownPageTransformer;
import com.zhy.magicviewpager.transformer.RotateUpPageTransformer;
import com.zhy.magicviewpager.transformer.RotateYTransformer;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;
import com.zhy.magicviewpager.widget.MagicViewPager;

import java.util.ArrayList;

public class SupportScrollActivity extends AppCompatActivity {

    private ArrayList<String> list;
    private MagicViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_activity);
        mViewPager = (MagicViewPager) findViewById(R.id.viewpager);
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(""+i);
        }

        SimplePagerAdapter adapter = new SimplePagerAdapter(getSupportFragmentManager());
        mViewPager.getViewPager().setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public class SimplePagerAdapter  extends FragmentPagerAdapter {


        public SimplePagerAdapter(FragmentManager fm) {
            super(fm);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        String[] effects = this.getResources().getStringArray(R.array.magic_effect);
        for (String effect : effects)
            menu.add(effect);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        String title = item.getTitle().toString();
        if ("RotateDown".equals(title))
        {
            mViewPager.setPageTransformer(new RotateDownPageTransformer());
        } else if ("RotateUp".equals(title))
        {
            mViewPager.setPageTransformer(new RotateUpPageTransformer());
        } else if ("RotateY".equals(title))
        {
            mViewPager.setPageTransformer(new RotateYTransformer());
        } else if ("Standard".equals(title))
        {
            mViewPager.setClipChildren(false);
            mViewPager.setPageTransformer(null);
        } else if ("Alpha".equals(title))
        {
            mViewPager.setClipChildren(false);
            mViewPager.setPageTransformer(new AlphaPageTransformer());
        } else if ("ScaleIn".equals(title))
        {
            mViewPager.setPageTransformer(new ScaleInTransformer());
        } else if ("RotateDown and Alpha".equals(title))
        {
            mViewPager.setPageTransformer(new RotateDownPageTransformer(new AlphaPageTransformer()));
        }else if ("RotateDown and Alpha And ScaleIn".equals(title))
        {
            mViewPager.setPageTransformer(new RotateDownPageTransformer(new AlphaPageTransformer(new ScaleInTransformer())));
        }else if ("Add Transform Can Vertical Scroll".equals(title))
        {
            onBackPressed();
        }

        setTitle(title);

        return true;
    }
}
