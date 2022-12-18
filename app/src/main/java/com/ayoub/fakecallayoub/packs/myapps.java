package com.ayoub.fakecallayoub.packs;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.ayoub.fakecallayoub.ItemModel;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.onesignal.OneSignal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class myapps extends Application implements Application.ActivityLifecycleCallbacks, LifecycleObserver {
    public static List<ItemModel> appList;

    public static   String FACEBOOK_REWARDE1   ;
    public static   String FACEBOOK_REWARDE  ;
    public static  String ADMOB_REWARDE,Applovin_REWARDE  ;
    public static String ADMOB_REWARDE1,Applovin_REWARDE1;
    public String url = "https://ayoubouabi6.github.io/tazateams/vjson.json";
    private static final String ONESIGNAL_APP_ID = "########-####-####-####-############";
    String openApp="ca-app-pub-3940256099942544/3419835294";

    public static String BANNER_ADMOB,BANNER_ADMOB1,
            BANNER_FACEBOOK,BANNER_FACEBOOK1,
            BANNER_APPLOVIN, BANNER_APPLOVIN1,
            INTERSTITIAL_ADMOB,INTERSTITIAL_ADMOB1,
            INTERSTITIAL_FACEBOOK,INTERSTITIAL_FACEBOOK1,
            INTERSTITIAL_APPLOVIN,INTERSTITIAL_APPLOVIN1,
            NATIVE_ADMOB,NATIVE_ADMOB1,
            NATIVE_FACEBOOK,NATIVE_FACEBOOK1,
            NATIVE_APPLOVIN,NATIVE_APPLOVIN1,
            banner_type,inter_type,native_type ,reward_type,
             ironsource_id,unityid;
    public static String NATIVE_YANDEX;
    public static String BANNER_YANDEX,INTERSTITIAL_YANDEX,YANDEX_REWARDE;
    private AppOpenAdManager appOpenAdManager;
    private Activity currentActivity;
    private static final String TAG = "MyApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        this.registerActivityLifecycleCallbacks(this);
        appList =new ArrayList<>();

        // Log the Mobile Ads SDK version.
        Log.d(TAG, "Google Mobile Ads SDK Version: " + MobileAds.getVersion());

        MobileAds.initialize(
                this,
                new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(
                            @NonNull InitializationStatus initializationStatus) {}
                });

        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        appOpenAdManager = new AppOpenAdManager();
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
        AppLovinSdk.getInstance( getApplicationContext() ).setMediationProvider( "max" );
        AppLovinSdk.initializeSdk( getApplicationContext(), new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration)
            {
                // AppLovin SDK is initialized, start loading ads
            }
        } );
       getServerAds();

    }



    public void getServerAds(){

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONObject object = response.getJSONObject("ADS");


                            banner_type=object.getString("banner_type");
                            inter_type=object.getString("inter_type");
                            reward_type=object.getString("reward_type");
                            native_type=object.getString("native_type");


                            ironsource_id=object.getString("ironsource_id");
                            unityid=object.getString("unity_id");

                            BANNER_YANDEX = object.getString("banner_yandex");
                            NATIVE_YANDEX = object.getString("native_yandex");
                            INTERSTITIAL_YANDEX = object.getString("interstitial_yandex");
                            YANDEX_REWARDE = object.getString("rewarde_yandex");


                            BANNER_ADMOB = object.getString("banner_admob");
                            INTERSTITIAL_ADMOB = object.getString("interstitial_admob");
                            ADMOB_REWARDE=object.getString("reward_admob");
                            NATIVE_ADMOB = object.getString("native_admob");



                            BANNER_ADMOB1 = object.getString("banner_admob1");
                            INTERSTITIAL_ADMOB1 = object.getString("interstitial_admob1");
                            ADMOB_REWARDE1=object.getString("reward_admob1");
                            NATIVE_ADMOB1 = object.getString("native_admob1");




                            BANNER_FACEBOOK = object.getString("facebook_banner");
                            INTERSTITIAL_FACEBOOK = object.getString("facebook_interstitial");
                            FACEBOOK_REWARDE = object.getString("facebook_reward");
                            NATIVE_FACEBOOK = object.getString("facebook_native");

                            BANNER_FACEBOOK1 = object.getString("facebook_banner1");
                            INTERSTITIAL_FACEBOOK1 = object.getString("facebook_interstitial1");
                            FACEBOOK_REWARDE1 = object.getString("facebook_reward1");
                            NATIVE_FACEBOOK1 = object.getString("facebook_native1");



                            BANNER_APPLOVIN = object.getString("applovin_banner");
                            INTERSTITIAL_APPLOVIN = object.getString("applovin_interstitial");
                            Applovin_REWARDE = object.getString("applovin_reward");
                            NATIVE_APPLOVIN = object.getString("applovin_native");

                            BANNER_APPLOVIN1 = object.getString("applovin_banner1");
                            INTERSTITIAL_APPLOVIN1 = object.getString("applovin_interstitial1");
                            Applovin_REWARDE1 = object.getString("applovin_reward1");
                            NATIVE_APPLOVIN1 = object.getString("applovin_native1");
                            JSONArray apps = response.getJSONArray("Apps");
                            for (int i = 0; i < apps.length(); i++) {
                                // creating a new json object and
                                JSONObject responseObj = apps.getJSONObject(i);
                                String ttl=responseObj.getString("title");
                                String img=responseObj.getString("image");
                                String url=responseObj.getString("url");
                                ItemModel myList=new ItemModel(img,url,ttl);
                                appList.add(myList);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("testtag", "onResponse: "+e.getMessage());
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d("testtagggggggggg", "onResponse: "+error.getMessage());
                    }
                });
        requestQueue.add(jsonObjectRequest);

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected void onMoveToForeground() {
        // Show the ad (if available) when the app moves to foreground.
        appOpenAdManager.showAdIfAvailable(currentActivity);
    }

    /** ActivityLifecycleCallback methods. */
    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {}

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        // An ad activity is started when an ad is showing, which could be AdActivity class from Google
        // SDK or another activity class implemented by a third party mediation partner. Updating the
        // currentActivity only when an ad is not showing will ensure it is not an ad activity, but the
        // one that shows the ad.
        if (!appOpenAdManager.isShowingAd) {
            currentActivity = activity;
        }
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {}

    @Override
    public void onActivityPaused(@NonNull Activity activity) {}

    @Override
    public void onActivityStopped(@NonNull Activity activity) {}

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {}

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {}

    /**
     * Shows an app open ad.
     *
     * @param activity the activity that shows the app open ad
     * @param onShowAdCompleteListener the listener to be notified when an app open ad is complete
     */
    public void showAdIfAvailable(
            @NonNull Activity activity,
            @NonNull OnShowAdCompleteListener onShowAdCompleteListener) {
        // We wrap the showAdIfAvailable to enforce that other classes only interact with MyApplication
        // class.
        appOpenAdManager.showAdIfAvailable(activity, onShowAdCompleteListener);
    }

    /**
     * Interface definition for a callback to be invoked when an app open ad is complete
     * (i.e. dismissed or fails to show).
     */
    public interface OnShowAdCompleteListener {
        void onShowAdComplete();
    }
    private class AppOpenAdManager {

        private static final String LOG_TAG = "AppOpenAdManager";
        //private static final String AD_UNIT_ID = SplashActivity.openApp;

        private AppOpenAd appOpenAd = null;
        private boolean isLoadingAd = false;
        private boolean isShowingAd = false;

        /** Keep track of the time an app open ad is loaded to ensure you don't show an expired ad. */
        private long loadTime = 0;

        /** Constructor. */
        public AppOpenAdManager() {}

        /**
         * Load an ad.
         *
         * @param context the context of the activity that loads the ad
         */
        private void loadAd(Context context) {
            // Do not load ad if there is an unused ad or one is already loading.
            if (isLoadingAd || isAdAvailable()) {
                return;
            }

            isLoadingAd = true;
            AdRequest request = new AdRequest.Builder().build();
            AppOpenAd.load(
                    context,
                    openApp,
                    request,
                    AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
                    new AppOpenAd.AppOpenAdLoadCallback() {
                        /**
                         * Called when an app open ad has loaded.
                         *
                         * @param ad the loaded app open ad.
                         */
                        @Override
                        public void onAdLoaded(AppOpenAd ad) {
                            appOpenAd = ad;
                            isLoadingAd = false;
                            loadTime = (new Date()).getTime();

                            Log.d(LOG_TAG, "onAdLoaded.");
                         }

                        /**
                         * Called when an app open ad has failed to load.
                         *
                         * @param loadAdError the error.
                         */
                        @Override
                        public void onAdFailedToLoad(LoadAdError loadAdError) {
                            isLoadingAd = false;
                            Log.d(LOG_TAG, "onAdFailedToLoad: " + loadAdError.getMessage());
                            Toast.makeText(context, "onAdFailedToLoad", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        /** Check if ad was loaded more than n hours ago. */
        private boolean wasLoadTimeLessThanNHoursAgo(long numHours) {
            long dateDifference = (new Date()).getTime() - loadTime;
            long numMilliSecondsPerHour = 3600000;
            return (dateDifference < (numMilliSecondsPerHour * numHours));
        }

        /** Check if ad exists and can be shown. */
        private boolean isAdAvailable() {
            // Ad references in the app open beta will time out after four hours, but this time limit
            // may change in future beta versions. For details, see:
            // https://support.google.com/admob/answer/9341964?hl=en
            return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4);
        }

        /**
         * Show the ad if one isn't already showing.
         *
         * @param activity the activity that shows the app open ad
         */
        private void showAdIfAvailable(@NonNull final Activity activity) {
            showAdIfAvailable(
                    activity,
                    new OnShowAdCompleteListener() {
                        @Override
                        public void onShowAdComplete() {
                            // Empty because the user will go back to the activity that shows the ad.
                        }
                    });
        }

        /**
         * Show the ad if one isn't already showing.
         *
         * @param activity the activity that shows the app open ad
         * @param onShowAdCompleteListener the listener to be notified when an app open ad is complete
         */
        private void showAdIfAvailable(
                @NonNull final Activity activity,
                @NonNull OnShowAdCompleteListener onShowAdCompleteListener) {
            // If the app open ad is already showing, do not show the ad again.
            if (isShowingAd) {
                Log.d(LOG_TAG, "The app open ad is already showing.");
                return;
            }

            // If the app open ad is not available yet, invoke the callback then load the ad.
            if (!isAdAvailable()) {
                Log.d(LOG_TAG, "The app open ad is not ready yet.");
                onShowAdCompleteListener.onShowAdComplete();
                loadAd(activity);
                return;
            }

            Log.d(LOG_TAG, "Will show ad.");

            appOpenAd.setFullScreenContentCallback(
                    new FullScreenContentCallback() {
                        /** Called when full screen content is dismissed. */
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            // Set the reference to null so isAdAvailable() returns false.
                            appOpenAd = null;
                            isShowingAd = false;

                            Log.d(LOG_TAG, "onAdDismissedFullScreenContent.");

                            onShowAdCompleteListener.onShowAdComplete();
                            loadAd(activity);
                        }

                        /** Called when fullscreen content failed to show. */
                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            appOpenAd = null;
                            isShowingAd = false;

                            Log.d(LOG_TAG, "onAdFailedToShowFullScreenContent: " + adError.getMessage());

                            onShowAdCompleteListener.onShowAdComplete();
                            loadAd(activity);
                        }

                        /** Called when fullscreen content is shown. */
                        @Override
                        public void onAdShowedFullScreenContent() {
                            Log.d(LOG_TAG, "onAdShowedFullScreenContent.");
                         }
                    });

            isShowingAd = true;
            appOpenAd.show(activity);
        }
    }
}

