package github.com.model;

import java.io.Serializable;
import java.util.Date;

import com.google.appengine.api.datastore.Key;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.CreationDate;
import org.slim3.datastore.Model;

@Model(schemaVersion = 1)
public class DailyTrend implements Serializable {

    private static final long serialVersionUID = 1L;

    @Attribute(primaryKey = true)
    private Key key;
    @Attribute(version = true)
    private Long version;
    private String word;
    private Integer count;
    @Attribute(listener = CreationDate.class)
    private Date createdAt;

    public Key getKey() { return key; }
    public void setKey(Key key) { this.key = key; }
    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }
    public String getWord() { return word; }
    public void setWord(String word) { this.word = word; }
    public Integer getCount() { return count; }
    public void setCount(Integer count) { this.count = count; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

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
