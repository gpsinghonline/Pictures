package ua.regin.vipjanta.api;

import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import org.androidannotations.annotations.EBean;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

@EBean(scope = EBean.Scope.Singleton)
public class ApiManager {

    private static final String BASE_URL = "http://www.vipjanta.net/api";

    public static final String DATE_FORMAT__NORMAL_WITH_YEAR = "yyyy-MM-dd HH:mm:ss";

    private final RestAdapter restAdapter;

    public ApiManager() {
        restAdapter = new RestAdapter.Builder()
                .setClient(new OkClient(new OkHttpClient()))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(new GsonBuilder()
                        .setDateFormat(DATE_FORMAT__NORMAL_WITH_YEAR)
                        .create()))
                .setEndpoint(BASE_URL).build();
    }

    public RestAdapter getRestAdapter() {
        return restAdapter;
    }
}