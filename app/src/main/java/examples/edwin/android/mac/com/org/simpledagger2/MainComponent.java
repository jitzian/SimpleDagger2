package examples.edwin.android.mac.com.org.simpledagger2;

import dagger.Component;

/**
 * Created by User on 11/26/2016.
 */

@Component(modules = {RetrofitModule.class})
public interface MainComponent {

    void inject(MainActivity mainActivity);
}
