package ism.com.worthyth.beep.api;
import java.util.List;

import ism.com.worthyth.beep.model.Compte;
import ism.com.worthyth.beep.model.GetMomCompte;
import ism.com.worthyth.beep.model.HistoryDivers;
import ism.com.worthyth.beep.model.Users;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
   @FormUrlEncoded
    @POST("saveUser.php")
    Call<Users> saveUser(
            @Field("nom") String nom,
            @Field("prenom") String prenom ,
            @Field("telephone") String telephone,
            @Field("type") String type,
            @Field("permis") String permis,
            @Field("idcommerce") String commerce,
            @Field("nomboutique") String nomboutique,
            @Field("compte") String compte,
            @Field("username") String usename,
            @Field("password") String password,
            @Field("createdby") int iduserConn
    );

    @GET("Users.php")
    Call<List<Users>> getUsers();
    @GET("Users.php")
    Call<List<Users>> getUsersBykey(
            @Query("key") int keyword
    );
    @GET("getUsers.php")
    Call<List<Users>> getUserConBykey(
            @Query("key") String keyword
    );

  /*  @GET("getEmploye.php")
    Call<List<Employe>> getEmployeBykey(
            @Query("key") String keyword
    );


    @FormUrlEncoded
    @POST("updateEmp.php")
    Call<Employe> updateEmploye(
            @Field("idEmp") int idEmp,
            @Field("nom") String nom,
            @Field("prenom") String prenom ,
            @Field("service") String service,
            @Field("sexe") String sexe,
            @Field("adresse") String adresse,
            @Field("email") String email,
            @Field("telephone") String telephone,
            @Field("cni") String cni,
            @Field("color") int color   );

    @FormUrlEncoded
    @POST("deleteEmp.php")
    Call<Employe> deleteEmploye(
            @Field("idEmp") int idEmp );



    @FormUrlEncoded
    @POST("saveCompte.php")
    Call<Compte> saveCompte(
            @Field("type") String type ,
            @Field("solde") double solde,
            @Field("decouvert")double decouvert,
            @Field("taux") double taux,
            @Field("proprietaire") int proprietaire,
            @Field("employe") String employe,
            @Field("color") int color
    );
;
  */
   @GET("getmomsolde.php")
   Call<List<GetMomCompte>> getComptes(
           @Query("key")String keyword
   );


    @GET("getmomsolde.php")
    Call<List<GetMomCompte>> getCompteBykey(
           @Query("key")String keyword
    );

 /*
    @GET("getmomsolde.php")
    Call<List<Compte>> getCompteBynumero(
            @Query("key") String keyword
    );
    /*@FormUrlEncoded
    @POST("updateCompte.php")
    Call<Compte> updateCompte(
            @Field("numeroCompte") int numero,
            @Field("type") String type ,
            @Field("solde") double solde,
            @Field("decouvert")double decouvert,
            @Field("taux") double taux,
            @Field("proprietaire") int proprietaire,
            @Field("employe") String employe,
            @Field("color") int color
    );
   /* @FormUrlEncoded
    @POST("retraitCompte.php")
    Call<Compte> retrait(
            @Field("numeroCompte") int numero,
            @Field("solde") double solde
    );
    @FormUrlEncoded
    @POST("verserCompte.php")
    Call<Compte> verser(
            @Field("numeroCompte") int numero,
            @Field("solde") double solde
    );*/
    @FormUrlEncoded
    @POST("virerCompte.php")
    Call<Compte> virer(
            @Field("numero1") String numeroi,
            @Field("numero2") String numerov,
            @Field("solde") double solde
    );

    @FormUrlEncoded
    @POST("rechargerCompte.php")
    Call<Compte> recharger(
            @Field("numero1") String numeroi,
            @Field("numero2") String numerov,
            @Field("solde") double solde
    );

    @FormUrlEncoded
    @POST("retrait.php")
    Call<Compte> retirer(
            @Field("numero1") String numeroi,
            @Field("numero2") String numerov,
            @Field("solde") double solde
    );
   /* @FormUrlEncoded
    @POST("deleteCompte.php")
    Call<Compte> deleteCompte(
            @Field("numeroCompte") int idCompte );



    @FormUrlEncoded
    @POST("saveOps.php")
    Call<Operation> saveOp(
            @Field("compte") String compte,
            @Field("description") String note,
            @Field("employe") String employe,
            @Field("color") int color   );*/

    @GET("HistoryDivers.php")
    Call<List<HistoryDivers>> getOps();

    @GET("getHistoryDivers.php")
    Call<List<HistoryDivers>> getOpBykey(
            @Query("key") String keyword
    );
    /*
    @GET("getMesOps.php")
    Call<List<Operation>> getOpMesOp(
            @Query("key") String keyword
    );
    @FormUrlEncoded
    @POST("updateOps.php")
    Call<Operation> updateOps(
            @Field("idOp") int idOp,
            @Field("compte") String compte,
            @Field("description") String note,
            @Field("employe") String employe,
            @Field("color") int color    );

    @FormUrlEncoded
    @POST("deleteOps.php")
    Call<Operation> deleteOps(
            @Field("idOp") int id );





    @POST("get_pets.php")
    Call<List<Pets>> getPets();

    @FormUrlEncoded
    @POST("add_pet.php")
    Call<Pets> insertPet(
            @Field("key") String key,
            @Field("nomCl") String nom,
            @Field("prenomCl") String prenom,
            @Field("etaCivil") int etatCivil,
            @Field("sexe") int sexe,
            @Field("dateNaiss") String datenaiss,
            @Field("adresse") String adresse,
            @Field("nationalite") String nationalite,
            @Field("telephone") String telephone,
            @Field("email") String email,
            @Field("CNI") String cni,
            @Field("MatriculeEmp") String matriculeEmp,
            @Field("photo") String picture);

    @FormUrlEncoded
    @POST("update_pet.php")
    Call<Pets> updatePet(
            @Field("key") String key,
            @Field("idCl") int id,
            @Field("nomCl") String nom,
            @Field("prenomCl") String prenom,
            @Field("etaCivil") int etatCivil,
            @Field("sexe") int sexe,
            @Field("dateNaiss") String datenaiss,
            @Field("adresse") String adresse,
            @Field("nationalite") String nationalite,
            @Field("telephone") String telephone,
            @Field("email") String email,
            @Field("CNI") String cni,
            @Field("MatriculeEmp") String matriculeEmp,
            @Field("photo") String picture);

    @FormUrlEncoded
    @POST("delete_pet.php")
    Call<Pets> deletePet(
            @Field("key") String key,
            @Field("idCl") int id,
            @Field("photo") String picture);

    @FormUrlEncoded
    @POST("update_love.php")
    Call<Pets> updateLove(
            @Field("key") String key,
            @Field("idCl") int id,
            @Field("love") boolean love);

}*/
}
