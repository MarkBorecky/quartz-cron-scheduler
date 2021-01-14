package com.example.quartz;

import com.example.quartz.job.RescreeningJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class QuartzApplication implements CommandLineRunner {

	@Autowired
	private Scheduler scheduler;

	public static void main(String[] args) {
		SpringApplication.run(QuartzApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		JobDetail jobDetail = buildJobDetailz();
		Trigger trigger = buildJobTrigger(jobDetail);
		scheduler.scheduleJob(jobDetail, trigger);
	}

	private Trigger buildJobTrigger(JobDetail jobDetail) {
		return TriggerBuilder.newTrigger()
				.forJob(jobDetail)
				.withIdentity(jobDetail.getKey().getName(), "rescreening-triggers")
				.withDescription("Rescreening Trigger")
				.startAt(new Date())
				.withSchedule(CronScheduleBuilder.cronSchedule("0 21 7 * * ?"))
				.build();
	}

	private JobDetail buildJobDetailz() {
		return JobBuilder.newJob(RescreeningJob.class)
				.withIdentity(UUID.randomUUID().toString(), "rescreening-job")
				.withDescription("Check the cases older than 3 month")
				.storeDurably()
				.build();
	}
}
