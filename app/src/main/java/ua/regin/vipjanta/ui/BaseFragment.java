package ua.regin.vipjanta.ui;

import android.content.Context;
import android.widget.Toast;

import com.trello.rxlifecycle.components.support.RxFragment;

public class BaseFragment extends RxFragment {

    public Context getContext() {
        return getActivity();
    }

    public void handleError(Throwable e) {
        Toast.makeText(getContext(), "Internet connection no available", Toast.LENGTH_SHORT).show();
    }
}
