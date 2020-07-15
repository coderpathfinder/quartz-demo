package com.ss.quartz.configuration

import com.ss.quartz.job.StartJob
import org.quartz.Trigger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.quartz.CronTriggerFactoryBean
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean
import org.springframework.scheduling.quartz.SchedulerFactoryBean

/**
 * @ClassName: QuartzConfig
 * @Description: TODO
 * @Author: 徐首达
 * @Date: 2020/7/15 22:34
 * @Version: 1.0
 **/
@Configuration
class QuartzConfig {

    //配置定时任务
    @Bean(name = ["jobDetail"])
    fun detailFactoryBean(startJob: StartJob)
            : MethodInvokingJobDetailFactoryBean {
        val jobDetail = MethodInvokingJobDetailFactoryBean()
        //是否并发
        jobDetail.setConcurrent(true)

        //设置job名称
        jobDetail.setName("start")
        //设置job分组
        jobDetail.setGroup("group1")

        jobDetail.targetObject = startJob

        jobDetail.targetMethod = "hello"

        return jobDetail

    }

    //配置触发器
    @Bean(name = ["jobTrigger"])
    fun jobTrigger(jobDetail: MethodInvokingJobDetailFactoryBean)
            : CronTriggerFactoryBean {
        val jobTrigger = CronTriggerFactoryBean()
        jobDetail.`object`?.let { jobTrigger.setJobDetail(it) }
        jobTrigger.setCronExpression("0 0/2 * * * ?")
        jobTrigger.setName("start")
        return jobTrigger
    }

    //quartz调度工厂
    @Bean(name = ["scheduler"])
    fun SchedulerFactoryBean(trigger: Trigger): SchedulerFactoryBean {
        val scheduler = SchedulerFactoryBean()
        scheduler.setOverwriteExistingJobs(true)
        scheduler.setStartupDelay(5)//应用启动后延迟五秒执行
        scheduler.setTriggers(trigger)
        return scheduler
    }

}