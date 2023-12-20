package com.company.laeventa;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    private static MyApplication sInstance;

    private static String hotelName;
    private static boolean isAdmin;
    private static boolean isSports;

    private static String username;
    private static String password;

    public MyApplication() {}

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static MyApplication getInstance(){
        return sInstance;
    }

    public static Context myAppContext(){
        return sInstance.getApplicationContext();
    }

    public static String getHotelName() {
        return hotelName;
    }

    public static void setHotelName(String hotelName) {
        MyApplication.hotelName = hotelName;
    }

    public static boolean getIsAdmin() {
        return isAdmin;
    }

    public static void setIsAdmin(boolean isAdmin) {
        MyApplication.isAdmin = isAdmin;
    }

    public static boolean getIsSports() {
        return isSports;
    }

    public static void setIsSports(boolean isSports) {
        MyApplication.isSports = isSports;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        MyApplication.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        MyApplication.password = password;
    }
}

