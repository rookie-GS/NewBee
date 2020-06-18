package com.example.base_common_lib.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.Map;
import java.util.Set;

//import cn.jpush.im.android.api.JMessageClient;

/**
 * Created by LMY on 2016/12/17.
 */

public class SPUtils {
    private static final String FILE_NAME_CONFIG = "xun_an";        //sp文件名
    public static final String ACCOUNT_ID = "accunt_id";            //AccountId  在我的账号里面用到
    public static final String USER_ID = "user_id";                 //UserId  常用
    public static final String USER_PHONE = "user_phone";           //UserPhone
    public static final String USER_NAME = "user_name";             //name
    public static final String USER_ICON = "user_iocn";             //头像
    public static final String USER_PWD_STATUS = "user_pwd_status"; //密码设置状态
    public static final String IS_LOGIN = "isLogin";                //登录状态
    public static final String LOGINTOKEN = "logintoken";           //LOGINTOKEN


    /**
     * 启动页flag
     */
    public static final String START_LOGO_FLAG = "start_logo_flag";
    /**
     * beta偏移值
     */
    public static final String BETA_OFF = "what_off";
    /**
     * beta适配value
     */
    public static final String BETA_ADAPTER_VALUE = "beta_adapter_value";
    /**
     * 清楚缓存状态记录
     */
    public static final String CLEAR_CACHE_STATUS = "clear_cache_status";
    /**
     * 强制试用时间记录 what is sb?
     */
    public static final String FORCE_BETA_TRY_TIME = "beta_try_time";
    /**
     * 每天列表点击，只启动一次播放详情
     */
    public static final String ITEM_JUMPED_TIME = "item_jumped_time";

    /**
     * IP地址记录，一般作为测试使用
     */
    public static final String REPLAY_IP = "test_ip";

    /**
     * 获取启动页启动状态
     */
    public static boolean getStartLogoFlag() {
        SharedPreferences preferences = getPreferences();
        return preferences.getBoolean(START_LOGO_FLAG, false);
    }

    public static int getBetaOff() {
        SharedPreferences prefs = getPreferences();
        return prefs.getInt(BETA_OFF, 0);
    }


    public static int getBetaTryTime() {
        SharedPreferences prefs = getPreferences();
        return prefs.getInt(FORCE_BETA_TRY_TIME, 0);
    }

    public static String getUserPhone() {
        SharedPreferences prefs = getPreferences();
        return prefs.getString(USER_PHONE, "");
    }

    public static long getItemJumpedTime() {
        SharedPreferences prefs = getPreferences();
        return prefs.getInt(ITEM_JUMPED_TIME, 0);
    }


    public static int getClearCacheStatus() {
        SharedPreferences prefs = getPreferences();
        return prefs.getInt(CLEAR_CACHE_STATUS, 0);
    }

    //或者适配value
    public static String getBetaAdapterValue() {
        SharedPreferences prefs = getPreferences();
        return prefs.getString(BETA_ADAPTER_VALUE, "");
    }

    //密码设置状态
    public static boolean getPwdStatus() {
        SharedPreferences prefs = getPreferences();
        return prefs.getBoolean(USER_PWD_STATUS, false);
    }

    //AccountId
    public static String getAccountId() {
        SharedPreferences prefs = getPreferences();
        return TextUtils.isEmpty(prefs.getString(ACCOUNT_ID, "")) ? "0" : prefs.getString(ACCOUNT_ID, "");
    }


    //userId
    public static String getUserId() {
        SharedPreferences prefs = getPreferences();
        return TextUtils.isEmpty(prefs.getString(USER_ID, "")) ? "0" : prefs.getString(USER_ID, "");
    }


//    第三方登录openId
//    public static String getOpenId() {
//        SharedPreferences prefs = getPreferences();
//        return prefs.getString(OPENID, "");
//    }

    //name
    public static String getUserName() {
        SharedPreferences prefs = getPreferences();
        return prefs.getString(USER_NAME, "");
    }

    //头像
    public static String getUserIcon() {
        SharedPreferences prefs = getPreferences();
        return prefs.getString(USER_ICON, "");
    }

//    //player
//    public static String getVideoUrl() {
//        SharedPreferences prefs = getPreferences();
//        return prefs.getString(PLAYER, "");
//    }


    //是否登录
    public static String getIsLogin() {
        SharedPreferences prefs = getPreferences();
        return prefs.getString(IS_LOGIN, "");
    }


    //LoginToKen
    public static String getLoginToKen() {
        SharedPreferences prefs = getPreferences();
        return prefs.getString(LOGINTOKEN, "");
    }

    public static String getReplayIp() {
        SharedPreferences prefs = getPreferences();
        return prefs.getString(REPLAY_IP, "https://api.ios.lanwuzhe.com/");
    }

    //存入String类型
    public static void putString(String key, String value) {
        SharedPreferences prefs = getPreferences();
        SharedPreferences.Editor edit = prefs.edit();

        edit.putString(key, value);
        //必须提交，否则结果无效
        edit.apply();
    }


    public static void putInt(String key, int value) {
        SharedPreferences prefs = getPreferences();
        SharedPreferences.Editor edit = prefs.edit();

        edit.putInt(key, value);
        //必须提交，否则结果无效
        edit.apply();
    }


    //存入String类型
    public static void putLong(String key, int value) {
        SharedPreferences prefs = getPreferences();
        SharedPreferences.Editor edit = prefs.edit();

        edit.putLong(key, value);
        //必须提交，否则结果无效
        edit.apply();
    }


    public static Set<String> getStringSet(String key, Set<String> defValues) {
        SharedPreferences prefs = getPreferences();
        return prefs.getStringSet(key, defValues);
    }

    public static void putStringSet(String key, Set<String> values) {
        SharedPreferences prefs = getPreferences();
        SharedPreferences.Editor edit = prefs.edit();
        edit.putStringSet(key, values);
        //必须提交，否则结果无效
        edit.apply();
    }

    private static SharedPreferences getPreferences() {
        // (String name xml文件名, int mode 访问权限)
        return Other_Utils.getContext().getSharedPreferences(FILE_NAME_CONFIG,
                Context.MODE_PRIVATE);
    }

    //清空登录状态
    public static void clearSPData() {
//        JMessageClient.logout();
        SharedPreferences preferences = getPreferences();
        SharedPreferences.Editor edit = preferences.edit();
        Map<String, ?> all = preferences.getAll();
        Set<String> strings = all.keySet();
        for (String string : strings) {
            if (!string.equals(CLEAR_CACHE_STATUS) && !string.equals(FORCE_BETA_TRY_TIME)
                    && !string.equals(ITEM_JUMPED_TIME)) {
                edit.remove(string);
            }
        }
        edit.apply();
    }

    public static void clearSPKeyData(String key) {
        SharedPreferences preferences = getPreferences();
        SharedPreferences.Editor edit = preferences.edit();
        edit.remove(key);
        edit.apply();
    }

    public static boolean getBoolean(String key, boolean defValue) {
        SharedPreferences prefs = getPreferences();
        return prefs.getBoolean(key, defValue);
    }

    public static void putBoolean(String key, boolean value) {
        SharedPreferences prefs = getPreferences();
        SharedPreferences.Editor edit = prefs.edit();
        edit.putBoolean(key, value);
        //必须提交，否则结果无效
        edit.apply();
    }
}
