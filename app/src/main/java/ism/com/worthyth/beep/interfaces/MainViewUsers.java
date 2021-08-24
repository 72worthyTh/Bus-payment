package ism.com.worthyth.beep.interfaces;



import java.util.List;

import ism.com.worthyth.beep.model.Users;


public interface MainViewUsers {
    void showLoading();
    void hideLoading();
    void onGetResultUsers(List<Users> users);
    void onErrorLoading(String message);
}
