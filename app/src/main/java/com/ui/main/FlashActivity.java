package com.ui.main;

import android.view.View;
import android.view.animation.AlphaAnimation;

import com.C;
import com.EventTags;
import com.app.annotation.javassist.Bus;
import com.apt.TRouter;
import com.base.BaseActivity;
import com.base.event.OkBus;
import com.base.util.AnimationUtil;
import com.base.util.StatusBarUtil;

import butterknife.Bind;

/**
 * Created by baixiaokang on 16/4/28.
 */
public class FlashActivity extends BaseActivity {

    @Bind(R.id.view)
    View view;

    @Override
    public int getLayoutId() {
        return R.layout.activity_flash;
    }

    @Override
    public void initView() {
        OkBus.getInstance().onStickyEvent(EventTags.FLASH_INIT_UI, null);
    }

    @Bus(EventTags.FLASH_INIT_UI)
    public void initUI() {
        StatusBarUtil.setTranslucentBackground(this);
        AlphaAnimation anim = new AlphaAnimation(0.8f, 0.1f);
        anim.setDuration(5000);
        view.startAnimation(anim);
        AnimationUtil.setAnimationListener(anim, () -> OkBus.getInstance().onEvent(EventTags.JUMP_TO_MAIN, null));
    }

    @Bus(EventTags.JUMP_TO_MAIN)
    public void jumpToMainPage() {
        TRouter.go(C.HOME);
        // startActivity(new Intent(mContext, HomeActivity.class));
        finish();
    }
}
