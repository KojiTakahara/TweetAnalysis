package github.com.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2013-07-07 21:46:14")
/** */
public final class DailyTrendMeta extends org.slim3.datastore.ModelMeta<github.com.model.DailyTrend> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<github.com.model.DailyTrend, java.lang.Integer> count = new org.slim3.datastore.CoreAttributeMeta<github.com.model.DailyTrend, java.lang.Integer>(this, "count", "count", java.lang.Integer.class);

    /** */
    public final org.slim3.datastore.CollectionAttributeMeta<github.com.model.DailyTrend, java.util.List<java.lang.Integer>, java.lang.Integer> hoursCount = new org.slim3.datastore.CollectionAttributeMeta<github.com.model.DailyTrend, java.util.List<java.lang.Integer>, java.lang.Integer>(this, "hoursCount", "hoursCount", java.util.List.class);

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<github.com.model.DailyTrend, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<github.com.model.DailyTrend, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<github.com.model.DailyTrend> word = new org.slim3.datastore.StringAttributeMeta<github.com.model.DailyTrend>(this, "word", "word");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<github.com.model.DailyTrend> ymd = new org.slim3.datastore.StringAttributeMeta<github.com.model.DailyTrend>(this, "ymd", "ymd");

    private static final DailyTrendMeta slim3_singleton = new DailyTrendMeta();

    /**
     * @return the singleton
     */
    public static DailyTrendMeta get() {
       return slim3_singleton;
    }

    /** */
    public DailyTrendMeta() {
        super("DailyTrend", github.com.model.DailyTrend.class);
    }

    @SuppressWarnings("deprecation")
    @Override
    public github.com.model.DailyTrend entityToModel(com.google.appengine.api.datastore.Entity entity) {
        github.com.model.DailyTrend model = new github.com.model.DailyTrend();
        model.setCount(longToInteger((java.lang.Long) entity.getProperty("count")));
        model.setHoursCount(longListToIntegerList(entity.getProperty("hoursCount")));
        model.setKey(entity.getKey());
        model.setWord((java.lang.String) entity.getProperty("word"));
        model.setYmd((java.lang.String) entity.getProperty("ymd"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        github.com.model.DailyTrend m = (github.com.model.DailyTrend) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("count", m.getCount());
        entity.setProperty("hoursCount", m.getHoursCount());
        entity.setProperty("word", m.getWord());
        entity.setProperty("ymd", m.getYmd());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        github.com.model.DailyTrend m = (github.com.model.DailyTrend) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        github.com.model.DailyTrend m = (github.com.model.DailyTrend) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        throw new IllegalStateException("The version property of the model(github.com.model.DailyTrend) is not defined.");
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
    }

    @Override
    protected void prePut(Object model) {
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
        github.com.model.DailyTrend m = (github.com.model.DailyTrend) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getCount() != null){
            writer.setNextPropertyName("count");
            encoder0.encode(writer, m.getCount());
        }
        if(m.getHoursCount() != null){
            writer.setNextPropertyName("hoursCount");
            writer.beginArray();
            for(java.lang.Integer v : m.getHoursCount()){
                encoder0.encode(writer, v);
            }
            writer.endArray();
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getWord() != null){
            writer.setNextPropertyName("word");
            encoder0.encode(writer, m.getWord());
        }
        if(m.getYmd() != null){
            writer.setNextPropertyName("ymd");
            encoder0.encode(writer, m.getYmd());
        }
        writer.endObject();
    }

    @SuppressWarnings("deprecation")
    @Override
    protected github.com.model.DailyTrend jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        github.com.model.DailyTrend m = new github.com.model.DailyTrend();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("count");
        m.setCount(decoder0.decode(reader, m.getCount()));
        reader = rootReader.newObjectReader("hoursCount");
        {
            java.util.ArrayList<java.lang.Integer> elements = new java.util.ArrayList<java.lang.Integer>();
            org.slim3.datastore.json.JsonArrayReader r = rootReader.newArrayReader("hoursCount");
            if(r != null){
                reader = r;
                int n = r.length();
                for(int i = 0; i < n; i++){
                    r.setIndex(i);
                    java.lang.Integer v = decoder0.decode(reader, (java.lang.Integer)null)                    ;
                    if(v != null){
                        elements.add(v);
                    }
                }
                m.setHoursCount(elements);
            }
        }
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("word");
        m.setWord(decoder0.decode(reader, m.getWord()));
        reader = rootReader.newObjectReader("ymd");
        m.setYmd(decoder0.decode(reader, m.getYmd()));
        return m;
    }
}