
package kevinlamcs.android.com.mapsdemo.ui.data.pojo.nearby;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import kevinlamcs.android.com.mapsdemo.BuildConfig;

public class Result implements Serializable {

    @SerializedName("geometry")
    @Expose
    private Geometry geometry;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("opening_hours")
    @Expose
    private OpeningHours openingHours;
    @SerializedName("photos")
    @Expose
    private List<Photo> photos = null;
    @SerializedName("place_id")
    @Expose
    private String placeId;
    @SerializedName("scope")
    @Expose
    private String scope;
    @SerializedName("alt_ids")
    @Expose
    private List<AltId> altIds = null;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("types")
    @Expose
    private List<String> types = null;
    @SerializedName("vicinity")
    @Expose
    private String vicinity;

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<AltId> getAltIds() {
        return altIds;
    }

    public void setAltIds(List<AltId> altIds) {
        this.altIds = altIds;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public boolean hasPhoto() {
        if (getPhotos() != null && getPhotos().size() > 0 && !getPhotos().get(0).getPhotoReference().equals("")) {
            return true;
        }
        return false;
    }

    public String getPhotoUrl() {
        String photoReference = getPhotos().get(0).getPhotoReference();
        int height = getPhotos().get(0).getHeight();
        int width = getPhotos().get(0).getWidth();
        return BuildConfig.GOOGLE_PLACES_URL + "photo?key=" + BuildConfig.GOOGLE_PLACES_API_KEY + "&photoreference=" + photoReference + "&maxwidth=" + width + "&maxheight=" + height;
    }

}
