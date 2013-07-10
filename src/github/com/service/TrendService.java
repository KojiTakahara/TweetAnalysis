package github.com.service;

import java.util.Calendar;
import java.util.List;

import org.slim3.datastore.Datastore;

import github.com.meta.DailyTrendMeta;
import github.com.model.DailyTrend;
import github.com.util.DateUtil;

public class TrendService {

    private DailyTrendMeta dailyTrendMeta = DailyTrendMeta.get();

    @SuppressWarnings("deprecation")
    public void registDailyTrend(String word, int count, Calendar cal) {
        DailyTrend trend = new DailyTrend();
        trend.setCount(count);
        trend.setWord(word);
        //trend.setCreatedAt(cal.getTime());
        Datastore.put(trend);
    }
    public List<DailyTrend> findDailyTrendByDate(Calendar calendar, int limit) {
        return Datastore.query(dailyTrendMeta)
                .filter(dailyTrendMeta.ymd.equal(DateUtil.toString(calendar.getTime(), "yyyyMMdd")))
                .sort(dailyTrendMeta.count.desc)
                .limit(limit)
                .asList();
    }
    private List<DailyTrend> findDailyTrendByDailyWord(String word, Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return Datastore.query(dailyTrendMeta)
                .filter(
                    dailyTrendMeta.word.equal(word))
                    //dailyTrendMeta.createdAt.greaterThanOrEqual(calendar.getTime()))
                .asList();
    }
    public void updateDailyTrend(String word, Calendar calendar, int count) {
        List<DailyTrend> dailyTrends = findDailyTrendByDailyWord(word, calendar);
        if (dailyTrends == null || dailyTrends.size() == 0)
            registDailyTrend(word, count, calendar);
        else {
            DailyTrend dailyTrend = dailyTrends.get(0);
            dailyTrend.setCount(dailyTrend.getCount() + count);
            Datastore.put(dailyTrend);
        }
    }
}
