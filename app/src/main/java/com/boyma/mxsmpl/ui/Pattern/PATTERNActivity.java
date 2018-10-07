package com.boyma.mxsmpl.ui.Pattern;

import android.os.Bundle;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.boyma.mxsmpl.R;

public class PATTERNActivity extends MvpAppCompatActivity implements IPATTERNActivityView {

    @InjectPresenter
    PATTERNActivityPresenter mpresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

    }

    private void initUI() {

    }

    @Override
    public void showToast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}
