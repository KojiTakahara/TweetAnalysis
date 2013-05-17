package github.com.controller.trend;

import github.com.model.HourlyTrend;
import github.com.service.TrendService;
import github.com.util.DateUtil;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class DailySummaryController extends Controller {

    private static final Logger log = Logger.getLogger(DailySummaryController.class.getName());

    @Override
    protected Navigation run() throws Exception {
        Calendar cal = DateUtil.getCalendar();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        TrendService trendService = new TrendService();
        List<HourlyTrend> hourlyTrends = trendService.findHourlyTrendByCreateAt(cal);
        Set<String> words = new HashSet<String>();
        for (HourlyTrend hourlyTrend : hourlyTrends)
            words.add(hourlyTrend.getWord());
        for (String word : words) {
            List<HourlyTrend> list = trendService.findHourlyTrendByHourlyWord(word, cal);
            Integer count = 0;
            for (HourlyTrend trend : list)
                count+=trend.getCount();
            if (1 < count) {
                log.info("register daily word : " + word + " ("+ count + ")");
                trendService.registDailyTrend(word, count, cal);
            }
        }
        return null;
    }
}
