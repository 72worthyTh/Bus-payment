package ism.com.worthyth.beep.model;

import ism.com.worthyth.beep.api.ApiClient;
import ism.com.worthyth.beep.api.ApiInterface;
import ism.com.worthyth.beep.interfaces.EditorView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorPresenterUsers {
    private EditorView view;

    public EditorPresenterUsers(EditorView view) {
        this.view = view;
    }
  public  void saveUsers(final String nom,final String prenom,final String telephone,
                     final String type,final String permis,final String commerce,final String nomboutique,
                     final String compte,final String username,final String password,int userConn){
        view.showProgress();
        ApiInterface apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        Call<Users> call=apiInterface.saveUser(nom,prenom,telephone,type,permis,commerce,nomboutique,compte,username,password,userConn);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                view.hideProgress();
                if(response.isSuccessful()&& response.body()!=null){
                    Boolean success=response.body().getSuccess();
                    if(success){
                        view.onRequestSuccess(response.body().getMessage());
                    }
                    else {
                        view.onRequestError(response.body().getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }
   /* void updateEmploye( int idEmp,String nom,String prenom, String service,
                        String sexe, String adresse, String email, String tel,
                        String cni,int color){
        view.showProgress();
        ApiInterface apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        Call<Employe> call=apiInterface.updateEmploye(idEmp,nom,prenom,service,sexe,adresse,email,tel,cni,color);
        call.enqueue(new Callback<Employe>() {
            @Override
            public void onResponse(Call<Employe> call, Response<Employe> response) {
                view.hideProgress();
                if(response.isSuccessful()&& response.body()!=null){
                    Boolean success=response.body().getSuccess();
                    if(success){
                        view.onRequestSuccess(response.body().getMessage());
                    }
                    else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<Employe> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    void deleteEmploye( int idEmp){
        view.showProgress();
        ApiInterface apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        Call<Employe> call=apiInterface.deleteEmploye(idEmp);
        call.enqueue(new Callback<Employe>() {
            @Override
            public void onResponse(Call<Employe> call, Response<Employe> response) {
                view.hideProgress();
                if(response.isSuccessful()&& response.body()!=null){
                    Boolean success=response.body().getSuccess();
                    if(success){
                        view.onRequestSuccess(response.body().getMessage());
                    }
                    else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<Employe> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }*/
}
