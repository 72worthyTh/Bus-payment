package ism.com.worthyth.beep.model;



import java.util.List;

import ism.com.worthyth.beep.api.ApiClient;
import ism.com.worthyth.beep.api.ApiInterface;
import ism.com.worthyth.beep.interfaces.MainViewUsers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {
    private MainViewUsers view;

    public MainPresenter(MainViewUsers view) {
        this.view = view;
    }

 public   void getDataCompte() {
        view.showLoading();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Users>> call = apiInterface.getUsers();
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetResultUsers(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }
}
