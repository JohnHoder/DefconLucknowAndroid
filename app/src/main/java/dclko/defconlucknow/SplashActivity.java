package dclko.defconlucknow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends Activity {
    private static final long SPLASH_DURATION = 1500L;

    private Handler mHandler;
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                dismissSplash();
            }
        };

        // allow user to click and dismiss the splash screen prematurely
        View rootView = findViewById(android.R.id.content);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissSplash();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.postDelayed(mRunnable, SPLASH_DURATION);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }

    private void dismissSplash() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
