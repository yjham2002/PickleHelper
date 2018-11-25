package kr.co.picklecode.helper.helper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TutorialFragment extends Fragment {

    View rootView;
    private TextView tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.fragment_tutorial, container, false);

        tv = rootView.findViewById(R.id.tutorialview);

        int position = getArguments().getInt("key");

        tv.setText(Integer.toString(position));


        return rootView;
    }
}