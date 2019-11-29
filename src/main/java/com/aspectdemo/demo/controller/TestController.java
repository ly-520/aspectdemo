package com.aspectdemo.demo.controller;/**
 * @author ljt
 * @Title: TestController
 * @ProjectName demo
 * @Description:
 * @Version:
 * @date 2019/11/29 18:15
 */

import com.aspectdemo.demo.aspect.SysLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @description
 * @date 2019/11/29
 *
 */

@RestController
public class TestController {

  @SysLog("参数为名字和班级")
  @GetMapping("/test")
  public String test(@RequestParam("name") String name,@RequestParam("class") String className){
    System.out.println("controller 测试参数为名字和班级");
    return name+":"+className;
  }

  @SysLog("测试年龄")
  @GetMapping("/testage")
  public String test(@RequestParam("age") String age){
    System.out.println("controller 测试测试年龄");
    return age;
  }

}
