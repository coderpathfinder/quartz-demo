package com.ss.quartz

import com.ss.quartz.service.JobEntityService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class QuartzApplicationTests {

	@Autowired
	lateinit var jobEntityService: JobEntityService

	@Test
	fun contextLoads() {
		val job = jobEntityService.getJobByName("start")
		println(job)
	}

}
