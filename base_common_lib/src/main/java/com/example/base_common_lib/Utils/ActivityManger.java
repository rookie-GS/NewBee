package com.example.base_common_lib.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author G_JS
 *
 * @Date Created by 2020/6/11 10:38
 *
 */

public class ActivityManger {
    private static volatile ActivityManger activityManger;
    private Stack<Activity> activitySet = new Stack<>();
    //批量关闭activity存储
    private Stack<Activity> finishActManger;

    private ActivityManger() {
    }

    public static ActivityManger getInstance() {
        if (activityManger == null) {
            synchronized (ActivityManger.class) {
                if (activityManger == null) {
                    activityManger = new ActivityManger();
                }
            }
        }
        return activityManger;
    }

    public void addActivity(Activity activity) {
        activitySet.add(activity);
    }

    public void removeActivity(Activity activity) {
        activitySet.remove(activity);
        if (finishActManger != null) {
            finishActManger.remove(activity);
        }
    }

    public FragmentActivity getActivity() {
        Activity activity = activitySet.lastElement();
        return (FragmentActivity) activity;
    }

    /**
     * 标记批量关闭的activity
     */
    public void flagBatchFinishAct(Activity activity) {
        if (finishActManger == null) {
            finishActManger = new Stack<>();
        }
        finishActManger.add(activity);
    }

    public boolean isAddCurrentActivity(Activity activity) {
        if (finishActManger == null) {
            return false;
        } else {
            return finishActManger.size() > 0 && finishActManger.lastElement() == activity;
        }
    }

    /**
     * 批量关闭activity
     */
    @SuppressLint("NewApi")
    public void finishActivity() {
        List<Activity> finishAct = new ArrayList<>();
        if (finishActManger != null) {
            finishAct.addAll(finishActManger);
            finishActManger.clear();
            finishActManger = null;
        }
        for (Activity activity : finishAct) {
            if (!activity.isFinishing() && !activity.isDestroyed()) {
                activity.finish();
            }
        }
    }

    public Context getContext() {
        return activitySet.lastElement();
    }
}
