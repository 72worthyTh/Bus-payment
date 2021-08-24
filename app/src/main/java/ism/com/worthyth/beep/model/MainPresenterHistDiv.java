package ism.com.worthyth.beep.model;



import java.util.List;

import ism.com.worthyth.beep.api.ApiClient;
import ism.com.worthyth.beep.api.ApiInterface;
import ism.com.worthyth.beep.interfaces.MainViewHisDiv;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenterHistDiv {
    private MainViewHisDiv view;

    public MainPresenterHistDiv(MainViewHisDiv view) {
        this.view = view;
    }

   public void getDataHistoryDiv(){
        view.showLoading();
        ApiInterface apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<HistoryDivers>> call=apiInterface.getOps();
        call.enqueue(new Callback<List<HistoryDivers>>() {
            @Override
            public void onResponse(Call<List<HistoryDivers>> call, Response<List<HistoryDivers>> response) {
                view.hideLoading();
                if(response.isSuccessful()&& response.body()!=null){
                    view.onGetResultCompte(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<HistoryDivers>> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}
