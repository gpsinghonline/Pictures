package ua.regin.vipjanta.application;

import ua.regin.vipjanta.utils.AnalyticsTrackers;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AnalyticsTrackers.initialize(this);
        AnalyticsTrackers.getInstance().get(AnalyticsTrackers.Target.APP);
    }
}
