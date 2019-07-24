package findcoo.batch.sample.job

import findcoo.batch.sample.SampleApplicationTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.batch.core.BatchStatus
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParameter
import org.springframework.batch.core.JobParameters
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
internal class BenchmarkTest : SampleApplicationTest() {

    @Autowired
    lateinit var messageSaveOneJob: Job

    @Autowired
    lateinit var messageSaveAllJob: Job

    @Autowired
    lateinit var notificationSaveOneJob: Job

    @Autowired
    lateinit var notificationSaveAllJob: Job

    @Test
    fun benchmarkSaveOneWithIdentityGeneratorJobTest() {
        val execution = jobLauncher.run(messageSaveOneJob, JobParameters(mapOf("readCount" to JobParameter(1000000))))
        Assertions.assertEquals(BatchStatus.COMPLETED, execution.status)
    }

    @Test
    fun benchmarkSaveAllWithIdentityGeneratorJobTest() {
        val execution = jobLauncher.run(messageSaveAllJob, JobParameters(mapOf("readCount" to JobParameter(1000000))))
        Assertions.assertEquals(BatchStatus.COMPLETED, execution.status)
    }

    @Test
    fun benchmarkSaveOneWithTableGeneratorJobTest() {
        val execution = jobLauncher.run(notificationSaveOneJob, JobParameters(mapOf("readCount" to JobParameter(100000))))
        Assertions.assertEquals(BatchStatus.COMPLETED, execution.status)
    }

    @Test
    fun benchmarkSaveAllWithTableGeneratorJobTest() {
        val execution = jobLauncher.run(notificationSaveAllJob, JobParameters(mapOf("readCount" to JobParameter(100000))))
        Assertions.assertEquals(BatchStatus.COMPLETED, execution.status)
    }

}