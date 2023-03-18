package org.mklinkj.qna.spring_guide.scheduling_tasks;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScheduledTasks {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

  @Scheduled(fixedRate = 5000)
  public void reportCurrentTime() {
    LOGGER.info("The time is now {}", LocalTime.now().format(FORMATTER));
  }
}
