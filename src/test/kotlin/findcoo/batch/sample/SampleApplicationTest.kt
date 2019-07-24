package findcoo.batch.sample

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.test.JobLauncherTestUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension


@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@EnableBatchProcessing
internal class SampleApplicationTest {
    var jobLauncherTestUtils: JobLauncherTestUtils = JobLauncherTestUtils()

    @Autowired
    lateinit var jobLauncher: JobLauncher

    @Autowired
    lateinit var jobRepository: JobRepository

    @BeforeEach
    fun beforeAll() {
        jobLauncherTestUtils.jobLauncher = jobLauncher
        jobLauncherTestUtils.jobRepository = jobRepository
    }

}