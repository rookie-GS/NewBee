package com.example.testlib;
import androidx.annotation.NonNull;
import android.graphics.BlurMaskFilter;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base_common_lib.Arouter_path;
import com.example.base_common_lib.Base.BaseActivity.BaseTitleActivity;
import com.example.base_common_lib.Utils.SpannableStringUtils;

@Route( path = Arouter_path.TEST_STRING_PAGE)
public class String_Builder_Activity extends BaseTitleActivity {
    TextView tv_str;

    @Override
    protected int setToolbgcolor() {
        return R.color.white;
    }

    @Override
    protected int setTitlecolor() {
        return R.color.black;
    }

    @Override
    protected String setTextTitle() {
        return "字符串拼接测试";
    }

    @Override
    protected boolean setToolbvis() {
        return true ;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_string_builder;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tv_str = findViewById(R.id.tv_str);
    }

    @Override
    protected void initData() {
        SpannableStringBuilder stringBuilder = SpannableStringUtils
                .getBuilder("五指山")
                .append("\n白茫茫大地真干净").setForegroundColor(getResources().getColor(R.color.background_info))
                .setClickSpan(new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View widget) {

                    }

                    @Override
                    public void updateDrawState(@NonNull TextPaint ds) {

                    }
                })
                .create();
        stringBuilder = SpannableStringUtils.getBuilder(stringBuilder)
                .append("\n,拔剑四顾心茫然").setBoldItalic().setForegroundColor(getResources().getColor(R.color.colorPrimary))
                .setClickSpan(new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View widget) {

                    }

                    @Override
                    public void updateDrawState(@NonNull TextPaint ds) {

                    }
                })
                .create();
        stringBuilder = SpannableStringUtils.getBuilder(stringBuilder)
                .append("\n,黑云压城城欲摧").setForegroundColor(getResources().getColor(R.color.background_error)).setBlur(3, BlurMaskFilter.Blur.NORMAL)
                .setClickSpan(new ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View widget) {

                    }

                    @Override
                    public void updateDrawState(@NonNull TextPaint ds) {

                    }
                })
                .create();
        tv_str.setText(stringBuilder);
        tv_str.setMovementMethod(LinkMovementMethod.getInstance());
    }
}