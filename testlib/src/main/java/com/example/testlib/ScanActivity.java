package com.example.testlib;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base_common_lib.Arouter_path;
import com.example.base_common_lib.Base.BaseActivity.BaseTitleActivity;
import com.example.base_common_lib.Utils.LogUtils;
import com.google.zxing.Result;
import com.google.zxing.client.result.ParsedResult;
import com.mylhyl.zxing.scanner.OnScannerCompletionListener;
import com.mylhyl.zxing.scanner.ScannerView;
import com.mylhyl.zxing.scanner.decode.QRDecode;

@Route(path = Arouter_path.TEST_SCAN_PAGE)
public class ScanActivity extends BaseTitleActivity implements OnScannerCompletionListener {
    ScannerView mScannerView;
    @Override
    protected int getContentView() {
        return R.layout.activity_scan;
    }

    @Override
    protected String setTextTitle() {
        return "扫描测试";
    }

    @Override
    protected int setToolbgcolor() {
        return R.color.white;
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
    protected void init(Bundle savedInstanceState) {
        mScannerView = findViewById(R.id.scanner_view);
        mScannerView.setOnScannerCompletionListener(this);
        mScannerView.setDrawTextColor(Color.RED);
    }

    @Override
    protected void initData() {

    }
    @Override
    protected void onResume() {
        mScannerView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mScannerView.onPause();
        super.onPause();
    }

    @Override
    public void onScannerCompletion(Result rawResult, ParsedResult parsedResult, Bitmap barcode) {


        LogUtils.e("二维码扫描数据：rawresult:"+rawResult.toString()+"   parsedresult:"+parsedResult.toString());
        String result = "";
        if(null!=rawResult){
            result = rawResult.getText();
        }else {
            result = "暂无内容";
        }

        Intent intent = new Intent();
        intent.putExtra("result", result);
        setResult(98, intent);
        finish();

//                QRDecode.decodeQR(barcode, this);
    }
}