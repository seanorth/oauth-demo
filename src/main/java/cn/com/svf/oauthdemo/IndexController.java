package cn.com.svf.oauthdemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 首页
 *
 * @author zhanfei
 * @create 2022/4/11
 */

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("recipient", "World");
        return "index.html";
    }
    @RequestMapping(value = "/oauth/redirect", method = RequestMethod.GET)
    public String redirect(Model model, HttpServletRequest httpServletRequest) throws IOException {
        String code = httpServletRequest.getParameter("code");
        model.addAttribute("recipient", "Redirect");
        //后端发送请求到github请求token
        Map<String,String> map=new HashMap<>();
        map.put("client_id","ca0489b80247aa9e20ca");
        map.put("client_secret","950afa7cd05c3a4eb16c7b574eca3a4c2dfad8d0");
        map.put("code",code);
        String tokenUrl="https://github.com/login/oauth/access_token";
        String token = HttpUtils.getInstance().postForm(tokenUrl, map);
        model.addAttribute("recipient", token);
        return "index.html";
    }
}
