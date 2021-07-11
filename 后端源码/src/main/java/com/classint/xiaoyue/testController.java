package com.classint.xiaoyue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class testController {
  private final Logger logger = LoggerFactory.getLogger(testController.class);

  @RequestMapping("hello")
  @CrossOrigin("http://localhost:63342")
  public String hello(HttpServletResponse response) {
    response.addHeader("Access-Control-Allow-Credentials", "true");
    //        LogToSql();
    return "hello API";
  }

  public void LogToSql() {
    logger.info("数据库日志info");
    logger.error("数据库日志error");
  }
}
