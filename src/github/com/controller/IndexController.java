package github.com.controller;

import github.com.service.MailService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class IndexController extends Controller {

    @Override
    public Navigation run() throws Exception {
        if (asBoolean("send")) {
            MailService mailService = new MailService();
            mailService.send();
        }
        return forward("Index.jsp");
    }
}
