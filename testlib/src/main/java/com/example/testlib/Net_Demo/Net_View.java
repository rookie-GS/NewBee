package com.example.testlib.Net_Demo;

import com.example.base_common_lib.Base.BaseMVPLifeView;
import com.example.base_common_lib.bean.ResultBean;

import java.util.List;

public interface Net_View extends BaseMVPLifeView {
    void onRequestSuccess(List<ResultBean> contentBeanList);
}
