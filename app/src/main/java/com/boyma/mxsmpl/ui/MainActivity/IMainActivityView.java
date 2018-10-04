package com.boyma.mxsmpl.ui.MainActivity;

import com.boyma.mxsmpl.base.IBaseMvpView;
import com.boyma.mxsmpl.data.jsonplaceholder.allnews.models.ResponseJsonObj;

import java.util.List;

public interface IMainActivityView extends IBaseMvpView {

    void showLoadingDialog();

    void hideLogTextView();

    void hideLoadingView();

    void addToList(List<ResponseJsonObj> siteJsonObj);
}
