package ism.com.worthyth.beep.interfaces;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginInterface {

    String LOGINURL = "https://10.0.2.2/soutrali_api/";
    @FormUrlEncoded
    @POST("simplelogin.php")
    Call<String> getUserLogin(

            @Field("nomutilisateur") String username,
            @Field("motdepasse") String password
    );
}
