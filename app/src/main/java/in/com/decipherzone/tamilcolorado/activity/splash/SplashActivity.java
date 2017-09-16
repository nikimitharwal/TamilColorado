package in.com.decipherzone.tamilcolorado.activity.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import in.com.decipherzone.tamilcolorado.R;
import in.com.decipherzone.tamilcolorado.activity.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    private long splashDelay = 2000;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            startActivity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, splashDelay);
    }

    private void startActivity() {
        Intent intent;
            intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
