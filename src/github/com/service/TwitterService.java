package github.com.service;

import github.com.util.Constant;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.UserList;
import twitter4j.auth.AccessToken;

public class TwitterService {

    public List<Status> getUserTimeline(String userId, Date date) throws TwitterException {
        int page = 1;
        List<Status> timeline = getUserTimeline(userId, page);
        Status status = timeline.get(timeline.size() - 1);
        while (status.getCreatedAt().after(date)) {
            page++;
            timeline.addAll(getUserTimeline(userId, page));
            status = timeline.get(timeline.size() - 1);
        }
        return timeline;
    }
    private List<Status> getUserTimeline(String userId, int page) throws TwitterException {
        int pageCount = 20;
        Twitter twitter = getTwitter();
        return twitter.getUserTimeline(userId, new Paging(page, pageCount));
    }
    private Twitter getTwitter() {
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(Constant.CONSUMER_KEY, Constant.CONSUMER_SECRET);
        twitter.setOAuthAccessToken(new AccessToken(Constant.ACCESS_TOKEN, Constant.ACCESS_TOKEN_SECRET));
        return twitter;
    }
    public String excludeTwitterWord(String str) {
        String twitterId = "@([A-Za-z0-9_]+)";
        String retweet = "RT";
        String url = "(http://|https://){1}[\\w\\.\\-/:\\#\\?\\=\\&\\;\\%\\~\\+]+";
        String regex = "(" + twitterId + "|" + retweet + "|" + url + ")";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.replaceAll("");
    }
    public UserList getList(String ownerName, String listName) throws TwitterException {
        Twitter twitter = getTwitter();
        return twitter.showUserList(ownerName, listName);
    }
}
