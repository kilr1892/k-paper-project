package cn.edu.zju.kpaperproject.controller;

import cn.edu.zju.kpaperproject.dto.EngineFactoryManufacturingTask;
import cn.edu.zju.kpaperproject.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * .
 *
 * @author RichardLee
 * @version v1.0
 */
@RestController
public class TestController {

    @Autowired
    TaskService taskService;


    @GetMapping("/test")
    public String test() {
        taskService.startTask(1);

        System.out.println();

        return "success";
    }

    @GetMapping("/test2")
    public String test2() {
        return "success";
    }
    @GetMapping("/test3")
    public String test3() {
        return "success";
    }
}
