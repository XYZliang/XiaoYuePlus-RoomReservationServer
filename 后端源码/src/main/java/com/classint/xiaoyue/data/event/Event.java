package com.classint.xiaoyue.data.event;

import com.alibaba.fastjson.JSONObject;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 用户实体类
 *
 * @author xiaoyue
 */
@Table(name = "event")
@Entity
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  // 自增长主键
  private Long event_id;

  private int event_y;
  private int event_d;
  private int event_m;
  private int event_s;
  private int event_e;
  private int user;

  private String room;
  private String event_title;
  private String event_des;
  private String event_type;
  private String name;

  private Date date;
  private Date startDate;

  private String tel;

  public Event(
      int event_y,
      int event_d,
      int event_m,
      int event_s,
      int event_e,
      int user,
      String room,
      String event_title,
      String event_des,
      String event_type,
      String name,
      String tel) {
    String time =
            String.valueOf(event_y)
                    + '-'
                    + String.format("%02d", event_m)
                    + '-'
                    + String.format("%02d", event_d)
                    + " 00:00:00";
    DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    DateFormat format3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();
    try {
      date = format3.parse(time);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    Date start = new Date(date.getTime() + 1000 * 60 * 8 + 1000 * 10 * event_s);
    this.startDate = start;
    this.event_y = event_y;
    this.event_d = event_d;
    this.event_m = event_m;
    this.event_s = event_s;
    this.event_e = event_e;
    this.user = user;
    this.room = room;
    this.event_title = event_title;
    this.event_des = event_des;
    this.event_type = event_type;
    this.date = new Date();
    this.name=name;
    this.tel=tel;
    System.out.println(this);
  }

  public Event() {}

  public static String getWeek(Date date) {
    String[] weeks = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
    if (week_index < 0) {
      week_index = 0;
    }
    return weeks[week_index];
  }

  public Long getEvent_id() {
    return event_id;
  }

  public void setEvent_id(Long event_id) {
    this.event_id = event_id;
  }

  public int getEvent_y() {
    return event_y;
  }

  public void setEvent_y(int event_y) {
    this.event_y = event_y;
  }

  public int getEvent_d() {
    return event_d;
  }

  public void setEvent_d(int event_d) {
    this.event_d = event_d;
  }

  public int getEvent_m() {
    return event_m;
  }

  public void setEvent_m(int event_m) {
    this.event_m = event_m;
  }

  public int getEvent_s() {
    return event_s;
  }

  public void setEvent_s(int event_s) {
    this.event_s = event_s;
  }

  public int getEvent_e() {
    return event_e;
  }

  public void setEvent_e(int event_e) {
    this.event_e = event_e;
  }

  public int getUser() {
    return user;
  }

  public void setUser(int user_id) {
    this.user = user_id;
  }

  public String getRoom() {
    return room;
  }

  public void setRoom(String room_id) {
    this.room = room_id;
  }

  public String getEvent_title() {
    return event_title;
  }

  public void setEvent_title(String event_title) {
    this.event_title = event_title;
  }

  public String getEvent_des() {
    return event_des;
  }

  public void setEvent_des(String event_des) {
    this.event_des = event_des;
  }

  public String getEvent_type() {
    return event_type;
  }

  public void setEvent_type(String event_type) {
    this.event_type = event_type;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return "Event{"
        + "event_id="
        + event_id
        + ", event_y="
        + event_y
        + ", event_d="
        + event_d
        + ", event_m="
        + event_m
        + ", event_s="
        + event_s
        + ", event_e="
        + event_e
        + ", user="
        + user
        + ", room='"
        + room
        + '\''
        + ", event_title='"
        + event_title
        + '\''
        + ", event_des='"
        + event_des
        + '\''
        + ", event_type='"
        + event_type
        + '\''
        + ", date="
        + date
        + '}';
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public JSONObject toEventJson() {
    String time =
        String.valueOf(event_y)
            + '-'
            + String.format("%02d", event_m)
            + '-'
            + String.format("%02d", event_d)
            + " 00:00";
    DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    DateFormat format3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();
    try {
      date = format1.parse(time);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    Date start = new Date(date.getTime() + 1000*60 * 60 * 8 + 1000*60 * 10 * event_s);
    Date end = new Date(date.getTime() + 1000*60 * 60 * 8 + 1000*60 * 10 * event_e);
    SimpleDateFormat hm = new SimpleDateFormat("hh:mm");
    SimpleDateFormat full = new SimpleDateFormat("yyyy/MM/dd");
    JSONObject object = new JSONObject(true);
    object.put("y", event_y);
    object.put("m", event_m);
    object.put("d", event_d);
    object.put("s", event_s);
    object.put("e", event_e);
    object.put("title", event_title);
    object.put("room", room);
    object.put("des", event_des);
    object.put("type", event_type);
    object.put("start", hm.format(start));
    object.put("end", hm.format(end));
    object.put("fullTime",format3.format(this.date));
    object.put("id", event_id);
    object.put("name", name);
    object.put("phone", tel);
    return object;
  }
}
