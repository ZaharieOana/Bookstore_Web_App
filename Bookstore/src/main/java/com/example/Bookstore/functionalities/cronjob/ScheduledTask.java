package com.example.Bookstore.functionalities.cronjob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
    @Autowired
    private CronJobService cronJobService;

//    @Scheduled(cron = "0 */5 * * * ?")
@Scheduled(cron = "0 8 1 * * ?") //trimite un mail in prima zi a lunii la ora 8
    public void execute(){
        cronJobService.doTask();
    }
}
