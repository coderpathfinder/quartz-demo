package com.ss.quartz.job

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.stereotype.Component

/**
 * @ClassName: StartJob
 * @Description: TODO
 * @Author: 徐首达
 * @Date: 2020/7/15 22:30
 * @Version: 1.0
 **/
@Configuration
@Component
@EnableScheduling
class StartJob {
    fun hello() {
        println("hello.....")
    }
}