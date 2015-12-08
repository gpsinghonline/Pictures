package ua.regin.pictures.ui;

import android.content.Context;
import android.widget.Toast;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

public class BaseActivity extends RxAppCompatActivity {

    public Context getContext() {
        return this;
    }

    public void handleError(Throwable e) {
        Toast.makeText(getContext(), "Internet connection no available", Toast.LENGTH_SHORT).show();
    }
}
