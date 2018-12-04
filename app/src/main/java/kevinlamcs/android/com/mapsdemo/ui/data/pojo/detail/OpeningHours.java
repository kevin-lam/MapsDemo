package kevinlamcs.android.com.mapsdemo.ui.data.pojo.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OpeningHours {
    @SerializedName("open_now")
    @Expose
    private boolean openNow;

    @SerializedName("periods")
    @Expose
    private List<OpeningPeriod> openPeriods;

    @SerializedName("weekday_text")
    @Expose
    private List<String> weekdayText;

    public boolean isOpenNow() {
        return openNow;
    }

    public void setOpenNow(boolean openNow) {
        this.openNow = openNow;
    }

    public List<OpeningPeriod> getOpenPeriods() {
        return openPeriods;
    }

    public void setOpenPeriods(List<OpeningPeriod> openPeriods) {
        this.openPeriods = openPeriods;
    }

    public List<String> getWeekdayText() {
        return weekdayText;
    }

    public void setWeekdayText(List<String> weekdayText) {
        this.weekdayText = weekdayText;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String dayOpeningHours : getWeekdayText()) {
            sb.append(dayOpeningHours);
            sb.append("\n");
        }
        return sb.toString();
    }
}
