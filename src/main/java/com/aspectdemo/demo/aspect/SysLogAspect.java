package com.aspectdemo.demo.aspect;/**
 * @author ljt
 * @Title: SysLogAspect
 * @ProjectName demo
 * @Description:
 * @Version:
 * @date 2019/11/28 15:55
 */

import com.aspectdemo.demo.pojo.SysLogBo;
import com.aspectdemo.demo.service.SysLogService;
import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @description
 * @date 2019/11/28
 *
 */
@Component
@Aspect
public class SysLogAspect {
    @Autowired
    private SysLogService sysLogService;

  /**
   * 这里我们使用注解的形式
   * 当然，我们也可以通过切点表达式直接指定需要拦截的package,需要拦截的class 以及 method
   * 切点表达式:   execution(...)
   */
  @Pointcut("@annotation(com.aspectdemo.demo.aspect.SysLog)")
  public void logPointCut(){
    System.out.println("执行切点。。。");
  }

  /**
   * 环绕通知 @Around  ， 当然也可以使用 @Before (前置通知)  @After (后置通知)
   * @param joinPoint
   * @return
   * @throws Throwable
   */
  @Around("logPointCut()")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
    System.out.println("执行环绕通知。。。");
    long beginTime = System.currentTimeMillis();
    Object result = joinPoint.proceed();   // proceed方法就是用于启动目标方法执行的.
    long time = System.currentTimeMillis() - beginTime;
    try{
      saveLog(joinPoint,time);
    }catch (Exception e){
      
    }
    return result;
  }

  //保存日志
  private void saveLog(ProceedingJoinPoint joinPoint, long time) {

    MethodSignature signature = (MethodSignature) joinPoint.getSignature();  //获取被增强的方法相关信息
    System.out.println(signature.toString());
    SysLogBo sysLogBo = new SysLogBo();
    sysLogBo.setExeutime(time);
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    sysLogBo.setCreateDate(format.format(new Date()));

    Method method = signature.getMethod();
    SysLog sysLog = method.getAnnotation(SysLog.class);
    if(sysLog != null){
      //sysLog.value()是注解上的描述
      sysLogBo.setRemark(sysLog.value());
    }
    //请求的 类名  方法名
    String className = joinPoint.getTarget().getClass().getName();
    String methodName = signature.getName();
    sysLogBo.setClassName(className);
    sysLogBo.setMethodName(methodName);
    //请求的参数
    Object[] args = joinPoint.getArgs();     //getArgs()获取方法的参数
    System.out.println("args:"+args);
    try {
      List<String> list = new ArrayList<>();
      for (Object o : args){
        list.add(new Gson().toJson(o));
        System.out.println(o);
      }
      System.out.println(list.toString());
      sysLogBo.setParams(list.toString());
    }catch (Exception e){
      sysLogService.save(sysLogBo);
    }
    System.out.println("日志保存==========================");
    System.out.println(sysLogBo.toString());
  }

  @Before("logPointCut()")
  public void doBefore(JoinPoint joinPoint){
    System.out.println("执行Before。。。");
  }

  @After("logPointCut()")
  public void doAfter(JoinPoint joinPoint){
    System.out.println("执行After。。。");
  }

  @AfterReturning(returning = "result",pointcut = "logPointCut()")
  public  void doAfterReturn(Object result){
    System.out.println("方法返回值："+result);
  }
}
