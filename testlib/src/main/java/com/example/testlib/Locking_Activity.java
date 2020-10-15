package com.example.testlib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base_common_lib.Arouter_path;
import com.example.base_common_lib.Base.BaseActivity.BaseTitleActivity;
import com.example.base_common_lib.Utils.ToastUtils;
import com.github.ihsg.demo.util.PatternHelper;
import com.github.ihsg.patternlocker.OnPatternChangeListener;
import com.github.ihsg.patternlocker.PatternLockerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Route(path = Arouter_path.TEST_SETLOCKING_PAGE)
public class Locking_Activity  extends BaseTitleActivity {
    private static final String TAG = "Locking_Activity";
    PatternHelper patternHelper;
    PatternLockerView patternLockerView;
    TextView tv_tip;
    @Override
    protected String setTextTitle() {
        return "设置密码";
    }
    @Override
    protected int setTitlecolor() {
        return R.color.black;
    }
    @Override
    protected boolean setToolbvis() {
        return true;
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_locking;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tv_tip = findViewById(R.id.tv_tip);
        patternLockerView = findViewById(R.id.patternLockerView);
        patternLockerView.setEnableHapticFeedback(true);
        patternHelper = new PatternHelper();
        patternLockerView.setOnPatternChangedListener(new OnPatternChangeListener() {
            @Override
            public void onStart(@NotNull PatternLockerView patternLockerView) {
                Log.e(TAG, "locking___onStart: " );
            }

            @Override
            public void onChange(@NotNull PatternLockerView patternLockerView, @NotNull List<Integer> list) {
                Log.e(TAG, "locking___onChange: " );
            }

            @Override
            public void onComplete(@NotNull PatternLockerView patternLockerView, @NotNull List<Integer> list) {

                Log.e(TAG, "locking___onComplete: " );
                boolean isOk = isPatternOk(list);
                patternLockerView.updateStatus(isOk);
                tv_tip.setText(patternHelper.getMessage());
                ToastUtils.showShortToast(patternHelper.getMessage());
                patternLockerView.clearHitState();

            }

            @Override
            public void onClear(@NotNull PatternLockerView patternLockerView) {
                Log.e(TAG, "locking___onClear: " );
                if(patternHelper.isFinish()){
                    finish();
                }
            }
        });
    }
    private boolean isPatternOk(List<Integer> list){
        patternHelper.validateForSetting(list);
        return patternHelper.isOk();
    }

    @Override
    protected void initData() {

    }
}