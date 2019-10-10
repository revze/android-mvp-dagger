package id.revan.mvpwithdagger.di;

import dagger.Component;

@Component(modules = {ApiModule.class, UIModule.class})
public interface AppComponent extends AppGraph {
}
