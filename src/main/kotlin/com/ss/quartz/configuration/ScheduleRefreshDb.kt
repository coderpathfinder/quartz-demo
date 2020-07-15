package com.ss.quartz.configuration

import com.ss.quartz.service.JobEntityService
import org.quartz.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import javax.annotation.Resource

/**
 * @ClassName: ScheduleRefreshDb
 * @Description: TODO
 * @Author: 徐首达
 * @Date: 2020/7/15 22:56
 * @Version: 1.0
 **/
@Configuration
@EnableScheduling
@Component
class ScheduleRefreshDb {

    @Autowired
    private lateinit var jobEntityService: JobEntityService
    @Resource(name = "jobDetail")
    private lateinit var jobDetail: JobDetail
    @Resource(name = "jobTrigger")
    private lateinit var jobTrigger: CronTrigger
    @Resource(name = "scheduler")
    private lateinit var scheduler: Scheduler

    //定时查库更新任务
    @Scheduled(fixedDelay = 5_000L)
    fun scheduleUpdateCronTrigger() {

        var trigger: CronTrigger = scheduler.getTrigger(jobTrigger.key) as CronTrigger
        val cron = trigger.cronExpression
        val job = jobEntityService.getJobByName("start")
        val dbCron = job?.cron
        if(cron!! != dbCron!!){
            val builder = CronScheduleBuilder.cronSchedule(dbCron)
            trigger = scheduler.getTrigger(jobTrigger.key) as CronTrigger
            trigger = trigger.triggerBuilder.withIdentity(jobTrigger.key)
                    .withSchedule(builder).build()

            scheduler.rescheduleJob(jobTrigger.key, trigger)

        }

    }

}