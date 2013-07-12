package github.com.controller.cron;

import github.com.model.DailyTrend;
import github.com.util.DateUtil;
import github.com.util.StringUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskHandle;
import com.google.appengine.api.taskqueue.TaskOptions;

public class SummaryController extends Controller {

    private static final String QUEUE_NAME = "summary-queue";
    private static final int TASK_COUNT = 1000;
    private static final int LEASE_SENCOND = 60 * 60 * 24;
    private static final String COUNT = "count";
    private static final String WORD = "word";
    private static final String YMD = "ymd";
    private static final String HOUR = "hour";
    private static final Logger log = Logger.getLogger(SummaryController.class.getName());

    public static void addPullQueue(String word, Integer count, String ymd, String hour) {
        TaskOptions option = TaskOptions.Builder.withMethod(TaskOptions.Method.PULL);
        option.param(WORD, word);
        option.param(COUNT, count.toString());
        option.param(YMD, ymd);
        option.param(HOUR, hour);
        Queue queue = QueueFactory.getQueue(QUEUE_NAME);
        queue.add(option);
    }

    @Override
    public Navigation run() throws Exception {
        Map<String, Map<String, Object>> resultMap = new HashMap<String, Map<String,Object>>();
        setMap(resultMap);
        List<DailyTrend> dailyTrends = new ArrayList<DailyTrend>();
        for (Entry<String, Map<String, Object>> entry : resultMap.entrySet()) {
            int count = Integer.valueOf(entry.getValue().get(COUNT).toString());
            if (1 < count) {
                String word = entry.getKey();
                String ymd = entry.getValue().get(YMD).toString();
                List<Integer> hoursCount = new ArrayList<Integer>();
                for (int i = 0; i < 24; i++) {
                    String key = String.format("%1$02d", i);
                    Object obj = entry.getValue().get(key) == null ? "0" : entry.getValue().get(key);
                    hoursCount.add(Integer.valueOf(obj.toString()));
                }
                DailyTrend dailyTrend = new DailyTrend(DailyTrend.createKey(word, ymd));
                dailyTrend.setCount(count);
                dailyTrend.setHoursCount(hoursCount);
                dailyTrend.setWord(word);
                dailyTrend.setYmd(ymd);
                dailyTrends.add(dailyTrend);
            }
        }
        Datastore.putAsync(dailyTrends);
        return null;
    }

    private void setMap(Map<String, Map<String, Object>> resultMap) throws UnsupportedEncodingException {
        Queue queue = QueueFactory.getQueue(QUEUE_NAME);
        List<TaskHandle> tasks = queue.leaseTasks(LEASE_SENCOND, TimeUnit.SECONDS, TASK_COUNT);
        String todayStr = DateUtil.toString(DateUtil.getDate(), "yyyyMMdd");
        int minSize = 0;
        while (minSize < tasks.size()) {
            log.info("task size : " + tasks.size() + "  " + "skip task size : " + minSize);
            for (TaskHandle task : tasks) {
                Map<String, String> paramMap = getParameterMap(task.getPayload());
                if (paramMap.get(YMD).equals(todayStr)) {
                    minSize++;
                    continue;
                }
                String word = StringUtil.decode(paramMap.get(WORD), StringUtil.UTF8);
                Map<String, Object> wordMap = resultMap.get(word);
                if (wordMap == null) {
                    wordMap = new HashMap<String, Object>();
                    wordMap.put(YMD, paramMap.get(YMD));
                    wordMap.put(COUNT, 0);
                    for (int i = 0; i < 24; i++) {
                        wordMap.put(String.format("%1$02d", i), 0);
                    }
                }
                Integer count = Integer.valueOf(wordMap.get(COUNT).toString());
                Integer addCount = Integer.valueOf(paramMap.get(COUNT));
                wordMap.put(COUNT, count + addCount);
                String hourStr = paramMap.get(HOUR);
                String hourCountStr = wordMap.get(hourStr).toString();
                wordMap.put(hourStr, Integer.valueOf(hourCountStr) + addCount);
                log.info(word + " : " + hourStr + "(" + wordMap.get(hourStr) + ")");
                resultMap.put(word, wordMap);
                queue.deleteTask(task);
            }
            tasks = queue.leaseTasks(LEASE_SENCOND, TimeUnit.SECONDS, TASK_COUNT);
        }
    }

    private Map<String, String> getParameterMap(byte[] b) {
        String[] params = {};
        try {
            params = new String(b, StringUtil.UTF8).split("&");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String, String> map = new HashMap<String, String>();
        for (String str : params) {
            String[] param = str.split("=");
            map.put(param[0], param[1]);
        }
        return map;
    }
}
