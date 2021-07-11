package com.classint.xiaoyue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
// @SpringBootApplication是 SpringBoot 的核心注解，一般用在入口类上。它是一个组合注解，其中主要内容有一下三个
// @SpringBootConfiguration：是一个类级注释，指示对象是一个bean定义的源，可以理解为xml中的beans，一般和 @Bean 注解一起使用。
// @EnableAutoConfiguration：启用 Spring 应用程序上下文的自动配置，试图猜测和配置您可能需要的bean。自动配置类通常采用基于你的 classpath 和已经定义的
// beans 对象进行应用。
// @ComponentScan：该注解会自动扫描指定包下的全部标有 @Component、@Service、@Repository、@Controller注解 的类，并注册成bean
//public class XiaoyueApplication {
//  public static void main(String[] args) {
//    SpringApplication.run(XiaoyueApplication.class, args);
//  }
//}
public class XiaoyueApplication extends SpringBootServletInitializer {
  public static void main(String[] args) {
    SpringApplication.run(XiaoyueApplication.class, args);
  }

  // 用于构建war文件并进行部署
  @Override
  protected SpringApplicationBuilder configure(
          SpringApplicationBuilder builder) {
    return builder.sources(this.getClass());
  }
}