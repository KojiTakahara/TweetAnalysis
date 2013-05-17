package github.com.controller.trend;

import java.util.List;

import github.com.model.DailyTrend;
import github.com.service.TrendService;
import github.com.util.DateUtil;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class ListController extends Controller {

    private TrendService trendService = new TrendService();

    @Override
    public Navigation run() throws Exception {
        //this.response.setContentType("application/json; charset=UTF-8");
        List<DailyTrend> trendList = trendService.findDailyTrendByDate(DateUtil.getCalendar(), 5);
        requestScope("trendList", trendList);
        return forward("list.jsp");
    }
}
