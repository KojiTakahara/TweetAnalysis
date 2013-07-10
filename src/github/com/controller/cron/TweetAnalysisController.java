package github.com.controller.cron;

import github.com.controller.IndexController;
import github.com.model.User;
import github.com.service.AnalysisService;
import github.com.service.TwitterService;
import github.com.service.UserService;
import github.com.util.DateUtil;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import twitter4j.Status;
import twitter4j.TwitterException;

public class TweetAnalysisController extends Controller {

    private TwitterService twitterService = new TwitterService();
    private AnalysisService analysisService = new AnalysisService();
    private UserService userService = new UserService();
    private static final Logger log = Logger.getLogger(IndexController.class.getName());

    private Map<String, Integer> map;

    @Override
    public Navigation run() throws Exception {
        this.map = new HashMap<String, Integer>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        List<User> users = userService.findAll();
        for (User user : users)
            setMap(user.getName(), calendar);
        Calendar cal = DateUtil.getCalendar();
        for(Map.Entry<String, Integer> e : this.map.entrySet()) {
            if (1 < e.getKey().length()) {
                String ymd = DateUtil.toString(cal.getTime(), "yyyyMMdd");
                String hour = DateUtil.toString(cal.getTime(), "HH");
                SummaryController.addPullQueue(e.getKey(), e.getValue(), ymd, hour);
            }
        }
        return null;
    }

    private void setMap(String userName, Calendar calendar) {
        try {
            List<Status> timeline = twitterService.getUserTimeline(userName, calendar.getTime());
            for (Status status : timeline) {
                if (status.getCreatedAt().before(calendar.getTime()))
                    continue;
                String text = twitterService.excludeTwitterWord(status.getText());
                List<String> keywords = analysisService.getSurfaceForm(text);
                for (String word : keywords) {
                    Integer count = this.map.get(word);
                    if (count == null) {
                        count = 1;
                        this.map.put(word, count);
                    } else {
                        this.map.put(word, count + 1);
                    }
                }
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            log.warning("取得失敗。userName: " + userName);
        }
    }
}
