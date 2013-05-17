package github.com.controller.User;

import github.com.service.TwitterService;
import github.com.service.UserService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;

import twitter4j.UserList;

public class RegistController extends Controller {

    private UserService userService = new UserService();
    private TwitterService twitterService = new TwitterService();

    @Override
    public Navigation run() throws Exception {
        if (getMap("action") != null && getMap("action").equals("list")) {
            @SuppressWarnings("unused")
            UserList userList = twitterService.getList(getMap("userName"), getMap("listName"));
        } else {
            String userName = getMap("userName");
            if (userService.findByName(userName) == null)
                userService.regist(userName);
        }
        return redirect("/User");
    }
    private String getMap(String key) {
        RequestMap requestMap = new RequestMap(request);
        Object obj = requestMap.get(key);
        if (obj == null)
            return null;
        return obj.toString();
    }
}
