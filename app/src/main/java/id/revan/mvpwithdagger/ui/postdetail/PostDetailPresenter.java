package id.revan.mvpwithdagger.ui.postdetail;

import id.revan.mvpwithdagger.api.ApiInterface;
import id.revan.mvpwithdagger.model.Post;
import id.revan.mvpwithdagger.ui.base.Presenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDetailPresenter implements Presenter<PostDetailView> {
    private PostDetailView view;
    private ApiInterface api;

    public PostDetailPresenter(PostDetailView view, ApiInterface api) {
        this.view = view;
        this.api = api;
    }

    @Override
    public void onDetach() {
        view = null;
    }

    void getPost(String id) {
        if (view != null) {
            view.onLoadPost();
        }

        api.getPost(id).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (view != null) {
                    view.onSuccessLoadPost(response.body());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                if (view != null) {
                    view.onFailedLoadPost("Failed load post");
                }
            }
        });
    }
}
