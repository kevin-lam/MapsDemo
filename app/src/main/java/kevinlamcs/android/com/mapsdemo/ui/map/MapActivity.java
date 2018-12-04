package kevinlamcs.android.com.mapsdemo.ui.map;

import kevinlamcs.android.com.mapsdemo.R;
import kevinlamcs.android.com.mapsdemo.ui.base.BaseActivity;

public class MapActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_map;
    }

    @Override
    protected void attachFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_container, MapFragment.getInstance(), MapFragment.TAG)
                .commit();
    }

    /*private void addRestaurantMarkers(List<Result> restaurants) {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadMap();
    }

    private void loadMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        viewModel.getNearbyRestaurants(Constants.sfLatLng, 1000);
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

        *//*map.addMarker(new MarkerOptions().position(sanFrancisco).title("Marker in San Francisco"));*//*
        map.moveCamera(CameraUpdateFactory.newLatLng(Constants.sfLatLng));
        map.moveCamera(CameraUpdateFactory.zoomTo(15.0f));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        DetailFragment detailFragment = DetailFragment.getInstance(new RestaurantMarker(marker).getRestaurant());
        displayFragment(detailFragment);
        return true;
    }*/
}
