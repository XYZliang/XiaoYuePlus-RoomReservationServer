package com.classint.xiaoyue.Async;

import com.classint.xiaoyue.data.event.Event;
import com.classint.xiaoyue.data.event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncEventRepo {
  @Autowired private EventRepository eventRepository;

  @Async
  public void saveEventAsync(Event eve) {
    save(eve);
  }

  public void save(Event eve) {
    eventRepository.save(eve);
  }

  @Async
  public void deleteAsync(long id) {
    delete(id);
  }

  public void delete(long id) {
    eventRepository.deleteEventById(id);
  }
}
