package ism.com.worthyth.beep.interfaces;



import java.util.List;

import ism.com.worthyth.beep.model.HistoryDivers;


public interface MainViewHisDiv {

    void showLoading();
    void hideLoading();
    void onGetResultCompte(List<HistoryDivers> historyDivers);
    void onErrorLoading(String message);

}
