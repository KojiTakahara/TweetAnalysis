package github.com.controller.User;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        if (isGet()) {
            return get();
        } else if (isPost()) {
            return post();
        }
        return null;
    }
    private Navigation get() {
        return forward("Index.jsp");
    }
    private Navigation post() {
        return null;
    }
}
