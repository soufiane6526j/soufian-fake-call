package com.ayoub.fakecallayoub.packs;

import android.app.Activity;

import com.ayoub.fakecallayoub.R;


public class AdsLoaded {


    public static void loadbanner (Activity activity){
        if(myapps.banner_type.equalsIgnoreCase("admob")){
            admobads.loadBanner(activity);
        }else if (myapps.banner_type.equalsIgnoreCase("applovin")){
            applovinn.applovinbanner(activity);
        }else if (myapps.banner_type.equalsIgnoreCase("facebook")){
            facebookads.showBanner(activity);
        }else if (myapps.banner_type.equalsIgnoreCase("ironsource")){
           ironsource.showBannerView(activity);
        }else if (myapps.banner_type.equalsIgnoreCase("unity")){
            UnityAds.loadbanner(activity);
        }else if (myapps.banner_type.equalsIgnoreCase("yandex")){
            yandex_ads.banner(activity);
        }
    }
    public static void LoadNative(Activity  activity){
        if(myapps.native_type.equalsIgnoreCase("admob")){
            admobads.loadNative(activity);
        }else if (myapps.native_type.equalsIgnoreCase("facebook")){
            facebookads.loadFacebookNative(activity);
        }
        else if (myapps.native_type.equalsIgnoreCase("applovin")){
            applovinn.applovinmrec(activity);
        }else if (myapps.native_type.equalsIgnoreCase("yandex")){
            yandex_ads.loadnative(activity);
        }
    }
    public interface  inter{
        void onfinished();
    }
    public  static void loadaAdInter(Activity activity )
    {



                if(myapps.inter_type.equalsIgnoreCase("admob")){
                    admobads.loadinter(activity, new admobads.inter() {
                        @Override
                        public void onfinished() {

                        }
                    });
                }else if(myapps.inter_type.equalsIgnoreCase("facebook")){
                    facebookads.showInter(activity, new facebookads.AdFinished() {
                        @Override
                        public void onAdFinished() {

                        }
                    });
                }else if(myapps.inter_type.equalsIgnoreCase("yandex")){
                    yandex_ads.inter(activity, new yandex_ads.interr() {
                        @Override
                        public void inter() {

                        }
                    });

                }else if(myapps.inter_type.equalsIgnoreCase("unity")){
                   UnityAds.DisplayInterstitialAd(activity, new UnityAds.inter() {
                       @Override
                       public void inter() {

                       }
                   });
                }else if (myapps.inter_type.equalsIgnoreCase("ironsource")){
                    ironsource.ironinter(new ironsource.inter() {
                        @Override
                        public void inter() {

                        }
                    });
                }else if (myapps.inter_type.equalsIgnoreCase("applovin")){
                    applovinn.applovininter(activity, new applovinn.OnAdsdone() {
                        @Override
                        public void adsdone() {

                        }
                    });

                }else if(myapps.inter_type.equalsIgnoreCase(" ")){

                }

        }
    public  static void loadaAdRew(Activity activity )
    {


                if(myapps.reward_type.equalsIgnoreCase("admob")){
                    admobads.RewardeLoad(activity, new admobads.inter() {
                        @Override
                        public void onfinished() {

                        }
                    });
                }else if(myapps.reward_type.equalsIgnoreCase("facebook")){
                    facebookads.fanRewa1(activity, new facebookads.AdFinished() {
                        @Override
                        public void onAdFinished() {

                        }
                    });
                }else if(myapps.reward_type.equalsIgnoreCase("unity")){
                    UnityAds.DisplayRewarde(activity, new UnityAds.inter() {
                        @Override
                        public void inter() {

                        }
                    });
                }else if (myapps.reward_type.equalsIgnoreCase("ironsource")){
                    ironsource.ironreawrde(new ironsource.inter() {
                        @Override
                        public void inter() {

                        }
                    });
                }else if (myapps.reward_type.equalsIgnoreCase("yandex")){
                    yandex_ads.rewarde(activity, new yandex_ads.interr() {
                        @Override
                        public void inter() {

                        }
                    });

                }else if (myapps.reward_type.equalsIgnoreCase("applovin")){
                    applovinn.applovinrew(activity, new applovinn.OnAdsdone() {
                        @Override
                        public void adsdone() {

                        }
                    });

                }

    }
    }

