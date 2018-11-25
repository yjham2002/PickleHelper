package kr.co.picklecode.helper.helper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {

    private static final String UPDATE_INTENT = "UPDATE_INTENT_FROM_ACTIVITY";

    private View rootView;

    private RecyclerView mRecyclerView;
    private ArticleAdapter mAdapter;

    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        
        initView();

        return rootView;
    }

    private void initView(){
        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        mAdapter = new ArticleAdapter(rootView.getContext(), R.layout.layout_article);
        mRecyclerView.setAdapter(mAdapter);
        layoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        loadList();
    }

    public void onResume(){
        super.onResume();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReceiver, new IntentFilter(UPDATE_INTENT));
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            switch (intent.getAction()){
                case UPDATE_INTENT: loadList(); break;
                default: break;
            }
        }
    };

    @Override
    public void onPause() {
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mMessageReceiver);
        super.onPause();
    }

    public void loadList(){
        mAdapter.mListData.clear();

    }

}
