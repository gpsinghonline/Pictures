package ua.regin.pictures.ui;

import android.content.Context;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

public class BaseActivity extends RxAppCompatActivity {

    public Context getContext() {
        return this;
    }
}
