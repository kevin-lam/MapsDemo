package kevinlamcs.android.com.mapsdemo.ui.data.remote;

import io.reactivex.Single;
import kevinlamcs.android.com.mapsdemo.BuildConfig;
import kevinlamcs.android.com.mapsdemo.ui.data.pojo.nearby.NearbyResponse;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NearbyService {
    @GET("nearbysearch/json")
    Single<NearbyResponse> findNearbyRestaurants(@Query("key") String apiKey, @Query("location") String location, @Query("radius") String radius, @Query("keyword") String keyword);

    class Builder {

        private Retrofit retrofit;

        private OkHttpClient okHttpClient() {
            return new OkHttpClient.Builder().build();
        }

        public NearbyService build() {
            OkHttpClient client = okHttpClient();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.GOOGLE_PLACES_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit.create(NearbyService.class);
        }
    }
}
