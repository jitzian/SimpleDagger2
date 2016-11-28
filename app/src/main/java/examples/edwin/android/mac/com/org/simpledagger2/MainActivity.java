package examples.edwin.android.mac.com.org.simpledagger2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import examples.edwin.android.mac.com.org.simpledagger2.adapter.RVArtistAdapter;
import examples.edwin.android.mac.com.org.simpledagger2.model.SpotifyResult;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String BASE_URL = "https://api.spotify.com";

    @BindView(R.id.mEditTextInputArtist)
    EditText mEditTextInputArtist;

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager layoutManager;
    private RVArtistAdapter adapter;

    @Inject
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");

        ButterKnife.bind(this);

        DaggerMainComponent.builder()
                .retrofitModule(new RetrofitModule(BASE_URL))
                .build()
                .inject(this);

        final RetrofitRest retrofitRest = retrofit.create(RetrofitRest.class);

        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        RxTextView.textChanges(mEditTextInputArtist)
                .subscribe(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {
                        Log.d(TAG, "-" + charSequence);

                        if (charSequence.length() > 3) {
                            Observable<SpotifyResult> spotifyResultObservable = retrofitRest.getResponse(String.valueOf(charSequence));
                            spotifyResultObservable.subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Subscriber<SpotifyResult>() {
                                        @Override
                                        public void onCompleted() {
                                            Log.d(TAG, "onCompleted");
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            Log.d(TAG, "onError::" + e.getMessage());
                                        }

                                        @Override
                                        public void onNext(SpotifyResult spotifyResult) {
                                            Log.d(TAG, "onNext::" + spotifyResult);
                                            adapter = new RVArtistAdapter(spotifyResult.getArtists().getItems(), MainActivity.this);
                                            mRecyclerView.setAdapter(adapter);
                                        }
                                    });
                        }
                    }
                });
    }


}
