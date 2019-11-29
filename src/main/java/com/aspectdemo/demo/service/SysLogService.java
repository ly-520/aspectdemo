package com.aspectdemo.demo.service;/**
 * @author ljt
 * @Title: SysLogService
 * @ProjectName demo
 * @Description:
 * @Version:
 * @date 2019/11/28 14:36
 */

import com.aspectdemo.demo.pojo.SysLogBo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SysLogService {

  public boolean save(SysLogBo sysLogBo){
    log.info("SysLogService:{}",sysLogBo.getParams());
    return true;
  }
}
