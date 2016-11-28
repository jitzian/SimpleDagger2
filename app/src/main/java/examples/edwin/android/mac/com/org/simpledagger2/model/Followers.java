
package examples.edwin.android.mac.com.org.simpledagger2.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Followers {

    @SerializedName("href")
    @Expose
    private Object href;
    @SerializedName("total")
    @Expose
    private int total;

    /**
     * 
     * @return
     *     The href
     */
    public Object getHref() {
        return href;
    }

    /**
     * 
     * @param href
     *     The href
     */
    public void setHref(Object href) {
        this.href = href;
    }

    /**
     * 
     * @return
     *     The total
     */
    public int getTotal() {
        return total;
    }

    /**
     * 
     * @param total
     *     The total
     */
    public void setTotal(int total) {
        this.total = total;
    }

}
