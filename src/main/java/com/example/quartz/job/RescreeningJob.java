package com.example.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.ZonedDateTime;

public class RescreeningJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("executing job with key " + jobExecutionContext.getMergedJobDataMap());
        rescreening();
    }

    private void rescreening() {
        System.out.println("RESCREENING!!! " + ZonedDateTime.now());
    }
}
