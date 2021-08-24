package ism.com.worthyth.beep.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://www.gebumas.com/beeppay/beepapi/";
    private static Retrofit retrofit;
    private static ApiClient mInstence;

    private static  Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    public static Retrofit getApiClient() {

        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();        }

        return retrofit;

    }
    public static synchronized ApiClient getInstance(){
        if(mInstence==null) {
            mInstence=new ApiClient();
        }
        return mInstence;
    }
    public ApiInterface getApi(){
        return retrofit.create(ApiInterface.class);
    }
}
