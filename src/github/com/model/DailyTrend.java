package github.com.model;

import github.com.meta.DailyTrendMeta;

import java.io.Serializable;
import java.util.List;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Datastore;
import org.slim3.datastore.Model;

@Model(schemaVersion = 1)
public class DailyTrend implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;
    private String word;
    private Integer count;
    private String ymd;
    private List<Integer> hoursCount;

    @Deprecated
    public DailyTrend() {
        super();
    }
    
    public DailyTrend(Key key) {
        super();
        this.key = key;
    }

    public static Key createKey(String word, String ymd) {
        return Datastore.createKey(DailyTrendMeta.get(), word + "@" + ymd);
    }

    public Key getKey() { return key; }
    public void setKey(Key key) { this.key = key; }
    public String getWord() { return word; }
    public void setWord(String word) { this.word = word; }
    public Integer getCount() { return count; }
    public void setCount(Integer count) { this.count = count; }
    public String getYmd() { return ymd; }
    public void setYmd(String ymd) { this.ymd = ymd; }
    public List<Integer> getHoursCount() { return hoursCount; }
    public void setHoursCount(List<Integer> hoursCount) { this.hoursCount = hoursCount; }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        DailyTrend other = (DailyTrend) obj;
        if (key == null) {
            if (other.key != null) {
                return false;
            }
        } else if (!key.equals(other.key)) {
            return false;
        }
        return true;
    }
}
