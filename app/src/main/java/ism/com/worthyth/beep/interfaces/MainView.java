package ism.com.worthyth.beep.interfaces;

;

import java.util.List;

import ism.com.worthyth.beep.model.GetMomCompte;


public interface MainView {
    void showLoading();
    void hideLoading();
    void onGetResultGetMomCompte(List<GetMomCompte>comptes);
    void onErrorLoading(String message);
}
