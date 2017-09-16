package in.com.decipherzone.base;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.baselib.image_cropping.ImageCompress;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import in.com.decipherzone.tamilcolorado.ApplicationContext;
import in.com.decipherzone.tamilcolorado.R;
import in.com.decipherzone.utilites.Constant;

/**
 * {@link BaseAppCompatSecondaryActivity} send an e-mail with some debug information
 * to the developer.
 *
 * @author GladiatoR
 */
public class BaseAppCompatSecondaryActivity extends BasePermissionAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void showHashKey() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String keyHash = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.i("KeyHash:", keyHash);
                Toast.makeText(BaseAppCompatSecondaryActivity.this, keyHash, Toast.LENGTH_LONG).show();
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }
    }

    public void makeToast(String message) {
        if (!TextUtils.isEmpty(message))
            Toast.makeText(BaseAppCompatSecondaryActivity.this, message, Toast.LENGTH_LONG).show();
    }

    public void makeSnackbar(String message) {
        View view = this.findViewById(android.R.id.content).getRootView();
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    public File file;
    public final static int CAPTURE_PHOTO = 100;
    public final static int SELECT_PHOTO = 101;
    public final static int CROP_IMAGE = 1001;

    // to get image from device
    public void showImageGettingDialog() {

        getStoragePermission(new RequestPermissionAction() {
            @Override
            public void permissionDenied() {
                Toast.makeText(BaseAppCompatSecondaryActivity.this, STORAGE_PERMISSION_NOT_GRANTED, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void permissionGranted() {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(BaseAppCompatSecondaryActivity.this);
                alertDialogBuilder.setTitle(getString(R.string.uploadImage));
                alertDialogBuilder
                        .setItems(R.array.pickerArray, (dialog, which) -> {
                            if (which == 0) {
                                openCamera();
                            } else if (which == 1) {
                                openGallery();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

    }

    private void createFile() {
        file = null;
        if (!ApplicationContext.getInstance().getCACHE_DIR().exists()) {
            ApplicationContext.getInstance().getCACHE_DIR().mkdirs();
        }
        file = new File(ApplicationContext.getInstance().getCACHE_DIR(), System.currentTimeMillis() + "-image.jpg");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // for open camera to capture image
    public void openCamera() {
        createFile();
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        camera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        camera.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 1024 * 1024);
        startActivityForResult(camera, CAPTURE_PHOTO);
    }

    // for open explorer to select image
    public void openGallery() {
        file = null;
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_PICK);
            startActivityForResult(Intent.createChooser(intent, Constant.APP_NAME), SELECT_PHOTO);
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, Constant.APP_NAME), SELECT_PHOTO);
        }
    }

    public File tempFile = null;

    // convert data to uri
    public Uri getImageBitmapFromIntentData(Intent data) {
        ImageCompress image = new ImageCompress();
//        File tempFile = null;
        String tempFilePath = "";
        if (file == null || !file.exists()) {
            if (data != null && data.getData() != null)
                tempFilePath = image.compressImage(this, data.getData().toString());
        } else {
            tempFilePath = image.compressImage(this, file.getPath());

            try {
                file.delete();
            } catch (Exception e) {
//                ApplicationContext.getInstance().logExceptionRecord(e, getClass());
            }
        }

        if (tempFilePath == null || tempFilePath.equals("")) {
            return null;
        }
        tempFile = new File(tempFilePath);

        if (tempFile == null) {
            makeToast("Error while reading Image File null");
            return null;
        }
        return Uri.fromFile(tempFile);
    }
}
