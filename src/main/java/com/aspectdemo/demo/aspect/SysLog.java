package com.aspectdemo.demo.aspect;

import java.lang.annotation.*;

/**
 * @author ljt
 * @Title: SysLog
 * @ProjectName demo
 * @Description:
 * @Version:
 * @date 2019/11/28 14:39
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

  String value() default "";

}
