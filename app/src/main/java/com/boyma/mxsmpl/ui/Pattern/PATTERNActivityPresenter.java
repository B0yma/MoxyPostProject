package com.boyma.mxsmpl.ui.Pattern;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class PATTERNActivityPresenter extends MvpPresenter<IPATTERNActivityView>{

    private int idtype1;
    private int idtype2;

    public PATTERNActivityPresenter() {
        getViewState().showToast("gg");
    }


    public void onCreate(int idtype1, int idtype2) {
        this.idtype1 = idtype1;
        this.idtype2 = idtype2;
        getViewState().showToast(String.valueOf(idtype1)+":"+String.valueOf(idtype2));
    }
}
