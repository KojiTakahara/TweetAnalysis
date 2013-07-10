package github.com.service;

import github.com.model.DailyTrend;
import github.com.util.Constant;
import github.com.util.DateUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {

    public void send() throws UnsupportedEncodingException, MessagingException {
        Long id = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append("<script type=\"text/javascript\">// \r\n");
        sb.append("  google.load(\"visualization\", \"1\", {packages:[\"corechart\"]});");
        sb.append("  google.setOnLoadCallback(drawChart);");
        sb.append("  function drawChart() {");
        sb.append("    var data = google.visualization.arrayToDataTable([");
        sb.append(getData());
        sb.append("    ]);");
        sb.append("    var options = {");
        sb.append("      theme: {");
        sb.append("        chartArea: {width: '100%', height: '80%'},");
        sb.append("        legend: {position: 'in'},");
        sb.append("        hAxis: {textPosition: 'out'}, vAxis: {textPosition: 'out'}");
        sb.append("      }");
        sb.append("    };");
        sb.append("    var chart = new google.visualization.LineChart(document.getElementById('"+id+"'));");
        sb.append("    chart.draw(data, options);");
        sb.append("  }");
        sb.append("</script>");
        sb.append("<div id=\""+id+"\" style=\"width: 100%; height: 300px;\"></div>");
        send(sb.toString(), Constant.ADDRESS_FROM, Constant.ADDRESS_TO);
    }
    private void send(String text, String fromStr, String toStr) throws AddressException, MessagingException {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        try {
            InternetAddress from = new InternetAddress(fromStr);
            InternetAddress to = new InternetAddress(toStr);
            Message msg = new MimeMessage(session);
            msg.setFrom(from);
            msg.addRecipient(Message.RecipientType.TO, to);
            Date yesterday = DateUtil.addDay(DateUtil.getDate(), -1);
            ((MimeMessage) msg).setSubject(DateUtil.toString(yesterday, "MM月dd日") + "のトレンド", "UTF-8");
            msg.setText(text);
            Transport.send(msg);
        } catch (AddressException e) {
            System.out.println(e);
            throw e;
        } catch (MessagingException e) {
            System.out.println(e);
            throw e;
        }
    }
    private String getData() {
        TrendService trendService = new TrendService();
        Calendar calendar = DateUtil.getCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        List<Object> base = new ArrayList<Object>();
        base.add("'hour'");
        List<List<Object>> lists = new ArrayList<List<Object>>();
        for (int i = 0; i < 24; i++) {
            List<Object> objects = new ArrayList<Object>();
            objects.add("'" + i + "時'");
            lists.add(objects);
        }
        // dailyからその日のトップ5を取得する
        List<DailyTrend> dailyTrends = trendService.findDailyTrendByDate(calendar, 5);
        for (DailyTrend dailyTrend : dailyTrends) {
            base.add("'" + dailyTrend.getWord() + "'");
            for (int i = 0; i < dailyTrend.getHoursCount().size(); i++) {
                lists.get(i).add(dailyTrend.getHoursCount().get(i));
            }
        }
        StringBuilder sb = new StringBuilder(base.toString());
        for (List<Object> list : lists)
            sb.append(", ").append(list);
        return sb.toString();
    }
}
