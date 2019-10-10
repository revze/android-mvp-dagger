package id.revan.mvpwithdagger.api;

import java.util.List;

import id.revan.mvpwithdagger.model.Post;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("posts")
    Call<List<Post>> getPosts();
}
