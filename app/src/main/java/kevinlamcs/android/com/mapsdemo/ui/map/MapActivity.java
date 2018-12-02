package kevinlamcs.android.com.mapsdemo.ui.map;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import kevinlamcs.android.com.mapsdemo.R;
import kevinlamcs.android.com.mapsdemo.ui.base.BaseActivity;

public class MapActivity extends BaseActivity<MapViewModel> implements OnMapReadyCallback {

    private GoogleMap map;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_map;
    }

    @Override
    protected MapViewModel getViewModel() {
        return null;
    }

    @Override
    protected void subscribeToViewModelChanges() {

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
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        setupMap(googleMap);
    }

    private void setupMap(GoogleMap googleMap) {
        map = googleMap;

        UiSettings mapSettings = map.getUiSettings();
        mapSettings.setZoomControlsEnabled(true);

        LatLng sanFrancisco = new LatLng(37.7749, -122.4194);
        map.addMarker(new MarkerOptions().position(sanFrancisco).title("Marker in San Francisco"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sanFrancisco));
        map.moveCamera(CameraUpdateFactory.zoomTo(15.0f));
    }
}
