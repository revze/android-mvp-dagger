package id.revan.mvpwithdagger.ui.main;

import java.util.List;

import id.revan.mvpwithdagger.api.ApiInterface;
import id.revan.mvpwithdagger.model.Post;
import id.revan.mvpwithdagger.ui.base.Presenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements Presenter<MainView> {
    private MainView view;
    private ApiInterface api;

    public MainPresenter(MainView view, ApiInterface api) {
        this.view = view;
        this.api = api;
    }

    @Override
    public void onDetach() {
        view = null;
    }

    void getPosts() {
        if (view != null) {
            view.onLoadPost();
        }

        api.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (view != null) {
                    view.onSuccessLoadPost(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                if (view != null) {
                    view.onFailedLoadPost("Failed load post");
                }
            }
        });
    }
}
