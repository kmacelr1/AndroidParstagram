package com.example.parstagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("xFoMBuALbX6JwevPktlbzdJ5GGReUSSMKTTlevuA")
                .clientKey("9H7uDtmsmtU4lhXIgtjatUOmAcaNTxHn5R0m6lzO")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
