package com.boyma.mxsmpl.ui.LoginActivity;

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.boyma.mxsmpl.base.IBaseMvpView;

public interface ILoginActivityView extends IBaseMvpView {

    void showLoadingDialog();

    void hideLoadingDialog();

    @StateStrategyType(SkipStrategy.class)
    void startMainActivity();
}
