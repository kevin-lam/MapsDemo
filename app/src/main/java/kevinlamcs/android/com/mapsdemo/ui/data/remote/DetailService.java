package kevinlamcs.android.com.mapsdemo.ui.data.remote;

import io.reactivex.Single;
import kevinlamcs.android.com.mapsdemo.BuildConfig;
import kevinlamcs.android.com.mapsdemo.ui.data.pojo.detail.DetailResponse;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DetailService {
    @GET("details/json")
    Single<DetailResponse> findRestaurantDetails(@Query("placeid") String placeId, @Query("fields") String fields, @Query("language") String language, @Query("key") String apiKey);

    class Builder {

        private Retrofit retrofit;

        private OkHttpClient okHttpClient() {
            return new OkHttpClient.Builder().build();
        }

        public DetailService build() {
            OkHttpClient client = okHttpClient();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.GOOGLE_PLACES_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            return retrofit.create(DetailService.class);
        }
    }
}
