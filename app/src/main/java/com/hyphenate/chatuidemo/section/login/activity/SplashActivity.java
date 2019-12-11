package com.hyphenate.chatuidemo.section.login.activity;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;

import androidx.lifecycle.ViewModelProviders;

import com.hyphenate.chatuidemo.MainActivity;
import com.hyphenate.chatuidemo.R;
import com.hyphenate.chatuidemo.core.utils.EmLog;
import com.hyphenate.chatuidemo.section.base.BaseInitActivity;
import com.hyphenate.chatuidemo.core.enums.Status;
import com.hyphenate.chatuidemo.section.login.viewmodels.SplashViewModel;

public class SplashActivity extends BaseInitActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.em_splash_activity;
    }

    @Override
    protected void initSystemFit() {
        setFitSystemForTheme(false, R.color.transparent);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(300);
        findViewById(R.id.iv_splash).startAnimation(animation);
    }

    @Override
    protected void initData() {
        super.initData();
        SplashViewModel model = ViewModelProviders.of(this).get(SplashViewModel.class);
        model.getLoginData().observe(this, response -> {
            if(response == null) {
                return;
            }
            if(response.status == Status.SUCCESS) {
                MainActivity.startAction(mContext);
                finish();
            }else if(response.status == Status.ERROR) {
                EmLog.i("TAG", "error message = "+response.getMessage());
                LoginActivity.startAction(mContext);
                finish();
            }
        });
    }
}
