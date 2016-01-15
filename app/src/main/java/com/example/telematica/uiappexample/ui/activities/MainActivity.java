package com.example.telematica.uiappexample.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.telematica.uiappexample.R;
import com.example.telematica.uiappexample.ui.fragment.ListFragment;

public class MainActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentFrame(R.id.content_frame);
        switchContent(ListFragment.newInstance(), null);

    }

}
