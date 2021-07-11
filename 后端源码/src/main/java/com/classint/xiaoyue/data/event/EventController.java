package com.classint.xiaoyue.data.event;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.classint.xiaoyue.Async.AsyncEventRepo;
import com.classint.xiaoyue.Async.AsyncLogs;
import com.classint.xiaoyue.data.user.User;
import com.classint.xiaoyue.data.user.UserRepository;
import com.classint.xiaoyue.response.ResResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.classint.xiaoyue.ajax.ajax.ajax;

@RestController
public class EventController {
  private final Logger logger = LoggerFactory.getLogger(EventController.class);
  @Autowired private EventRepository eventRepository;
  @Autowired private AsyncEventRepo asyncEventRepo;
  @Autowired private AsyncLogs asyncLogs;
  @Autowired private UserRepository userRepository;

  @RequestMapping("nowEvent")
  public ResResult setEvent(@RequestParam String JsonEvent, HttpServletResponse response) {
    ajax(response);
    JSONObject JSONEVENT = JSON.parseObject(JsonEvent);
    String userName = JSONEVENT.getString("userName");
    User user = userRepository.findByUserName(userName);
    Event event =
        new Event(
            JSONEVENT.getInteger("y"),
            JSONEVENT.getInteger("d"),
            JSONEVENT.getInteger("m"),
            JSONEVENT.getInteger("s"),
            JSONEVENT.getInteger("e"),
            user.getId().intValue(),
            JSONEVENT.getString("roomid"),
            JSONEVENT.getString("title"),
            JSONEVENT.getString("des"),
            JSONEVENT.getString("type"),
                user.getName(),
                user.getPhone());
    System.out.println(event);
    asyncEventRepo.saveEventAsync(event);
    return ResResult.suc(event.toEventJson());
  }

  @RequestMapping("getEvent")
  public ResResult getEvent(@RequestParam String userName, HttpServletResponse response) {
    ajax(response);
    User user = userRepository.findByUserName(userName);
    List<Event> event = eventRepository.findByUserOrderByDate(user.getId().intValue());
    System.out.println(event);
    JSONObject jsonObject = new JSONObject(true);
    for (Event value : event) {
      jsonObject.put(value.getEvent_title(), value.toEventJson());
    }
    return ResResult.suc(jsonObject.toJSONString());
  }

  @RequestMapping("getEventByDay")
  public ResResult getEventByDay(
      @RequestParam String userName,
      @RequestParam String y,
      @RequestParam String m,
      @RequestParam String d,
      HttpServletResponse response) {
    ajax(response);
    User user = userRepository.findByUserName(userName);
    List<Event> event = eventRepository.findByUserOrderByDate(user.getId().intValue());
    System.out.println(event);
    JSONObject jsonObject = new JSONObject(true);
    for (Event value : event) {
      if (value.getEvent_y() == Integer.parseInt(y)
          && value.getEvent_m() == Integer.parseInt(m)
          && value.getEvent_d() == Integer.parseInt(d))
        jsonObject.put(value.getEvent_title(), value.toEventJson());
    }
    return ResResult.suc(jsonObject.toJSONString());
  }

  @RequestMapping("deleteEve")
  public ResResult getEventByDay(@RequestParam Long id, HttpServletResponse response) {
    ajax(response);
    asyncEventRepo.deleteAsync(id);
    return ResResult.suc();
  }

  @RequestMapping("getAllEve")
  public ResResult getEventAll(HttpServletResponse response) {
    ajax(response);
    List<Event> event =  eventRepository.findAllByOrderByDate();
    JSONObject jsonObject = new JSONObject(true);
    System.out.println(event.size());
    System.out.println(event);
    for (Event value : event) {
      //if (value.getEvent_type().contains("审核"))
      System.out.println(value);
        jsonObject.put(String.valueOf(value.getEvent_id()), value.toEventJson());
    }
    return ResResult.suc(jsonObject.toJSONString());
  }

  @RequestMapping("EveNotOk")
  public ResResult EventNotOk(@RequestParam Long id,@RequestParam String rea,HttpServletResponse response) {
    ajax(response);
    eventRepository.findEventNotById(id,"审核失败："+rea);
    return ResResult.suc();
  }

  @RequestMapping("EveOk")
  public ResResult EventOk(@RequestParam Long id,HttpServletResponse response) {
    ajax(response);
    eventRepository.findEventOkById(id,"审核通过");
    return ResResult.suc();
  }
}
