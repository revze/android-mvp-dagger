package id.revan.mvpwithdagger.di;

import id.revan.mvpwithdagger.ui.main.MainActivity;
import id.revan.mvpwithdagger.ui.postdetail.PostDetailActivity;

public interface AppGraph {
    void inject(MainActivity activity);

    void inject(PostDetailActivity activity);
}
