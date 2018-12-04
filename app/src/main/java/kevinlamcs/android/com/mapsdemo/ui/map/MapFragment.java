package kevinlamcs.android.com.mapsdemo.ui.map;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import kevinlamcs.android.com.mapsdemo.R;
import kevinlamcs.android.com.mapsdemo.ui.base.BaseActivity;
import kevinlamcs.android.com.mapsdemo.ui.base.BaseFragment;
import kevinlamcs.android.com.mapsdemo.ui.common.Constants;
import kevinlamcs.android.com.mapsdemo.ui.custom.RestaurantLocator;
import kevinlamcs.android.com.mapsdemo.ui.custom.RestaurantMarker;
import kevinlamcs.android.com.mapsdemo.ui.data.pojo.nearby.Result;
import kevinlamcs.android.com.mapsdemo.ui.data.remote.NearbyService;
import kevinlamcs.android.com.mapsdemo.ui.detail.DetailFragment;

public class MapFragment extends BaseFragment<MapViewModel> implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    public static final String TAG = MapFragment.class.getSimpleName();

    private GoogleMap map;
    private MapViewModel viewModel;

    public static MapFragment getInstance() {
        return new MapFragment();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        DetailFragment detailFragment = DetailFragment.getInstance(new RestaurantMarker(marker).getRestaurant());
        ((BaseActivity)getActivity()).displayFragment(detailFragment);
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        setupMap(googleMap);
    }

    private void setupMap(GoogleMap googleMap) {
        map = googleMap;

        map.setOnMarkerClickListener(this);
        UiSettings mapSettings = map.getUiSettings();
        mapSettings.setZoomControlsEnabled(true);

        /*map.addMarker(new MarkerOptions().position(sanFrancisco).title("Marker in San Francisco"));*/
        map.moveCamera(CameraUpdateFactory.newLatLng(Constants.sfLatLng));
        map.moveCamera(CameraUpdateFactory.zoomTo(15.0f));
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_map;
    }

    @Override
    public MapViewModel getViewModel() {
        NearbyService nearbyService = new NearbyService.Builder().build();
        RestaurantLocator locator = new RestaurantLocator(nearbyService);
        viewModel = new MapViewModel(locator);
        return viewModel;
    }

    @Override
    public void subscribeToViewModelChanges() {
        viewModel.getNearbyRestaurants().subscribe(this::addRestaurantMarkers);
    }


    private void addRestaurantMarkers(List<Result> restaurants) {
        for (Result restaurant : restaurants) {
            double lat = restaurant.getGeometry().getLocation().getLat();
            double lng = restaurant.getGeometry().getLocation().getLng();
            Marker marker = addMarker(lat, lng);
            RestaurantMarker.add(marker, restaurant);
        }
    }

    private Marker addMarker(Double lat, Double lng) {
        LatLng latLng = new LatLng(lat, lng);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        return map.addMarker(markerOptions);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadMap();
    }

    private void loadMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        viewModel.getNearbyRestaurants(Constants.sfLatLng, 1000);
    }
}
