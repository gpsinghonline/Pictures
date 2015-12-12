package ua.regin.pictures.application;

import ua.regin.pictures.utils.AnalyticsTrackers;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AnalyticsTrackers.initialize(this);
        AnalyticsTrackers.getInstance().get(AnalyticsTrackers.Target.APP);
    }
}
