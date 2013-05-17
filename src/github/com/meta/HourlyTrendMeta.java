package github.com.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2013-05-17 23:49:27")
/** */
public final class HourlyTrendMeta extends org.slim3.datastore.ModelMeta<github.com.model.HourlyTrend> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<github.com.model.HourlyTrend, java.lang.Integer> count = new org.slim3.datastore.CoreAttributeMeta<github.com.model.HourlyTrend, java.lang.Integer>(this, "count", "count", java.lang.Integer.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<github.com.model.HourlyTrend, java.util.Date> createdAt = new org.slim3.datastore.CoreAttributeMeta<github.com.model.HourlyTrend, java.util.Date>(this, "createdAt", "createdAt", java.util.Date.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<github.com.model.HourlyTrend, java.lang.Integer> hour = new org.slim3.datastore.CoreAttributeMeta<github.com.model.HourlyTrend, java.lang.Integer>(this, "hour", "hour", java.lang.Integer.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<github.com.model.HourlyTrend, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<github.com.model.HourlyTrend, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<github.com.model.HourlyTrend, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<github.com.model.HourlyTrend, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<github.com.model.HourlyTrend> word = new org.slim3.datastore.StringAttributeMeta<github.com.model.HourlyTrend>(this, "word", "word");

    private static final org.slim3.datastore.CreationDate slim3_createdAtAttributeListener = new org.slim3.datastore.CreationDate();

    private static final HourlyTrendMeta slim3_singleton = new HourlyTrendMeta();

    /**
     * @return the singleton
     */
    public static HourlyTrendMeta get() {
       return slim3_singleton;
    }

    /** */
    public HourlyTrendMeta() {
        super("HourlyTrend", github.com.model.HourlyTrend.class);
    }

    @Override
    public github.com.model.HourlyTrend entityToModel(com.google.appengine.api.datastore.Entity entity) {
        github.com.model.HourlyTrend model = new github.com.model.HourlyTrend();
        model.setCount(longToInteger((java.lang.Long) entity.getProperty("count")));
        model.setCreatedAt((java.util.Date) entity.getProperty("createdAt"));
        model.setHour(longToInteger((java.lang.Long) entity.getProperty("hour")));
        model.setKey(entity.getKey());
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        model.setWord((java.lang.String) entity.getProperty("word"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        github.com.model.HourlyTrend m = (github.com.model.HourlyTrend) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("count", m.getCount());
        entity.setProperty("createdAt", m.getCreatedAt());
        entity.setProperty("hour", m.getHour());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("word", m.getWord());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        github.com.model.HourlyTrend m = (github.com.model.HourlyTrend) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        github.com.model.HourlyTrend m = (github.com.model.HourlyTrend) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        github.com.model.HourlyTrend m = (github.com.model.HourlyTrend) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        github.com.model.HourlyTrend m = (github.com.model.HourlyTrend) model;
        long version = m.getVersion() != null ? m.getVersion().longValue() : 0L;
        m.setVersion(Long.valueOf(version + 1L));
    }

    @Override
    protected void prePut(Object model) {
        github.com.model.HourlyTrend m = (github.com.model.HourlyTrend) model;
        m.setCreatedAt(slim3_createdAtAttributeListener.prePut(m.getCreatedAt()));
    }

    @Override
    protected void postGet(Object model) {
    }

    @Override
    public String getSchemaVersionName() {
        return "slim3.schemaVersion";
    }

    @Override
    public String getClassHierarchyListName() {
        return "slim3.classHierarchyList";
    }

    @Override
    protected boolean isCipherProperty(String propertyName) {
        return false;
    }

    @Override
    protected void modelToJson(org.slim3.datastore.json.JsonWriter writer, java.lang.Object model, int maxDepth, int currentDepth) {
        github.com.model.HourlyTrend m = (github.com.model.HourlyTrend) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getCount() != null){
            writer.setNextPropertyName("count");
            encoder0.encode(writer, m.getCount());
        }
        if(m.getCreatedAt() != null){
            writer.setNextPropertyName("createdAt");
            encoder0.encode(writer, m.getCreatedAt());
        }
        if(m.getHour() != null){
            writer.setNextPropertyName("hour");
            encoder0.encode(writer, m.getHour());
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        if(m.getWord() != null){
            writer.setNextPropertyName("word");
            encoder0.encode(writer, m.getWord());
        }
        writer.endObject();
    }

    @Override
    protected github.com.model.HourlyTrend jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        github.com.model.HourlyTrend m = new github.com.model.HourlyTrend();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("count");
        m.setCount(decoder0.decode(reader, m.getCount()));
        reader = rootReader.newObjectReader("createdAt");
        m.setCreatedAt(decoder0.decode(reader, m.getCreatedAt()));
        reader = rootReader.newObjectReader("hour");
        m.setHour(decoder0.decode(reader, m.getHour()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        reader = rootReader.newObjectReader("word");
        m.setWord(decoder0.decode(reader, m.getWord()));
        return m;
    }
}