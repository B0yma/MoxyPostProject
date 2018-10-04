package com.boyma.mxsmpl.ui.MainActivity;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.boyma.mxsmpl.common.Constants;
import com.boyma.mxsmpl.data.jsonplaceholder.allnews.JsonPlaceHolderRepo;
import com.boyma.mxsmpl.data.jsonplaceholder.allnews.models.ResponseJsonObj;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<IMainActivityView>{

    private JsonPlaceHolderRepo jsonPlaceHolderRepo;
    private boolean isLoading = false;
    private List<ResponseJsonObj> postList = new ArrayList<>();


    public MainActivityPresenter() {
        jsonPlaceHolderRepo = new JsonPlaceHolderRepo();
        initView();
    }

    private void initView() {
        showLoad();
        loadCurrencyListView();
    }

    private void showLoad() {
        isLoading = true;
        getViewState().showLoadingDialog();
    }

    private void loadCurrencyListView() {

        Observable<List<ResponseJsonObj>> dataObservable = jsonPlaceHolderRepo.getData();
        dataObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //.delay(1, TimeUnit.MILLISECONDS,AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<ResponseJsonObj>>() {
                    @Override
                    public void onNext(List<ResponseJsonObj> siteJsonObj) {

                        postList.addAll(siteJsonObj);
                        getViewState().addToList(siteJsonObj);

                        hideload();
                        getViewState().hideLogTextView();
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideload();
                        getViewState().showToast(e.toString());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void hideload() {
        isLoading = false;
        getViewState().hideLoadingView();
    }


    public void onItemClick(int position) {
        /*if (getViewState().getOnlineStatus()){
            //getView().startNewsActivity(newsList.get(pos).getId());
        }else {
            getViewState().showToast("No connection");
        }*/
    }


}
