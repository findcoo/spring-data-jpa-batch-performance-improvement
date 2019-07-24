package findcoo.batch.sample

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableBatchProcessing
@SpringBootApplication
class SampleApplication

fun main(args: Array<String>) {
    runApplication<SampleApplication>(*args)
}