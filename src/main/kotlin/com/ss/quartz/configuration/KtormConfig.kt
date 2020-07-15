package com.ss.quartz.configuration

import com.fasterxml.jackson.databind.Module
import me.liuwj.ktorm.database.Database
import me.liuwj.ktorm.jackson.KtormModule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

/**
 * @ClassName: KtormConfig
 * @Description: TODO
 * @Author: 徐首达
 * @Date: 2020/7/15 21:52
 * @Version: 1.0
 **/
@Configuration
class KtormConfig {

    @Autowired
    lateinit var dataSource: DataSource

    @Bean
    fun datasource(): Database {
        return Database.connectWithSpringSupport(dataSource)
    }

    @Bean
    fun ktormModule(): Module {
        return KtormModule()
    }

}