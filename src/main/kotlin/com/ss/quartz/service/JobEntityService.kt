package com.ss.quartz.service

import com.ss.quartz.model.Job
import com.ss.quartz.model.jobs
import me.liuwj.ktorm.database.Database
import me.liuwj.ktorm.dsl.eq
import me.liuwj.ktorm.entity.find
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @ClassName: JobEntityService
 * @Description: TODO
 * @Author: 徐首达
 * @Date: 2020/7/15 22:16
 * @Version: 1.0
 **/
@Service
class JobEntityService {

    @Autowired
    private lateinit var database: Database

    fun getJobByName(name: String): Job? {
        return database.jobs.find { it.name eq name }
    }

    fun updateJobByName(name: String, cron: String) {
        val job = database.jobs.find { it.name eq name }
        job?.cron = cron
        job?.flushChanges()
    }

}