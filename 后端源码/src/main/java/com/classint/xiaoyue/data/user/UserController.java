package com.classint.xiaoyue.data.user;

import com.alibaba.fastjson.JSONObject;
import com.classint.xiaoyue.Async.AsyncLogs;
import com.classint.xiaoyue.Async.AsyncUserRepo;
import com.classint.xiaoyue.encryption.encryption;
import com.classint.xiaoyue.response.ResResult;
import com.classint.xiaoyue.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

import static com.classint.xiaoyue.ajax.ajax.ajax;
import static com.classint.xiaoyue.encryption.encryption.makeSalt;

@RestController
public class UserController {
  private final Logger logger = LoggerFactory.getLogger(UserController.class);
  @Autowired private UserRepository userRepository;
  @Autowired private AsyncUserRepo asyncUserRepo;
  @Autowired private AsyncLogs asyncLogs;

  @RequestMapping("all")
  public List<User> getAllUsers() {
    List<User> listUsers = (List<User>) userRepository.findAll();
    return listUsers;
  }

  @RequestMapping("register")
  public ResResult register(
      @RequestParam String Name,
      @RequestParam String password,
      @RequestParam String userName,
      @RequestParam long id,
      HttpServletResponse response) {
    ajax(response);
    User user = new User();
    user.setName(Name);
    user.setUserName(userName);
    user.setStuid(String.valueOf(id));
    user.setCreatedDate(new Date());
    user.setUpdatedDate(user.getCreatedDate());
    user.setSalt(makeSalt(id, user.getCreatedDate()));
    user.setPassword(encryption.getSaltMD5(password, user.getSalt()));
    user.setCredit(100);
    user.setType("学生");
    //    asyncUserRepo.registerAsync(
    //        userName,
    //        Name,
    //        user.getPassword(),
    //        String.valueOf(id),
    //        user.getCreatedDate(),
    //        user.getSalt());
    //    asyncUserRepo.saveAsync(user);
    asyncUserRepo.saveAsync(user);
    JSONObject object = new JSONObject(true);
    object.put("status", "注册成功");
    object.put("username", user.getUserName());
    object.put("name", user.getName());
    System.out.println(user);
    return ResResult.suc(object.toString());
  }

  @RequestMapping("login")
  public ResResult login(
      @RequestParam String password,
      @RequestParam String userName,
      HttpServletResponse response,
      HttpServletRequest request) {
    ajax(response);
    User user;
    asyncLogs.sendInfoAsync("用户登陆" + userName + "/" + password);
    try {
      user = userRepository.findByUserName(userName);
    } catch (IncorrectResultSizeDataAccessException e) {
      System.out.println(userName + "用户重复");
      return ResResult.fail(ResultCode.USER_HAS_EXISTED);
    } catch (NullPointerException e) {
      System.out.println(userName + "用户不存在");
      return ResResult.fail(ResultCode.USER_NOT_EXISTED);
    }
    if (user == null) {
      System.out.println(userName + "用户不存在");
      return ResResult.fail(ResultCode.USER_NOT_EXISTED);
    }
    System.out.println(user);
    if (!encryption.getSaltMD5(password, user.getSalt()).equals(user.getPassword()))
      return ResResult.fail(ResultCode.USER_PWD_ERROR);
    JSONObject object = new JSONObject(true);
    object.put("status", "登陆成功");
    object.put("username", user.getUserName());
    object.put("name", user.getName());
    user.setLoginDate(new Date());
    user.setUa(request.getHeader("User-Agent"));
    asyncUserRepo.saveAsync(user);
    System.out.println("返回数据");
    return ResResult.suc(object.toString());
  }

  @RequestMapping("getInfo")
  public ResResult getUser(
      @RequestParam String userName, HttpServletResponse response, HttpServletRequest request) {
    ajax(response);
    User user;
    asyncLogs.sendInfoAsync("用户尝试自动登陆" + userName);
    try {
      user = userRepository.findByUserName(userName);
    } catch (IncorrectResultSizeDataAccessException e) {
      System.out.println(userName + "用户重复");
      return ResResult.fail(ResultCode.USER_HAS_EXISTED);
    } catch (NullPointerException e) {
      System.out.println(userName + "用户不存在");
      return ResResult.fail(ResultCode.USER_NOT_EXISTED);
    }
    if (user == null) {
      System.out.println(userName + "用户不存在");
      return ResResult.fail(ResultCode.USER_NOT_EXISTED);
    }
    System.out.println(user);
    if (!request.getHeader("User-Agent").equals(user.getUa()))
      return ResResult.fail(ResultCode.USER_UA_CHANGE);
    user.setLoginDate(new Date());
    asyncUserRepo.saveAsync(user);
    System.out.println(user.toUserJson());
    return ResResult.suc(user.toUserJson());
  }

  @RequestMapping("setAuthentication")
  public ResResult setAu(
      @RequestParam String userName,
      @RequestParam String name,
      @RequestParam String stuid,
      HttpServletResponse response,
      HttpServletRequest request) {
    ajax(response);
    User user = userRepository.findByUserName(userName);
    user.setStuid(stuid);
    user.setName(name);
    user.setAuthentication(true);
    asyncUserRepo.saveAsync(user);
    return ResResult.suc(user.toUserJson());
  }

  @RequestMapping("setPhone")
  public ResResult setPhone(
      @RequestParam String userName,
      @RequestParam String phone,
      HttpServletResponse response,
      HttpServletRequest request) {
    ajax(response);
    User user = userRepository.findByUserName(userName);
    user.setPhone(phone);
    asyncUserRepo.saveAsync(user);
    return ResResult.suc(user.toUserJson());
  }
}
