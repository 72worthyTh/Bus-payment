package ism.com.worthyth.beep.interfaces;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
public interface RegisterInterface {
    String REGIURL = "https://demonuts.com/Demonuts/JsonTest/Tennis/";
    @FormUrlEncoded
    @POST("simpleregister.php")
    Call<String> getUserRegi(
            @Field("nom") String nom,
            @Field("prenom") String prenom,
            @Field("telephone") String telephone,
            @Field("nomU") String nomUtilisateur,
            @Field("motdepasse") String motdepasse
    );
}
