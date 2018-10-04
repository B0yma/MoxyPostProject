package com.boyma.mxsmpl.ui.LoginActivity;

import com.boyma.mxsmpl.base.IBaseMvpView;

public interface ILoginActivityView extends IBaseMvpView {

    void showLoadingDialog();

    void hideLoadingDialog();

    void startMainActivity();
}
