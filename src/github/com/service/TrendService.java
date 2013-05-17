package github.com.service;

import java.util.Calendar;
import java.util.List;

import org.slim3.datastore.Datastore;

import github.com.meta.DailyTrendMeta;
import github.com.meta.HourlyTrendMeta;
import github.com.model.DailyTrend;
import github.com.model.HourlyTrend;

public class TrendService {

    private DailyTrendMeta dailyTrendMeta = DailyTrendMeta.get();
    private HourlyTrendMeta hourlyTrendMeta = HourlyTrendMeta.get();

    public void registHourlyTrend(String word, int count, Calendar cal) {
        HourlyTrend trend = new HourlyTrend();
        trend.setCount(count);
        trend.setWord(word);
        trend.setHour(cal.get(Calendar.HOUR_OF_DAY));
        trend.setCreatedAt(cal.getTime());
        Datastore.put(trend);
    }
    public void registDailyTrend(String word, int count, Calendar cal) {
        DailyTrend trend = new DailyTrend();
        trend.setCount(count);
        trend.setWord(word);
        trend.setCreatedAt(cal.getTime());
        Datastore.put(trend);
    }
    public List<HourlyTrend> findHourlyTrendByHourlyWord(String word, Calendar calendar) {
        List<HourlyTrend> list = Datastore.query(hourlyTrendMeta)
                .filter(
                    hourlyTrendMeta.word.equal(word),
                    hourlyTrendMeta.createdAt.greaterThanOrEqual(calendar.getTime()))
                .sort(hourlyTrendMeta.createdAt.asc)
                .sort(hourlyTrendMeta.hour.asc)
                .asList();
        return list;
    }
    public List<HourlyTrend> findHourlyTrendByCreateAt(Calendar calendar) {
        return Datastore.query(hourlyTrendMeta)
                .filter(
                    hourlyTrendMeta.createdAt.greaterThanOrEqual(calendar.getTime()))
                .asList();
    }
    public List<DailyTrend> findDailyTrendByDate(Calendar calendar, int limit) {
        return Datastore.query(dailyTrendMeta)
                .filter(
                    dailyTrendMeta.createdAt.greaterThanOrEqual(calendar.getTime()))
                .sort(dailyTrendMeta.createdAt.asc)
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
                    dailyTrendMeta.word.equal(word),
                    dailyTrendMeta.createdAt.greaterThanOrEqual(calendar.getTime()))
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
