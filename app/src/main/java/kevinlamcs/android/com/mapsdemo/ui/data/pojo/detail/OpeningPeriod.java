package kevinlamcs.android.com.mapsdemo.ui.data.pojo.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpeningPeriod {

    @SerializedName("open")
    @Expose
    private Open open;

    @SerializedName("close")
    @Expose
    private Close close;

    public Open getOpen() {
        return open;
    }

    public void setOpen(Open open) {
        this.open = open;
    }

    public Close getClose() {
        return close;
    }

    public void setClose(Close close) {
        this.close = close;
    }
}
