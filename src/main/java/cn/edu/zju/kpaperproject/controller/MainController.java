package cn.edu.zju.kpaperproject.controller;

import cn.edu.zju.kpaperproject.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
@Controller
public class MainController {

    @Autowired
    private InitService initService;

    @RequestMapping("/start/{experimentsNumber}")
    public void startService(@PathVariable("experimentsNumber") int experimentsNumber) {
        // TODO 可能要改变下获取参数的形式? 还是用配置文件? 需要再议
        initService.init(experimentsNumber);
    }
}
