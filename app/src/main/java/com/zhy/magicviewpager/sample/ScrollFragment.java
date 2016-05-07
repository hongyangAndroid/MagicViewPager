package com.zhy.magicviewpager.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * usage:
 * author: kHRYSTAL
 * create time: 16/5/7
 * update time:
 * email: 723526676@qq.com
 */
public class ScrollFragment extends Fragment{

    public static Fragment getInstance() {
        ScrollFragment fragment = new ScrollFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.scroll_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        ListView listView = (ListView)view.findViewById(R.id.list_view);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(""+i);
        }
        SimpleAdapter adapter = new SimpleAdapter(getContext(),list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public class SimpleAdapter extends BaseAdapter {

        private ArrayList<String> list;
        private Context context;

        public SimpleAdapter(Context context,ArrayList<String> list){
            this.list = list;
            this.context = context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
                holder = new ViewHolder();
                holder.textView = (TextView)convertView.findViewById(R.id.text);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.textView.setText(list.get(position));
            return convertView;
        }
    }

    public class ViewHolder{
        public TextView textView;
    }
}
