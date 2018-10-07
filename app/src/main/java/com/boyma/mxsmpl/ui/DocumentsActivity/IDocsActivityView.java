package com.boyma.mxsmpl.ui.DocumentsActivity;

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.boyma.mxsmpl.base.IBaseMvpView;
import com.boyma.mxsmpl.net.jsonplaceholder.allnews.models.ResponseJsonObj;

import java.util.List;

public interface IDocsActivityView extends IBaseMvpView {

    void showLoadingDialog();

    void hideLogTextView();

    void hideLoadingView();

    void addToList(List<ResponseJsonObj> siteJsonObj);

    @StateStrategyType(SkipStrategy.class)
    void startPhotosActivity(int idtype1, Integer idtype2);
}
