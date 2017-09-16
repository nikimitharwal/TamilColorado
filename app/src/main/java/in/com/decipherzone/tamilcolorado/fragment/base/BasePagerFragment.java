package in.com.decipherzone.tamilcolorado.fragment.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import in.com.decipherzone.base.BaseAppCompatActivity;
import in.com.decipherzone.base.MainView;


public class BasePagerFragment extends Fragment implements OnClickListener, MainView {

    public BaseAppCompatActivity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseAppCompatActivity) {
            activity = (BaseAppCompatActivity) context;
        }
    }

    public void makeToast(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void initializeView() {

    }

    @Override
    public void showProgressBar() {
        activity.showProgressingView();
    }

    @Override
    public void hideProgressBar() {
        activity.hideProgressingView();
    }

    public void setViewEnable(int id, View view, boolean flag) {
        View v = view.findViewById(id);
        v.setEnabled(flag);
    }

    public void onFloatingButtonClick() {

    }

    public void refreshScreen() {

    }

    public void afterPermissionGranted() {
    }

    public View setTouchNClick(int id, View view) {
        View v = view.findViewById(id);
        v.setOnClickListener(this);
        v.setOnTouchListener(BaseAppCompatActivity.TOUCH);
        return v;
    }

    public View setTouchNClickSrc(int id, View view) {
        View v = view.findViewById(id);
        v.setOnClickListener(this);
        v.setOnTouchListener(BaseAppCompatActivity.imageTouch);
        return v;
    }

    public View setClick(int id, View view) {
        View v = view.findViewById(id);
        v.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View arg0) {
    }

    public void setViewVisibility(int id, View view, int flag) {
        View v = view.findViewById(id);
        v.setVisibility(flag);
    }

    public void setViewText(int id, String text, View view) {
        View v = ((View) view.findViewById(id));
        if (v instanceof TextView) {
            TextView tv = (TextView) v;
            tv.setText(text);
        }
        if (v instanceof EditText) {
            EditText et = (EditText) v;
            et.setText(text);
        }
        if (v instanceof Button) {
            Button btn = (Button) v;
            btn.setText(text);
        }

    }

    public String getViewText(int id, View view) {
        String text = "";
        View v = ((View) view.findViewById(id));
        if (v instanceof TextView) {
            TextView tv = (TextView) v;
            text = tv.getText().toString().trim();
        }
        if (v instanceof EditText) {
            EditText et = (EditText) v;
            text = et.getText().toString().trim();
        }
        if (v instanceof Button) {
            Button btn = (Button) v;
            text = btn.getText().toString().trim();
        }
        return text;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
