package com.example.zs_wan_android.main.home;

import com.example.zs_wan_android.base.IBaseView;

public interface HomeContract {
    interface View extends IBaseView {
        void showData();
    }

    interface Presenter{
        void loadData();
    }
}