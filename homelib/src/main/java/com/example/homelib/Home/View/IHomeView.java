package com.example.homelib.Home.View;


import com.example.base_common_lib.Base.BaseMVPLifeView;
import com.example.base_common_lib.bean.ResultBean;

import java.util.List;

public interface IHomeView extends BaseMVPLifeView {
    void onRequestSuccess(List<ResultBean> contentBeanList);

}
