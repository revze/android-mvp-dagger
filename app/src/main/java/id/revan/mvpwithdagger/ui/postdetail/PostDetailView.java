package id.revan.mvpwithdagger.ui.postdetail;

import id.revan.mvpwithdagger.model.Post;
import id.revan.mvpwithdagger.ui.base.View;

public interface PostDetailView extends View {
    void onLoadPost();

    void onFailedLoadPost(String message);

    void onSuccessLoadPost(Post post);
}
