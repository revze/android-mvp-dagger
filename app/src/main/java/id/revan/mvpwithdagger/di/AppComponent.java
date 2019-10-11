package id.revan.mvpwithdagger.di;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class, UIModule.class})
public interface AppComponent extends AppGraph {
}
