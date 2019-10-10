package id.revan.mvpwithdagger.ui.main;

import java.util.List;

import id.revan.mvpwithdagger.model.Post;
import id.revan.mvpwithdagger.ui.base.View;

public interface MainView extends View {
    void onLoadPost();

    void onFailedLoadPost(String message);

    void onSuccessLoadPost(List<Post> posts);
}
