package in.com.decipherzone.tamilcolorado.fragment.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import in.com.decipherzone.base.BaseAppCompatActivity;
import in.com.decipherzone.base.MainView;
import in.com.decipherzone.base.TouchEffectImageViewSrc;
import in.com.decipherzone.base.TouchEffectOnViewBackground;
import in.com.decipherzone.tamilcolorado.ApplicationContext;

public class BaseFragment extends Fragment implements OnClickListener, OnCheckedChangeListener, MainView {

    public static final TouchEffectOnViewBackground TOUCH = new TouchEffectOnViewBackground();
    public static final TouchEffectImageViewSrc imageTouch = new TouchEffectImageViewSrc();

    public int fragmentType;
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

    public void onFloatingButtonClick() {

    }

    @Override
    public void onResume() {
        super.onResume();
//        ((MainActivity) activity).lockDrawer();
//        ((MainActivity) activity).showHideFloatingButton(false);
        ApplicationContext.getInstance().setLiveFragment(this);
    }

    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Method use to set Touch and Click listener on View.
     *
     * @param id   Resource id of View
     * @param view your layout view.
     * @return
     */
    public View setTouchNClick(int id, View view) {

        View v = view.findViewById(id);
        v.setOnClickListener(this);
        v.setOnTouchListener(TOUCH);
        return v;
    }

    public View setTouchNClickSrc(int id, View view) {
        View v = view.findViewById(id);
        v.setOnClickListener(this);
        v.setOnTouchListener(imageTouch);
        return v;
    }

    /**
     * Method use to set Click listener on View.
     *
     * @param id   Resource id of View
     * @param view your layout view.
     * @return
     */
    public View setClick(int id, View view) {

        View v = view.findViewById(id);
        v.setOnClickListener(this);
        return v;
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

    /**
     * Method use to enable/disable view.
     *
     * @param id   resource id.
     * @param view
     * @param flag flag true if you want to make view enable else false
     */
    public void setViewEnable(int id, View view, boolean flag) {
        View v = view.findViewById(id);
        v.setEnabled(flag);
    }

    /**
     * Method use to set ViewVisiblity
     *
     * @param id   id resource id of View.
     * @param view
     * @param flag flag value can be VISIBLE, GONE, INVISIBLE.
     */
    public void setViewVisibility(int id, View view, int flag) {
        View v = view.findViewById(id);
        v.setVisibility(flag);
    }


    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
//		Intent i  = null;
        switch (arg0.getId()) {

        }
    }

    /**
     * Method use to set text on TextView, EditText, Button.
     *
     * @param id   resource of TextView, EditText, Button.
     * @param text string you want to set on TextView, EditText, Button
     * @param view
     */
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

    /**
     * Method use to get Text from TextView, EditText, Button.
     *
     * @param id   resource id TextView, EditText, Button
     * @param view
     * @return string text from view
     */
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


    /**
     * Method use to put focus on EditText.
     *
     * @param id   resourceid of EditText.
     * @param view
     */
    public void setEditTextFocus(int id, View view) {
        EditText et = (EditText) view.findViewById(id);
        et.requestFocus();
    }

    /**
     * To check whether ToggleButton is checked or not
     *
     * @param id   resource id ToggleButton
     * @param view
     * @return true if ToggleButton is checked else false
     */
    public boolean isToggleButtonChecked(int id, View view) {
        ToggleButton cb = (ToggleButton) view.findViewById(id);
        return cb.isChecked();
    }

    /**
     * Method use to add Checkbox listener on CheckBox
     *
     * @param id   resource id of checkbox
     * @param view
     */
    public void setCheckBoxCheckListener(int id, View view) {

        CheckBox cb = (CheckBox) view.findViewById(id);
        cb.setOnCheckedChangeListener(this);
    }

    /**
     * Method use to check checkbox
     *
     * @param id      resourceid of CheckBox
     * @param checked true for checked and else for unchecked.
     * @param view
     */
    public void setCheckBoxCheck(int id, boolean checked, View view) {
        CheckBox cb = (CheckBox) view.findViewById(id);
        cb.setChecked(checked);
    }

    /**
     * Method use to add ToggleButtonListner
     *
     * @param id   resource id of Togglebutton
     * @param view
     */
    public void setToggleButtonListner(int id, View view) {
        ToggleButton cb = (ToggleButton) view.findViewById(id);
        cb.setOnCheckedChangeListener(this);
    }


    /**
     * To check whether checkbox is checked or not
     *
     * @param id   resouce id of checkbox
     * @param view
     * @return true if checkbox is checked else false
     */
    public boolean isCheckBoxChecked(int id, View view) {
        CheckBox cb = (CheckBox) view.findViewById(id);
        return cb.isChecked();
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        // TODO Auto-generated method stub

    }

    /**
     * Method use to select/unselect view.
     *
     * @param id   resource id.
     * @param view
     * @param flag flag true if you want to make view selected else false
     */
    public void setViewSelected(int id, View view, boolean flag) {
        View v = view.findViewById(id);
        v.setSelected(flag);
    }

//	public void customizedActionBar(View view, String title){
//		TextView barTitle = (TextView) view.findViewById(R.id.drawer_title);
//		if(title != null && !title.equals(""))
//			barTitle.setText(title);
//		else
//			barTitle.setText("WhoAround ?");
////		ImageView drawerIcon = (ImageView) mCustomView.findViewById(R.id.drawer_icon);
//		ImageView drawerIcon = (ImageView) view.findViewById(R.id.drawer_icon);
//		drawerIcon.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				((WhoAroundHome)getActivity()).toggelDrawer();
//			}
//		});
//	}


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void doAfterSaveUser() {

    }

}
