package ism.com.worthyth.beep.interfaces;

public interface EditorView {
    void showProgress();
    void hideProgress();
    void onRequestSuccess(String messege);
    void onRequestError(String messege);
}
