package com.zhy.magicviewpager.sample;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class SupportScrollActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support_scroll_activity);
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
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        if ("RotateDown".equals(title))
        {
            bundle.putInt(SupportScrollFragment.TRANSFORM_TYPE,2);
        } else if ("RotateUp".equals(title))
        {
            bundle.putInt(SupportScrollFragment.TRANSFORM_TYPE,3);
        } else if ("RotateY".equals(title))
        {
            bundle.putInt(SupportScrollFragment.TRANSFORM_TYPE,4);
        } else if ("Standard".equals(title))
        {
            bundle.putInt(SupportScrollFragment.TRANSFORM_TYPE,0);
        } else if ("Alpha".equals(title))
        {
            bundle.putInt(SupportScrollFragment.TRANSFORM_TYPE,1);
        } else if ("ScaleIn".equals(title))
        {
            bundle.putInt(SupportScrollFragment.TRANSFORM_TYPE,5);
        } else if ("RotateDown and Alpha".equals(title))
        {
            bundle.putInt(SupportScrollFragment.TRANSFORM_TYPE,6);
        }else if ("RotateDown and Alpha And ScaleIn".equals(title))
        {
            bundle.putInt(SupportScrollFragment.TRANSFORM_TYPE,7);
        }else if ("Add Transform Can Vertical Scroll".equals(title))
        {
            onBackPressed();
        }
        fragmentTransaction.replace(R.id.fragment_container,SupportScrollFragment.getInstance(bundle));
        fragmentTransaction.commit();
        setTitle(title);
        return true;
    }
}
