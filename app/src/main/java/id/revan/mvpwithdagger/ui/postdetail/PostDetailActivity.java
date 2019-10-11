package id.revan.mvpwithdagger.ui.postdetail;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import id.revan.mvpwithdagger.R;
import id.revan.mvpwithdagger.api.ApiInterface;
import id.revan.mvpwithdagger.di.Injector;
import id.revan.mvpwithdagger.helper.LayoutHelper;
import id.revan.mvpwithdagger.model.Post;

public class PostDetailActivity extends AppCompatActivity implements PostDetailView {

    @Inject
    ApiInterface apiInterface;

    @Inject
    LayoutHelper layoutHelper;

    private PostDetailPresenter presenter;
    private LinearLayout layoutWrapper;
    private ScrollView svPostDetail;
    private TextView tvTitle;
    private TextView tvBody;
    public static final String ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        String id = getIntent().getStringExtra(ID);

        Injector.obtain().inject(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        layoutWrapper = findViewById(R.id.layout_wrapper);
        svPostDetail = findViewById(R.id.sv_post_detail);
        tvTitle = findViewById(R.id.tv_title);
        tvBody = findViewById(R.id.tv_body);

        presenter = new PostDetailPresenter(this, apiInterface);
        presenter.getPost(id);
    }

    @Override
    public void onLoadPost() {
        layoutWrapper.setVisibility(View.VISIBLE);
        svPostDetail.setVisibility(View.GONE);
        layoutHelper.showLoader(layoutWrapper);
    }

    @Override
    public void onFailedLoadPost(String message) {
        layoutWrapper.setVisibility(View.VISIBLE);
        svPostDetail.setVisibility(View.GONE);
        layoutHelper.showLayoutError(layoutWrapper, message);
    }

    @Override
    public void onSuccessLoadPost(Post post) {
        layoutWrapper.setVisibility(View.GONE);
        svPostDetail.setVisibility(View.VISIBLE);
        tvTitle.setText(post.getTitle());
        tvBody.setText(post.getBody());
    }

    @Override
    public void onDetachView() {
        presenter.onDetach();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
