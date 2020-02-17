 package com.example.thorineaccount.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.thorineaccount.R;

 /**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {

    private View mRootView;
    public SummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_summary, container,false);
        initView();
        return mRootView;
    }

    private void initView() {
        //余额展示
//        AccountApplication app = (AccountApplication)(this.getActivity().getApplication());
  //      AccountDao dbManager = app.getmDatabaseManager();
        TextView textViewSummary = (TextView) mRootView.findViewById(R.id.textViewSummary);
        double summary = 555/*dbManager.getIncomeSummary(app.currentDateMonth) - dbManager.getOutlaySummary(app.currentDateMonth)*/;
        textViewSummary.setText(String.valueOf(summary));
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
