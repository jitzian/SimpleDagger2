package examples.edwin.android.mac.com.org.simpledagger2;

import examples.edwin.android.mac.com.org.simpledagger2.model.SpotifyResult;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by User on 11/26/2016.
 */

public interface RetrofitRest {

    @GET("/v1/search?type=artist")
    Observable<SpotifyResult>getResponse(@Query("q")String query);

}
