package kevinlamcs.android.com.mapsdemo.ui.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;
import kevinlamcs.android.com.mapsdemo.R;
import kevinlamcs.android.com.mapsdemo.ui.base.BaseFragment;
import kevinlamcs.android.com.mapsdemo.ui.custom.RestaurantDetailDirectory;
import kevinlamcs.android.com.mapsdemo.ui.data.pojo.nearby.Result;
import kevinlamcs.android.com.mapsdemo.ui.data.remote.DetailService;

public class DetailFragment extends BaseFragment<DetailViewModel> {

    @BindView(R.id.detail_title)
    TextView title;

    @BindView(R.id.detail_hours)
    TextView hours;

    @BindView(R.id.detail_address)
    TextView address;

    @BindView(R.id.detail_photo)
    ImageView photo;

    private static final String ARG_RESTAURANT = "Restaurant";
    private DetailViewModel viewModel;

    public DetailFragment() {
    }

    public static DetailFragment getInstance(Result restaurant) {
        DetailFragment fragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_RESTAURANT, restaurant);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_detail;
    }

    @Override
    public DetailViewModel getViewModel() {
        DetailService detailService = new DetailService.Builder().build();
        RestaurantDetailDirectory restaurantDetailDirectory = new RestaurantDetailDirectory(detailService);
        viewModel = new DetailViewModel(restaurantDetailDirectory);
        return viewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupDetails();
    }

    private void setupDetails() {
        Result restaurant = (Result) this.getArguments().getSerializable(ARG_RESTAURANT);
        if (restaurant.hasPhoto()) Glide.with(this).asBitmap().load(restaurant.getPhotoUrl()).into(photo);
        viewModel.getRestaurantDetails(restaurant.getPlaceId());
    }

    @Override
    public void subscribeToViewModelChanges() {
        viewModel.getRestaurantDetails().subscribe(detail -> {
            title.setText(detail.getName());
            address.setText(detail.getFormattedAddress());
            hours.setText(detail.getOpeningHours().toString());
        });
    }

    @OnClick(R.id.detail_button_close)
    public void onClick() {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
