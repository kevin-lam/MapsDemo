package kevinlamcs.android.com.mapsdemo.ui.map;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import io.reactivex.subjects.PublishSubject;
import kevinlamcs.android.com.mapsdemo.ui.base.BaseViewModel;
import kevinlamcs.android.com.mapsdemo.ui.custom.RestaurantLocator;
import kevinlamcs.android.com.mapsdemo.ui.data.pojo.nearby.Result;

public class MapViewModel extends BaseViewModel {

    private final RestaurantLocator restaurantLocator;

    public MapViewModel(RestaurantLocator restaurantLocator) {
        this.restaurantLocator = restaurantLocator;
    }

    public void getNearbyRestaurants(LatLng latLng, double radius) {
        restaurantLocator.getNearbyRestaurants(latLng.latitude + "," + latLng.longitude, Double.toString(radius));
    }

    public PublishSubject<List<Result>> getNearbyRestaurants() {
        return restaurantLocator.nearbyRestaurants;
    }
}
