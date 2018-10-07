package com.boyma.mxsmpl.ui.LoginActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.boyma.mxsmpl.R;
import com.boyma.mxsmpl.ui.MainActivity.MainActivity;

public class LoginActivity extends MvpAppCompatActivity implements ILoginActivityView {

    @InjectPresenter
    LoginActivityPresenter mpresenter;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUI();


    }

    private void initUI() {
        Button signin = findViewById(R.id.btn_login);
        EditText email = findViewById(R.id.input_email);
        EditText pass = findViewById(R.id.input_password);
        signin.setOnClickListener(v -> {
            mpresenter.onSignInClick(email.getText().toString(),pass.getText().toString());
        });
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoadingDialog() {
        progress = new ProgressDialog(this);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setTitle("Загрузка");
        progress.setMessage("Подождите...");
        progress.setCancelable(false);
        progress.show();
    }

    @Override
    public void hideLoadingDialog() {
        progress.dismiss();
    }

    @Override
    public void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
