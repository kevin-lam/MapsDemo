package kevinlamcs.android.com.mapsdemo.ui.custom;

import android.util.Log;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import kevinlamcs.android.com.mapsdemo.BuildConfig;
import kevinlamcs.android.com.mapsdemo.ui.data.pojo.nearby.Result;
import kevinlamcs.android.com.mapsdemo.ui.data.remote.NearbyService;
import kevinlamcs.android.com.mapsdemo.ui.map.MapViewModel;

public class RestaurantLocator {

    public PublishSubject<List<Result>> nearbyRestaurants = PublishSubject.create();
    NearbyService service;

    public RestaurantLocator(NearbyService service) {
        this.service = service;
    }

    public void getNearbyRestaurants(String location, String radius) {
        service.findNearbyRestaurants(BuildConfig.GOOGLE_PLACES_API_KEY, location, radius, "restaurants")
                .map(nearbyResponse -> nearbyResponse.getResults())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(restaurants -> {
                    Log.d(MapViewModel.class.getSimpleName(), restaurants.toString());
                    nearbyRestaurants.onNext(restaurants);
                }, error -> {
                    Log.d("Restaurants load error", error.toString());
                });
    }
}
