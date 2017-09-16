package in.com.decipherzone.base;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import org.json.JSONObject;

import in.com.decipherzone.tamilcolorado.R;


/**
 * {@link BaseAppCompatActivity} send an e-mail with some debug information
 * to the developer.
 *
 * @author GladiatoR
 */
public class BaseAppCompatActivity extends BaseAppCompatSecondaryActivity implements OnClickListener {
    public static final TouchEffectOnViewBackground TOUCH = new TouchEffectOnViewBackground();
    public static final TouchEffectImageViewSrc imageTouch = new TouchEffectImageViewSrc();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(BaseAppCompatActivity.this));

    }

    // Setting up status bar color according to theme
    public void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window w = getWindow();
//            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    // Set status bar color transparent
    public void setStatusBarTransperrent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //status bar height
//            int actionBarHeight = getActionBarHeight();
//            int statusBarHeight = getStatusBarHeight();
            //action bar height
//            statusBar.getLayoutParams().height = actionBarHeight + statusBarHeight;
//            statusBar.setBackgroundColor(color);
        }
    }

    private void crossfade(final View viewToBeHide, final View viewToBeShow) {
        // Set the content view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
        viewToBeShow.setAlpha(0f);
        viewToBeShow.setVisibility(View.VISIBLE);

        // Animate the content view to 100% opacity, and clear any animation
        // listener set on the view.
        viewToBeShow.animate()
                .alpha(1f)
                .setDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .setListener(null);

        // Animate the loading view to 0% opacity. After the animation ends,
        // set its visibility to GONE as an optimization step (it won't
        // participate in layout passes, etc.)
        viewToBeHide.animate()
                .alpha(0f)
                .setDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        viewToBeHide.setVisibility(View.GONE);
                    }
                });
    }


    public void doNothing(View view) {

    }

    // Set onclick and return view by getting id
    public View setClick(int id) {
        View v = findViewById(id);
        v.setOnClickListener(this);
        return v;
    }

    // Set onclick and touch and return view by getting id
    public View setTouchNClick(int id) {
        View v = findViewById(id);
        v.setOnClickListener(this);
        v.setOnTouchListener(TOUCH);
        return v;
    }

    // Set onclick and touch on images and return view by getting id
    public View setTouchNClickSrc(int id) {
        View v = findViewById(id);
        v.setOnClickListener(this);
        v.setOnTouchListener(imageTouch);
        return v;
    }

    @Override
    public void onClick(View v) {
    }

    // set Text of textview and edittext by value
    public View setText(int id, String value) {
        View v = findViewById(id);
        ((TextView) v).setText(value);
        return v;
    }

    // set Text of textview and edittext by string key
    public View setText(int id, int value) {
        View v = findViewById(id);
        ((TextView) v).setText(value);
        return v;
    }

    ViewGroup progressView;
    private boolean isProgressShowing = false;

    // show progressing view on the screen
    public void showProgressingView() {
        if (!isProgressShowing) {
            isProgressShowing = true;
            progressView = (ViewGroup) getLayoutInflater().inflate(R.layout.progressbar_layout, null);
            View v = this.findViewById(android.R.id.content).getRootView();
            ViewGroup viewGroup = (ViewGroup) v;
            viewGroup.addView(progressView);
            try {
                progressView.findViewById(R.id.relativeLayout).setOnClickListener(this);
            } catch (Exception e) {
//                ApplicationContext.getInstance().logExceptionRecord(e, getClass());
            }
        }
    }

    // hid progressing view from the screen
    public void hideProgressingView() {
        View v = this.findViewById(android.R.id.content).getRootView();
        ViewGroup viewGroup = (ViewGroup) v;
        viewGroup.removeView(progressView);
        isProgressShowing = false;
    }

    public boolean isProgressShowing() {
        return isProgressShowing;
    }

    @Override
    public void onBackPressed() {
        if (!isProgressShowing)
            super.onBackPressed();
    }

    public static final int PICK_CONTACT = 3210;

    public void launchGetContactIntent() {
        getContactReadPermission(new RequestPermissionAction() {
            @Override
            public void permissionDenied() {
                System.out.println("No Permission Granted");
            }

            @Override
            public void permissionGranted() {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });
    }

    public void onError(ErrorType errorType) {
        if (errorType == ErrorType.ERROR) {
            makeToast(getString(R.string.ERROR));
        } else if (errorType == ErrorType.ERROR500) {
            makeToast(getString(R.string.ERROR500));
        } else if (errorType == ErrorType.NO_INTERNET) {
            makeToast(getString(R.string.noInternetAccess));
        } else if (errorType == ErrorType.PARSING_ERROR) {
            makeToast(getString(R.string.PARSING_ERROR));
        }
    }

    private String getJsonArray(String result, String objectName) {
        try {
            JSONObject json = new JSONObject(result);
            return json.getJSONArray(objectName).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
