package com.classint.xiaoyue.data.user;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户实体类
 *
 * @author xiaoyue
 */
@Table(name = "user")
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  // 自增长主键
  private Long id;

  // 注册的昵称
  private String userName;
  // 姓名
  private String name;
  // 学号
  private String stuid;
  // 身份
  private String type;
  // 用于加密的盐
  private String salt;
  // 加密后的密码
  private String password;
  // 手机号
  private String phone;
  // 邮箱
  private String email;
  // 用户注册时间
  @CreatedDate
  @Column(updatable = false, nullable = false)
  private Date createdDate;
  // 用户最后一次修改时间
  @LastModifiedDate
  @Column(nullable = false)
  private Date updatedDate;
  // 用户微信绑定
  private String wechat;
  // 用户QQ绑定
  private String qq;
  // 用户苹果绑定
  private String apple;
  // 用户苹果绑定
  private String serverKey;
  // 用户登录设备
  private String ua;
  // 用户上次登录时间
  private Date loginDate;
  // 是否认证
  private boolean authentication;
  // 是否高权限
  private boolean highPermissions;
  // 是否高权限
  private int credit;

  // get/set/toString 方法
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStuid() {
    return stuid;
  }

  public void setStuid(String stuid) {
    this.stuid = stuid;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getWechat() {
    return wechat;
  }

  public void setWechat(String wechat) {
    this.wechat = wechat;
  }

  public String getQq() {
    return qq;
  }

  public void setQq(String qq) {
    this.qq = qq;
  }

  public String getApple() {
    return apple;
  }

  public void setApple(String apple) {
    this.apple = apple;
  }

  public String getUa() {
    return ua;
  }

  public void setUa(String ua) {
    this.ua = ua;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  public Date getLoginDate() {
    return loginDate;
  }

  public void setLoginDate(Date loginDate) {
    this.loginDate = loginDate;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getServerKey() {
    return serverKey;
  }

  public void setServerKey(String serverKey) {
    this.serverKey = serverKey;
  }

  public boolean isAuthentication() {
    return authentication;
  }

  public void setAuthentication(boolean authentication) {
    this.authentication = authentication;
  }

  public boolean isHighPermissions() {
    return highPermissions;
  }

  public void setHighPermissions(boolean highPermissions) {
    this.highPermissions = highPermissions;
  }

  public int getCredit() {
    return credit;
  }

  public void setCredit(int credit) {
    this.credit = credit;
  }

  @Override
  public String toString() {
    return "User{"
        + "id="
        + id
        + ", userName='"
        + userName
        + '\''
        + ", name='"
        + name
        + '\''
        + ", stuid='"
        + stuid
        + '\''
        + ", type='"
        + type
        + '\''
        + ", salt='"
        + salt
        + '\''
        + ", password='"
        + password
        + '\''
        + ", phone='"
        + phone
        + '\''
        + ", email='"
        + email
        + '\''
        + ", createdDate="
        + createdDate
        + ", updatedDate="
        + updatedDate
        + ", wechat='"
        + wechat
        + '\''
        + ", qq='"
        + qq
        + '\''
        + ", apple='"
        + apple
        + '\''
        + ", serverKey='"
        + serverKey
        + '\''
        + ", ua='"
        + ua
        + '\''
        + ", loginDate="
        + loginDate
        + ", authentication="
        + authentication
        + ", highPermissions="
        + highPermissions
        + ", credit="
        + credit
        + '}';
  }

  public JSONObject toUserJson() {
    JSONObject object = new JSONObject(true);
    object.put("userName", userName);
    object.put("name", name);
    object.put("stuid", stuid);
    object.put("type", type);
    object.put("highPermissions", (highPermissions == true) ? true : false);
    object.put("wechat", wechat != null);
    object.put("qq", qq != null);
    object.put("apple", apple != null);
    object.put("serverKey", serverKey != null);
    object.put("xinyong", credit);
    object.put("phone", phone);
    object.put("certification", authentication);
    return object;
  }
}
