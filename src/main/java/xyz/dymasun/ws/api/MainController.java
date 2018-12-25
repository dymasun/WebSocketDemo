package xyz.dymasun.ws.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.dymasun.ws.rule.RuleMap;
import xyz.dymasun.ws.rule.RuleResult;
import xyz.dymasun.ws.utils.CodeUtils;

import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @RequestMapping(value = "/test",
            produces = "application/text;charset=utf-8",
            method = RequestMethod.GET
    )
    @ResponseBody
    public String test(){
        return "success";
    }
    @RequestMapping(value = "/getToken",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.GET
    )
    @ResponseBody
    public String getToken(HttpServletRequest request){
        HttpSession session = request.getSession();
        String token = CodeUtils.getRandomString2(20);
        session.setAttribute("token",token);
        return RuleResult.success(token).toJSON();
    }
}
