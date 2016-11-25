package xinQing.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Login
 *
 * Created by xuan on 16-11-23.
 */
@Controller
public class LoginController {

    /**
     * 首页
     *
     * @return /index.jsp
     */
    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String home() {
        return "../../index";
    }

    /**
     * 管理员页面
     *
     * @return  /WEB-INF/views/admin.jsp
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin() {
        return "admin";
    }

    /**
     * 登录页面
     *
     * @param logout    注销
     * @param modelMap  ModelMap
     * @return  /login.jsp
     */
    @RequestMapping(value = "/login*", method = RequestMethod.GET)
    public String login(@RequestParam(required = false, value = "logout") String logout,
                        ModelMap modelMap) {
        if (logout != null) {
            modelMap.addAttribute("logout", "你已经成功的退出系统!");
        }
        return "../../login";
    }

//    由于已经禁用Spring Security的csrf，因此/logout支持GET请求，不用自己写对GET请求的支持
//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public String logout(HttpSession session) {
//        session.invalidate();
//        return "redirect:/login?logout";
//    }

}
