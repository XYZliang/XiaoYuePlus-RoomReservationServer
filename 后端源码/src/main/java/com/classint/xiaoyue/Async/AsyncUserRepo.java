package com.classint.xiaoyue.Async;

import com.classint.xiaoyue.data.user.User;
import com.classint.xiaoyue.data.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AsyncUserRepo {
  @Autowired private UserRepository userRepository;

  @Async
  public void registerAsync(
      String userName, String Name, String Password, String id, Date CreatedDate, String Salt) {
    userRepository.register(userName, Name, Password, id, CreatedDate, Salt);
  }

  @Async
  public void saveAsync(User user) {
    save(user);
  }

  public void save(User user) {
    System.out.println("异步储存");
    userRepository.save(user);
  }
}
