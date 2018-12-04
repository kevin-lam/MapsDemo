package kevinlamcs.android.com.mapsdemo.ui.custom;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import kevinlamcs.android.com.mapsdemo.BuildConfig;
import kevinlamcs.android.com.mapsdemo.ui.data.pojo.detail.Result;
import kevinlamcs.android.com.mapsdemo.ui.data.remote.DetailService;

public class RestaurantDetailDirectory {

    public PublishSubject<Result> restaurantDetail  = PublishSubject.create();
    DetailService service;

    public RestaurantDetailDirectory(DetailService service) {
        this.service = service;
    }

    public void getNearbyRestaurants(String placeId) {
        service.findRestaurantDetails(placeId, "formatted_address,geometry,name,opening_hours", "en", BuildConfig.GOOGLE_PLACES_API_KEY)
                .map(detailResponse -> detailResponse.getResult())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(detail -> {
                    Log.d(RestaurantDetailDirectory.class.getSimpleName(), detail.toString());
                    restaurantDetail.onNext(detail);
                }, error -> {
                    Log.d("Restaurants load error", error.toString());
                });
    }
}
