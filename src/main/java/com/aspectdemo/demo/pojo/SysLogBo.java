package com.aspectdemo.demo.pojo;

import lombok.Data;

/**
 * @author ljt
 * @Title: SysLogBo
 * @ProjectName demo
 * @Description:
 * @Version:
 * @date 2019/11/28 14:32
 */
@Data
public class SysLogBo {

  private String className;

  private String methodName;

  private String params;

  private Long exeutime;

  private String remark;

  private String createDate;
}
