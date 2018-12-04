package kevinlamcs.android.com.mapsdemo.ui.detail;

import io.reactivex.subjects.PublishSubject;
import kevinlamcs.android.com.mapsdemo.ui.base.BaseViewModel;
import kevinlamcs.android.com.mapsdemo.ui.custom.RestaurantDetailDirectory;
import kevinlamcs.android.com.mapsdemo.ui.data.pojo.detail.Result;

public class DetailViewModel extends BaseViewModel {

    private final RestaurantDetailDirectory restaurantDetailDirectory;

    public DetailViewModel(RestaurantDetailDirectory restaurantDetailDirectory) {
        this.restaurantDetailDirectory = restaurantDetailDirectory;
    }

    public void getRestaurantDetails(String placeId) {
        restaurantDetailDirectory.getNearbyRestaurants(placeId);
    }

    public PublishSubject<Result> getRestaurantDetails() {
        return restaurantDetailDirectory.restaurantDetail;
    }
}
