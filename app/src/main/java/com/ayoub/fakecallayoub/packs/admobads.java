package com.ayoub.fakecallayoub.packs;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ayoub.fakecallayoub.R;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;

public class admobads extends  Activity {
    static InterstitialAd mInterstitialAd;
    public interface  inter{
        void onfinished();
    }

    static RewardedInterstitialAd rewardedInterstitialAd;
    public static void RewardeLoad(Activity context,inter i) {
        // Use the test ad unit ID to load an ad.
        RewardedInterstitialAd.load(context,myapps.ADMOB_REWARDE,
                new AdRequest.Builder().build(),  new RewardedInterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(RewardedInterstitialAd ad) {
                        rewardedInterstitialAd = ad;
                        Log.e(TAG, "onAdLoaded");
                        showrewarde(context);
                        rewardedInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            /** Called when the ad failed to show full screen content. */
                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                i.onfinished();

                            }

                            /** Called when ad showed the full screen content. */
                            @Override
                            public void onAdShowedFullScreenContent() {
                            }

                            /** Called when full screen content is dismissed. */
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                i.onfinished();
                            }
                        });

                    }
                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        RewardeLoad1(context, new inter() {
                            @Override
                            public void onfinished() {
                                i.onfinished();
                            }
                        });
                    }
                });
    }
    private static void RewardeLoad1(Activity context,inter i) {
        // Use the test ad unit ID to load an ad.
        RewardedInterstitialAd.load(context,myapps.ADMOB_REWARDE1,
                new AdRequest.Builder().build(),  new RewardedInterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(RewardedInterstitialAd ad) {
                        rewardedInterstitialAd = ad;
                        Log.e(TAG, "onAdLoaded");
                        rewardedInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            /** Called when the ad failed to show full screen content. */
                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                i.onfinished();

                            }

                            /** Called when ad showed the full screen content. */
                            @Override
                            public void onAdShowedFullScreenContent() {

                            }

                            /** Called when full screen content is dismissed. */
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                i.onfinished();
                            }
                        });

                    }
                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        i.onfinished();
                    }
                });
    }

    private   static void showrewarde(Activity activity){
        if ( rewardedInterstitialAd!= null) {
            Activity activityContext = activity;
            rewardedInterstitialAd.show(activityContext, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {

                }
            });
        } else {
            Toast.makeText(activity, "Please try later ", Toast.LENGTH_SHORT).show();

        }

    }

    public static void loadBanner(Activity activity) {
        AdView adView = new AdView(activity);
        adView.setAdUnitId(myapps.BANNER_ADMOB);
        adView.setAdSize(getAdSize(activity));
        ((RelativeLayout) activity.findViewById(R.id.bannerAd)).addView(adView);
        AdRequest build = new AdRequest.Builder().build();
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                loadBanner1(activity);

            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }
        });
        adView.loadAd(build);
    }
    private static  void loadBanner1(Activity activity) {
        AdView adView = new AdView(activity);
        adView.setAdUnitId(myapps.BANNER_ADMOB1);
        adView.setAdSize(getAdSize(activity));
        ((RelativeLayout) activity.findViewById(R.id.bannerAd)).addView(adView);
        AdRequest build = new AdRequest.Builder().build();
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
             }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }
        });
        adView.loadAd(build);
    }

    public static AdSize getAdSize(Activity activity) {
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, (int) (((float) displayMetrics.widthPixels) / displayMetrics.density));
    }
    public static void loadinter(Activity context, final inter finished){
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(context,myapps.INTERSTITIAL_ADMOB, adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        showinter(context);
                        Log.i(TAG, "onAdLoaded");
                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                finished.onfinished();
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                finished.onfinished();

                                // Called when fullscreen content failed to show.

                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when fullscreen content is shown.
                                // Make sure to set your reference to null so you don't
                                // show it a second time.
                                mInterstitialAd = null;

                            }
                        });

                    }
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        loadinter1(context,new inter() {
                            @Override
                            public void onfinished() {
                                finished.onfinished();

                            }
                        });

                    }
                });
    }
    private static void loadinter1(Activity context,final inter finished){
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(context,myapps.INTERSTITIAL_ADMOB1, adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        showinter(context);
                        Log.i(TAG, "onAdLoaded");
                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                finished.onfinished();
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                finished.onfinished();

                                // Called when fullscreen content failed to show.

                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when fullscreen content is shown.
                                // Make sure to set your reference to null so you don't
                                // show it a second time.
                                mInterstitialAd = null;

                            }
                        });

                    }
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {

                        finished.onfinished();

                    }
                });
    }

    private static void showinter(Activity context){
        if (mInterstitialAd != null) {
            mInterstitialAd.show(context);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }
    }
    public static void  loadNative(Activity activity) {
        AdLoader.Builder builder = new AdLoader.Builder(activity,myapps.NATIVE_ADMOB);
        builder.forNativeAd(nativeAd -> {
            FrameLayout frame_native = activity.findViewById(R.id.frame_native);
            NativeAdView adView = (NativeAdView) LayoutInflater.from(activity).inflate(R.layout.gnt_medium_template_view, null);
            populateUnifiedNativeAdView(nativeAd, adView);
            frame_native.removeAllViews();
            frame_native.addView(adView);
        });

        VideoOptions videoOptions = new VideoOptions.Builder()
                .setStartMuted(true)
                .build();

        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                .setVideoOptions(videoOptions)
                .build();

        builder.withNativeAdOptions(adOptions);
        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                Log.e("Ads ", "NativeAd onAdFailedToLoad: " + adError.getMessage());
                loadNative1(activity);
             }
        }).build();

        AdRequest.Builder builerRe = new AdRequest.Builder();
        adLoader.loadAd(builerRe.build());
    }
    private static void loadNative1(Activity activity) {
        AdLoader.Builder builder = new AdLoader.Builder(activity,  myapps.NATIVE_ADMOB1);
        builder.forNativeAd(nativeAd -> {
            FrameLayout frame_native = activity.findViewById(R.id.frame_native);
            NativeAdView adView = (NativeAdView) LayoutInflater.from(activity).inflate(R.layout.gnt_medium_template_view, null);
            populateUnifiedNativeAdView(nativeAd, adView);
            frame_native.removeAllViews();
            frame_native.addView(adView);
        });

        VideoOptions videoOptions = new VideoOptions.Builder()
                .setStartMuted(true)
                .build();

        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                .setVideoOptions(videoOptions)
                .build();

        builder.withNativeAdOptions(adOptions);
        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                 Log.e("Ads ", "NativeAd onAdFailedToLoad: " + adError.getMessage());
            }
        }).build();

        AdRequest.Builder builerRe = new AdRequest.Builder();
        adLoader.loadAd(builerRe.build());
    }


    private static void populateUnifiedNativeAdView(NativeAd nativeAd, NativeAdView adView) {
        // Set the media view. Media content will be automatically populated in the media view once
        // adView.setNativeAd() is called.
        com.google.android.gms.ads.nativead.MediaView mediaView = adView.findViewById(R.id.ad_media);
        adView.setMediaView(mediaView);

        // Set other ad assets.
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

        // The headline is guaranteed to be in every UnifiedNativeAd.
        try {
            ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        adView.getStoreView().setVisibility(View.GONE);
        adView.getPriceView().setVisibility(View.GONE);

        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad. The SDK will populate the adView's MediaView
        // with the media content from this native ad.
        adView.setNativeAd(nativeAd);

        // Get the video controller for the ad. One will always be provided, even if the ad doesn't
        // have a video asset.
        VideoController vc = nativeAd.getMediaContent().getVideoController();

        // Updates the UI to say whether or not this ad has a video asset.
        if (vc.hasVideoContent()) {
//			videoStatus.setText(String.format(Locale.getDefault(),
//					"Video status: Ad contains a %.2f:1 video asset.",
//					vc.getAspectRatio()));

            // Create a new VideoLifecycleCallbacks object and pass it to the VideoController. The
            // VideoController will call methods on this object when events occur in the video
            // lifecycle.
            vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                @Override
                public void onVideoEnd() {
                    // Publishers should allow native packs to complete video playback before
                    // refreshing or replacing them with another ad in the same UI location.
//					refresh.setEnabled(true);
//					videoStatus.setText("Video status: Video playback has ended.");
                    super.onVideoEnd();
                }
            });
        } else {
//			videoStatus.setText("Video status: Ad does not contain a video asset.");
//			refresh.setEnabled(true);
        }
    }
}

