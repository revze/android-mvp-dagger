package id.revan.mvpwithdagger;

import android.app.Application;

import id.revan.mvpwithdagger.di.AppGraph;
import id.revan.mvpwithdagger.di.Injector;

public class App extends Application {
    private static App app;
    private AppGraph appGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
        appGraph = Injector.create(this);
    }

    public static App get() {
        return app;
    }

    public AppGraph getInjector() {
        return appGraph;
    }
}
