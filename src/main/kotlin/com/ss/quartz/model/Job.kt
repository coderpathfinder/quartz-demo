package com.ss.quartz.model

import me.liuwj.ktorm.database.Database
import me.liuwj.ktorm.entity.Entity
import me.liuwj.ktorm.entity.sequenceOf
import me.liuwj.ktorm.schema.Table
import me.liuwj.ktorm.schema.long
import me.liuwj.ktorm.schema.varchar

interface Job: Entity<Job> {
    companion object: Entity.Factory<Job>()
    var id: Long
    var cron: String
    var name: String
}

object Jobs: Table<Job>("job") {
    val id = long("id").primaryKey().bindTo { it.id }
    val cron = varchar("cron").bindTo { it.cron }
    val name = varchar("name").bindTo { it.name }
}

val Database.jobs get() = this.sequenceOf(Jobs)