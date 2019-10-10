package id.revan.mvpwithdagger.di;

import android.content.Context;

import id.revan.mvpwithdagger.App;

public class Injector {
    public static AppGraph create(Context context) {
        return DaggerAppComponent.builder().uIModule(new UIModule(context)).build();
    }

    public static AppGraph obtain() {
        return App.get().getInjector();
    }
}
