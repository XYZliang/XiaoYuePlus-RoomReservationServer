package com.classint.xiaoyue.Async;

import com.classint.xiaoyue.testController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncLogs {
  private final Logger logger = LoggerFactory.getLogger(testController.class);

  @Async
  public void sendInfoAsync(String a) {
    logger.info(a);
  }

  @Async
  public void sendErrorAsync(String a) {
    logger.error(a);
  }
}
