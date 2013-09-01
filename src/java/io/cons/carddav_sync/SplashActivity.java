package io.cons.carddav_sync;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import clojure.lang.Symbol;
import clojure.lang.Var;
import clojure.lang.RT;

import io.cons.carddav_sync.R;

public class SplashActivity extends Activity {

    private static boolean firstLaunch = true;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

	Intent selector = getIntent().getSelector();
	if (selector == null) {
            selector = new Intent("io.cons.carddav_sync.MAIN");
	}
        if (firstLaunch) {
            firstLaunch = false;
            setupSplash();
            loadClojure(selector);
        } else {
            proceed(selector);
        }
    }

    public void setupSplash() {
        setContentView(R.layout.splashscreen);

        TextView appNameView = (TextView)findViewById(R.id.splash_app_name);
        appNameView.setText(R.string.app_name);

        Animation rotation = AnimationUtils.loadAnimation(this, R.anim.splash_rotation);
        ImageView circleView = (ImageView)findViewById(R.id.splash_circles);
        circleView.startAnimation(rotation);
    }

    public void proceed(final Intent selector) {
        startActivity(selector);
        finish();
    }

    public void loadClojure(final Intent selector) {
        new Thread(new Runnable(){
                @Override
                public void run() {
                    Symbol CLOJURE_MAIN = Symbol.intern("neko.application");
                    Var REQUIRE = RT.var("clojure.core", "require");
                    REQUIRE.invoke(CLOJURE_MAIN);

                    Var INIT = RT.var("neko.application", "init-application");
                    INIT.invoke(SplashActivity.this.getApplication());

                    proceed(selector);
                }
            }).start();
    }
}
