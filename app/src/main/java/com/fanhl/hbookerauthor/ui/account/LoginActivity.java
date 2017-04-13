package com.fanhl.hbookerauthor.ui.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.widget.Button;

import com.fanhl.hbookerauthor.R;
import com.fanhl.hbookerauthor.common.Local;
import com.fanhl.hbookerauthor.io.rest.data.request.LoginForm;
import com.fanhl.hbookerauthor.ui.common.BaseActivity;
import com.fanhl.hbookerauthor.util.Log;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class LoginActivity extends BaseActivity {
    public static final String TAG = LoginActivity.class.getSimpleName();

    private TextInputEditText emailEt;
    private TextInputEditText passwordEt;
    private Button loginBtn;

    public static void launch(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        assginViews();
        initData();
    }

    private void assginViews() {
        this.emailEt = (TextInputEditText) findViewById(R.id.emailEt);
        this.passwordEt = (TextInputEditText) findViewById(R.id.passwordEt);
        this.loginBtn = (Button) findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(v -> preLogin());
    }

    private void initData() {
        // FIXME: 2017/4/7 TEST
        emailEt.setText(Local.email);
        passwordEt.setText(Local.password);
    }

    private void preLogin() {
        String email = emailEt.getText().toString();
        String password = passwordEt.getText().toString();

        LoginForm loginForm = new LoginForm(email, password);

        loginBtn.setEnabled(false);
        checkForm(loginForm, new CheckFormCallback() {
            @Override
            public void onSuccess(LoginForm loginForm) {
                login(loginForm);
            }

            @Override
            public void onFail() {
                loginBtn.setEnabled(true);
            }
        });
    }

    private void checkForm(LoginForm loginForm, CheckFormCallback checkFormCallback) {
        // FIXME: 2017/4/7
        checkFormCallback.onSuccess(loginForm);
    }

    private void login(LoginForm loginForm) {
        getApp().getClient().getAccountService()
                .login(loginForm)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody response) {
//                        MainActivity.launch(LoginActivity.this);
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        loginBtn.setEnabled(true);
                    }

                    @Override
                    public void onComplete() {
                        loginBtn.setEnabled(true);
//                        getViewList();
                    }
                });
    }

    @Deprecated
    private void getViewList() {
        getApp().getClient().getBookService()
                .getView_list()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Log.d(TAG, "responseBody:" + responseBody);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "getView_list:", e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private interface CheckFormCallback {
        void onSuccess(LoginForm loginForm);

        void onFail();
    }
}
