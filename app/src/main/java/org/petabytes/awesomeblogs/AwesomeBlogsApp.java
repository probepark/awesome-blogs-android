package org.petabytes.awesomeblogs;

import android.app.Application;
import android.preference.PreferenceManager;

import com.crashlytics.android.Crashlytics;
import com.f2prateek.rx.preferences.RxSharedPreferences;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.petabytes.api.Api;
import org.petabytes.coordinator.ActivityLayoutBinder;

import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class AwesomeBlogsApp extends Application {

    private static AwesomeBlogsApp instance;
    private ActivityLayoutBinder activityLayoutBinder;
    private Api api;
    private RxSharedPreferences preferences;
    private FirebaseAnalytics analytics;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        instance = this;

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
            .setDefaultFontPath("fonts/NanumBarunGothicLight.otf")
            .setFontAttrId(R.attr.fontPath)
            .build());
    }

    public static AwesomeBlogsApp get() {
        return instance;
    }

    public final ActivityLayoutBinder activityLayoutBinder() {
        return activityLayoutBinder == null ? activityLayoutBinder = createActivityLayoutBinder() : activityLayoutBinder;
    }

    protected ActivityLayoutBinder createActivityLayoutBinder() {
        return ActivityLayoutBinder.DEFAULT;
    }

    public final Api api() {
        return api == null ? api = createApi() : api;
    }

    protected Api createApi() {
        return new Api(this, false);
    }

    public final RxSharedPreferences preferences() {
        return preferences == null ? preferences = createPreferences() : preferences;
    }

    RxSharedPreferences createPreferences() {
        return RxSharedPreferences.create(PreferenceManager.getDefaultSharedPreferences(this));
    }

    public final FirebaseAnalytics analytics() {
        return analytics == null ? analytics = createAnalytics() : analytics;
    }

    FirebaseAnalytics createAnalytics() {
        return FirebaseAnalytics.getInstance(this);
    }
}
