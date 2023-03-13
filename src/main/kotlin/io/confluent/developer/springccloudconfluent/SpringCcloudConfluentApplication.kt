package io.confluent.developer.springccloudconfluent

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringCcloudConfluentApplication

fun main(args: Array<String>) {
	runApplication<SpringCcloudConfluentApplication>(*args)
}
