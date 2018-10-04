package com.boyma.mxsmpl.ui.Pattern;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<IMainActivityView>{

    public MainActivityPresenter() {
        getViewState().showToast("gg");
    }


}
