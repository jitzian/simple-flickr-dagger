package com.example.raian.com.org.simpleflicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.raian.com.org.simpleflicker.adapter.RVFlickerAdapter;
import com.example.raian.com.org.simpleflicker.component.DaggerMainComponent;
import com.example.raian.com.org.simpleflicker.model.Result;
import com.example.raian.com.org.simpleflicker.module.RestModule;
import com.example.raian.com.org.simpleflicker.rest.RestService;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    Retrofit retrofit;

    private static String baseURL = "https://api.flickr.com/";
    private static String API_KEY = "52b082213eb8821fb5a02307573b4c7d";
    private static String method = "flickr.photos.getRecent";
    private static String perPage = "100";
    private static String format = "json";
    private static String callBack = "nojsoncallback";

    //RecyclerView
    private RVFlickerAdapter adapter;

    @BindView(R.id.mFlickerRecyclerView)
    RecyclerView mFlickerRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");

        ButterKnife.bind(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mFlickerRecyclerView.setLayoutManager(layoutManager);

        DaggerMainComponent.builder()
        .restModule(new RestModule(baseURL))
        .build()
        .inject(this);

        RestService restService = retrofit.create(RestService.class);
        Observable<Result> observableResult =  restService.getPhotos(method, API_KEY, perPage, format, callBack);

        observableResult.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError::" + e.getMessage());
                    }

                    @Override
                    public void onNext(Result result) {
                        Log.d(TAG, "onNext::" + result);
                        adapter = new RVFlickerAdapter(MainActivity.this, result.getPhotos().getPhoto());
                        mFlickerRecyclerView.setAdapter(adapter);

                    }
                });


    }
}
