package com.qmy.mtablayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/3/18 0018.
 */

public class MainFragment extends Fragment {

    private static String TAG = "tag";
    private TextView textView;
    public static MainFragment newInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(TAG, position);
        MainFragment fragment = new MainFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(getView());
    }

    private void initView(View view) {
        textView = (TextView) view.findViewById(R.id.textView);
        textView.setText("选项卡"+getArguments().getInt(TAG));
    }
}
