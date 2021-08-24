package ism.com.worthyth.beep.model;


import ism.com.worthyth.beep.api.ApiClient;
import ism.com.worthyth.beep.api.ApiInterface;
import ism.com.worthyth.beep.interfaces.EditorView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorPresenterCompte {
    private EditorView view;

    public EditorPresenterCompte(EditorView view) {
        this.view = view;
    }
   /* void saveCompte(final String type,final double solde, final double decouvert,
                    final double taux, final int proprietaire,final String employe, final int color){
        view.showProgress();
        ApiInterface apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        Call<Compte> call=apiInterface.saveCompte(type, solde, decouvert, taux, proprietaire, employe, color);
        call.enqueue(new Callback<Compte>() {
            @Override
            public void onResponse(Call<Compte> call, Response<Compte> response) {
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
            public void onFailure(Call<Compte> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }
    void updateCompte(int numero,String type,double solde, double decouvert,
                      double taux, int proprietaire, String employe, int color){
        view.showProgress();
        ApiInterface apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        Call<Compte> call=apiInterface.updateCompte(numero, type, solde, decouvert, taux,proprietaire, employe, color);
        call.enqueue(new Callback<Compte>() {
            @Override
            public void onResponse(Call<Compte> call, Response<Compte> response) {
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
            public void onFailure(Call<Compte> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    void deleteCompte( int idCompte){
        view.showProgress();
        ApiInterface apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        Call<Compte> call=apiInterface.deleteCompte(idCompte);
        call.enqueue(new Callback<Compte>() {
            @Override
            public void onResponse(Call<Compte> call, Response<Compte> response) {
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
            public void onFailure(Call<Compte> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });

    }
    void retrait(int numeroCompte,double solde){
        view.showProgress();
        ApiInterface apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        Call<Compte> call=apiInterface.retrait(numeroCompte,solde);
        call.enqueue(new Callback<Compte>() {
            @Override
            public void onResponse(Call<Compte> call, Response<Compte> response) {
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
            public void onFailure(Call<Compte> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });

    }

    void verser(int numeroCompte,double solde){
        view.showProgress();
        ApiInterface apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        Call<Compte> call=apiInterface.verser(numeroCompte,solde);
        call.enqueue(new Callback<Compte>() {
            @Override
            public void onResponse(Call<Compte> call, Response<Compte> response) {
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
            public void onFailure(Call<Compte> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });

    }*/
  public  void virer(String numero,String numero2,double solde){
        view.showProgress();
        ApiInterface apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        Call<Compte> call=apiInterface.virer(numero,numero2,solde);
        call.enqueue(new Callback<Compte>() {
            @Override
            public void onResponse(Call<Compte> call, Response<Compte> response) {
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
            public void onFailure(Call<Compte> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });

    }


    public  void recharge(String numero,String numero2,double solde){
        view.showProgress();
        ApiInterface apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        Call<Compte> call=apiInterface.recharger(numero,numero2,solde);
        call.enqueue(new Callback<Compte>() {
            @Override
            public void onResponse(Call<Compte> call, Response<Compte> response) {
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
            public void onFailure(Call<Compte> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });

    }


    public  void retrait(String numero,String numero2,double solde){
        view.showProgress();
        ApiInterface apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        Call<Compte> call=apiInterface.retirer(numero,numero2,solde);
        call.enqueue(new Callback<Compte>() {
            @Override
            public void onResponse(Call<Compte> call, Response<Compte> response) {
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
            public void onFailure(Call<Compte> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });

    }
}


