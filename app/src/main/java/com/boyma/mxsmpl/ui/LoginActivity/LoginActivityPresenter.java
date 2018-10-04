package com.boyma.mxsmpl.ui.LoginActivity;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.boyma.mxsmpl.data.jsonplaceholder.allnews.JsonPlaceHolderRepo;

@InjectViewState
public class LoginActivityPresenter extends MvpPresenter<ILoginActivityView>{

    private JsonPlaceHolderRepo jsonPlaceHolderRepo;

    public LoginActivityPresenter() {
        this.jsonPlaceHolderRepo = new JsonPlaceHolderRepo();
    }


    public void onSignInClick(String email, String pass) {
/*
        getViewState().showLoadingDialog();

        Observable<Authorization> auth = authrepo.loginWithPassword(email,pass);

        auth.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .delay(10000, TimeUnit.MILLISECONDS,AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Authorization>() {
                    @Override
                    public void onNext(Authorization siteJsonObj) {

                        getViewState().showToast("Auth Access!");
                        getViewState().startMainActivity();

                    }

                    @Override
                    public void onError(Throwable e) {



                        getViewState().showToast(e.getMessage());
                        getViewState().hideLoadingDialog();
                    }

                    @Override
                    public void onComplete() {
                        getViewState().hideLoadingDialog();
                    }
                });*/
        getViewState().startMainActivity();
    }
}
