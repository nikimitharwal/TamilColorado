package in.com.decipherzone.tamilcolorado.fragment.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.com.decipherzone.tamilcolorado.R;
import in.com.decipherzone.tamilcolorado.activity.main.MainActivity;
import in.com.decipherzone.tamilcolorado.fragment.base.BaseFragment;

public class HistoryFragment extends BaseFragment {

    View fragmentView;
    MainActivity mainActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_history, container, false);
        initializeView();
        return fragmentView;
    }

    @Override
    public void initializeView() {
        if (getActivity() instanceof MainActivity) {
            mainActivity = (MainActivity) getActivity();
        }
        mainActivity.setActionBarTitle(activity.getString(R.string.title_history));
    }
}
