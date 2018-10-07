package com.boyma.mxsmpl.ui.PhotosActivity;

import com.boyma.mxsmpl.base.IBaseMvpView;

import java.util.List;

public interface IPhotosActivityView extends IBaseMvpView {

    void refreshGrid(List<String> uris);

    void hideEmptyText();
}
