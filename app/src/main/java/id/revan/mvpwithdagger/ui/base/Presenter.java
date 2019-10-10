package id.revan.mvpwithdagger.ui.base;

public interface Presenter<T extends View> {
    void onDetach();
}
