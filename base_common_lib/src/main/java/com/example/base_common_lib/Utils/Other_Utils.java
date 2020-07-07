package com.example.base_common_lib.Utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.PendingIntent;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static android.app.usage.UsageStatsManager.INTERVAL_DAILY;
import static android.content.Context.USAGE_STATS_SERVICE;

/**
 * @Author G_JS
 * 
 * @Date Created by 2020/6/11 11:09
 * 
 */
public class Other_Utils {
    @SuppressLint("StaticFieldLeak")     //去除黄色警告
    private static Context context;     //注意使用,不会照成内存泄漏,

    private Other_Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Other_Utils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("u should init first");
    }

    /**
     * 是否登录
     */
//    public static boolean isLogin() {
//        if (!SPUtils.getIsLogin().equals("1")) {
//            ARouter.getInstance()
//                    .build(ModelJumpCommon.LOGIN_PAGER)
//                    .navigation();
////            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("model://login"));
////            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////            context.startActivity(intent);
//            return false;
//        }
//        return true;
//    }

//    /**
//     * 是否登录
//     *
//     * @return true
//     */
//    public static boolean isLoginEquals() {
//        return SPUtils.getIsLogin().equals("1");
//    }

    /**
     * 是否登录，用于首页启动，和音乐服务启动判断是否登录，包含跳过按钮
     */
//    public static void isFlagLogin() {
//        try {
//            if (!SPUtils.getIsLogin().equals("1")) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("model://login"));
//                intent.putExtra("isFlagLogin", true);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    /**
     * @return 手机虚拟键的高度, 适配的时候需要
     */
    public static int getNavBarHeight() {
        return getTotalHeight() - getScreenHeight();
    }

    /**
     * @return 获取屏幕原始尺寸高度，包括虚拟功能键高度
     */
    public static int getTotalHeight() {
        int dpi = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, displayMetrics);
            dpi = displayMetrics.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dpi;
    }

    /**
     * @return 获取屏幕内容高度不包括虚拟按键
     */
    public static int getScreenNoHeight() {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    public static int getScreenHeight() {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /*
     * 获取屏幕的宽*/
    public static int getScreenWidth() {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 检查手机是否是小米
     *
     * @ref http://dev.xiaomi.com/doc/p=254/index.html
     */
    public static boolean isMIUI() {
        String device = Build.MANUFACTURER;
        if (device.equals("Xiaomi")) {
            return true;
//            return new Properties().getProperty(KEY_MIUI_VERSION_CODE, null) != null
//                    || new Properties().getProperty(KEY_MIUI_VERSION_NAME, null) != null
//                    || new Properties().getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null;
        } else {
            return false;
        }
    }

    /**
     * 开启多进程，防止多次调用application
     *
     * @param pid 进程id
     * @return 进程名称
     */

    public static String getProcessName(Context context, int pid) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) {
            return null;
        }
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo prcInfo : runningApps) {
            if (prcInfo.pid == pid) {
                return prcInfo.processName;
            }
        }
        return null;
    }

    //是否开启定位
    public static boolean isOPen() {
        LocationManager locationManager
                = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 通过WiFI或移动网络(3G/2G)确定的位置（也称作GPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        return gps || network;

    }

    //强制帮用户开启定位
    public static void openGPS() {
        Intent GPSIntent = new Intent();
        GPSIntent.setClassName("com.android.settings",
                "com.android.settings.widget.SettingsAppWidgetProvider");
        GPSIntent.addCategory("android.intent.category.ALTERNATIVE");
        GPSIntent.setData(Uri.parse("custom:3"));
        try {
            PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }

    // 加载系统默认设置，字体不随用户设置变化
    public static void setToDefaults() {
        Resources res = context.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
    }


    /**
     * dp转换
     */
    public static int dip2px(float dpValue) {
        float scale = Other_Utils.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5F);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp
     */
    public static int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                Resources.getSystem().getDisplayMetrics());
    }

    public static String float2String(float value) {
        return String.valueOf(formatFloat(value));
    }

    private static float formatFloat(float value) {
        BigDecimal bigDecimal = BigDecimal.valueOf(value);
        return bigDecimal.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
    }


    /**
     * 测量View的宽高
     *
     * @param view View
     */
    public static void measureWidthAndHeight(View view) {
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(widthMeasureSpec, heightMeasureSpec);
    }

    @SuppressLint("SimpleDateFormat")
    public static String getData(Long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(time);
    }

    /**
     * 获取系统手机版本号
     *
     * @return 版本号
     */
    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    public static int getSystemSDK() {
        return Build.VERSION.SDK_INT;
    }


    /**
     * 根据视频路径获取视频图片
     *
     * @param videoPath 视频路径
     * @return 图片路径
     */
    public static String getVideosImagePath(String videoPath) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(videoPath);
        // 取得视频的长度(单位为毫秒)
        String duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        Bitmap bitmap = retriever.getFrameAtTime(TimeUnit.MILLISECONDS.toMicros(1));
        String pathUrl = Environment.getExternalStorageDirectory() + File.separator + 1 + ".jpg";
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(pathUrl);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos);
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pathUrl;
    }

//    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//    public static boolean isNotificationEnabled() {
//        Class appOpsClass = null;
//        /* Context.APP_OPS_MANAGER */
//        try {
//            String CHECK_OP_NO_THROW = "checkOpNoThrow";
//            String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
//
//            AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
//            ApplicationInfo appInfo = context.getApplicationInfo();
//            String pkg = context.getApplicationContext().getPackageName();
//            int uid = appInfo.uid;
//            appOpsClass = Class.forName(AppOpsManager.class.getName());
//            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE,
//                    String.class);
//            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);
//
//            int value = (Integer) opPostNotificationValue.get(Integer.class);
//            return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    /**
     * 获取版本名称
     */
    public static String getVersionName() {
        try {
            //获取包管理器
            PackageManager pm = context.getPackageManager();
            //获取包信息
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            //返回版本号
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "0";
    }


    /**
     * 获取版本code
     */
    public static int getVersionCode() {
        try {
            //获取包管理器
            PackageManager pm = context.getPackageManager();
            //获取包信息
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            //返回版本号
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 转换货币金额格式
     *
     * @param string 需要转换的数据
     */
    public static String getMoneyType(String string) {
        // 把string类型的货币转换为double类型。
        Double numDouble = Double.parseDouble(string);
        // 想要转换成指定国家的货币格式
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CHINA);
        // 把转换后的货币String类型返回
        return format.format(numDouble);
    }

    /**
     * 转换货币金额格式
     *
     * @param string 需要转换的数据
     */
    public static String getMoneyVip(String string) {
        // 把string类型的货币转换为double类型。
        Integer numDouble = Integer.parseInt(string);
        // 想要转换成指定国家的货币格式
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CHINA);
        // 把转换后的货币String类型返回
//        LogUtils.d("numDouble.intValue()="+numDouble.intValue());
        String money = format.format(numDouble.intValue());
        if (money.contains(".")) {
            return money.substring(0, money.indexOf("."));
        }
        return money;
    }


    /**
     * 转换货币金额格式
     *
     * @param numDouble 需要转换的数据
     */
    public static String getMoneyType(double numDouble) {
        // 把string类型的货币转换为double类型。
//        Double numDouble = Double.parseDouble(string);
        // 想要转换成指定国家的货币格式
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CHINA);
        // 把转换后的货币String类型返回
        return format.format(numDouble);
    }


    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }

    /**
     * 判断service是否在运行
     *
     * @param className 包名+类名
     */
    public static boolean isWorked(String className) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServiceInfos = am.getRunningServices(200);
        if (runningServiceInfos.size() <= 0) {
            return false;
        }
        for (ActivityManager.RunningServiceInfo serviceInfo : runningServiceInfos) {
            if (serviceInfo.service.getClassName().equals(className)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 图片压缩
     *
     * @param path   路径
     * @param bitmap 图片
     */
    public static void fileString(String path, Bitmap bitmap) {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(path)));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * num显示万
     */
    public static String numTextShow(int num) {
        if (num >= 10000) {
            double n = (double) num / 10000;
            BigDecimal b = new BigDecimal(n);
            double format = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            return format + "万";
        }
        return String.valueOf(num);
    }

    //当前应用是否处于前台
    public static boolean isForeground() {
        if (context != null) {
            try {
                ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
                if (am != null) {
                    ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
                    String currentPackageName = cn.getPackageName();
                    if (!TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(context.getPackageName())) {
                        return true;
                    }
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
    //获取所有应用的信息
    @SuppressLint("NewApi")
    private void getForegroundApp() {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        long endt = calendar.getTimeInMillis();//结束时间
        long statt = calendar.getTimeInMillis()-86400000;//开始时间
        UsageStatsManager usageStatsManager=(UsageStatsManager) getContext().getSystemService(USAGE_STATS_SERVICE);
        //获取一天内的信息
        List<UsageStats> queryUsageStats = usageStatsManager.queryUsageStats(INTERVAL_DAILY,statt,endt);
        if(queryUsageStats.size()==0){
            getContext().startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
        }else {
            for (int i = 0; i <queryUsageStats.size() ; i++) {
                if(queryUsageStats.get(i).getPackageName().equals("com.example.newbee")){
                    LogUtils.e("使用时长测试：包名："+queryUsageStats.get(i).getPackageName()+"   使用时长："+ queryUsageStats.get(i).getTotalTimeVisible());
                    break;
                }
            }
        }
    }


}
