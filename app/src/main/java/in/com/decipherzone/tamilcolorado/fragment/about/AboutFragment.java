package in.com.decipherzone.tamilcolorado.fragment.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.com.decipherzone.tamilcolorado.R;
import in.com.decipherzone.tamilcolorado.activity.main.MainActivity;
import in.com.decipherzone.tamilcolorado.fragment.base.BaseFragment;

public class AboutFragment extends BaseFragment {

    @BindView(R.id.txt_headingAF)
    TextView txt_headingAF;

    View fragmentView;
    MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this, fragmentView);
        initializeView();
        return fragmentView;
    }

    @Override
    public void initializeView() {
        if (getActivity() instanceof MainActivity) {
            mainActivity = (MainActivity) getActivity();
        }

        mainActivity.setActionBarTitle(activity.getString(R.string.title_about));
    }
}
