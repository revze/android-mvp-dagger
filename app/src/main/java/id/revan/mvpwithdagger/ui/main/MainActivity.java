package id.revan.mvpwithdagger.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import id.revan.mvpwithdagger.App;
import id.revan.mvpwithdagger.R;
import id.revan.mvpwithdagger.adapter.PostAdapter;
import id.revan.mvpwithdagger.api.ApiInterface;
import id.revan.mvpwithdagger.di.AppComponent;
import id.revan.mvpwithdagger.di.DaggerAppComponent;
import id.revan.mvpwithdagger.di.Injector;
import id.revan.mvpwithdagger.di.UIModule;
import id.revan.mvpwithdagger.helper.LayoutHelper;
import id.revan.mvpwithdagger.model.Post;

public class MainActivity extends AppCompatActivity implements MainView {

    @Inject
    ApiInterface api;

    @Inject
    LayoutHelper layoutHelper;

    private MainPresenter presenter;
    private List<Post> posts = new ArrayList<>();
    private PostAdapter adapter;
    private RecyclerView rvPost;
    private LinearLayout layoutWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Injector.obtain().inject(this);

        rvPost = findViewById(R.id.rv_post);
        adapter = new PostAdapter(posts);
        rvPost.setAdapter(adapter);
        rvPost.setLayoutManager(new LinearLayoutManager(this));

        layoutWrapper = findViewById(R.id.layout_wrapper);

        presenter = new MainPresenter(this, api);
        presenter.getPosts();
    }

    @Override
    public void onLoadPost() {
        layoutWrapper.setVisibility(View.VISIBLE);
        rvPost.setVisibility(View.GONE);
        layoutHelper.showLoader(layoutWrapper);
    }

    @Override
    public void onFailedLoadPost(String message) {
        layoutWrapper.setVisibility(View.VISIBLE);
        rvPost.setVisibility(View.GONE);
        layoutHelper.showLayoutError(layoutWrapper, message);
    }

    @Override
    public void onSuccessLoadPost(List<Post> posts) {
        layoutWrapper.setVisibility(View.GONE);
        rvPost.setVisibility(View.VISIBLE);
        this.posts.clear();
        this.posts.addAll(posts);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDetachView() {
        presenter.onDetach();
    }
}
