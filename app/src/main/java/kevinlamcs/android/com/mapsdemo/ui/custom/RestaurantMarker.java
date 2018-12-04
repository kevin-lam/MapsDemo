package kevinlamcs.android.com.mapsdemo.ui.custom;

import com.google.android.gms.maps.model.Marker;

import java.util.HashMap;
import java.util.Map;

import kevinlamcs.android.com.mapsdemo.ui.data.pojo.nearby.Result;

public class RestaurantMarker {

    private static Map<Marker, Result> markerToResultMap = new HashMap<>();

    private Marker marker;

    public RestaurantMarker(Marker marker) {
        this.marker = marker;
    }

    public Result getRestaurant() {
        return markerToResultMap.get(marker);
    }

    public static void add(Marker marker, Result restaurant) {
        markerToResultMap.put(marker, restaurant);
    }
}
