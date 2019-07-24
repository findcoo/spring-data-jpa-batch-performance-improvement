package findcoo.batch.sample.job

import findcoo.batch.sample.MessageResource
import findcoo.batch.sample.domain.Message
import findcoo.batch.sample.domain.MessageRepository
import findcoo.batch.sample.domain.Notification
import findcoo.batch.sample.domain.NotificationRepository
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SaveOneBatchConfiguration(private val stepBuilderFactory: StepBuilderFactory,
                                private val jobBuilderFactory: JobBuilderFactory) {

    @Bean
    fun messageSaveOneItemWriter(messageRepository: MessageRepository): ItemWriter<MessageResource> = ItemWriter { items ->
        items.forEach { messageRepository.save(Message(to = it.to, from = it.from, content = it.content)) }
    }

    @Bean
    fun notificationSaveOneItemWriter(notificationRepository: NotificationRepository): ItemWriter<MessageResource> = ItemWriter { items ->
        items.forEach { notificationRepository.save(Notification(to = it.to, from = it.from, content = it.content)) }
    }

    @Bean
    fun messageSaveOneMainStep(messageGenerator: ItemReader<MessageResource>, messageSaveOneItemWriter: ItemWriter<MessageResource>): Step =
            stepBuilderFactory.get("messageSaveOneMainStep")
                    .chunk<MessageResource, MessageResource>(5000)
                    .reader(messageGenerator)
                    .writer(messageSaveOneItemWriter)
                    .build()

    @Bean
    fun notificationSaveOneMainStep(messageGenerator: ItemReader<MessageResource>, notificationSaveOneItemWriter: ItemWriter<MessageResource>): Step =
            stepBuilderFactory.get("notificationSaveOneMainStep")
                    .chunk<MessageResource, MessageResource>(5000)
                    .reader(messageGenerator)
                    .writer(notificationSaveOneItemWriter)
                    .build()


    @Bean
    fun messageSaveOneJob(messageSaveOneMainStep: Step): Job =
            jobBuilderFactory.get("messageSaveOneJob")
                    .incrementer(RunIdIncrementer())
                    .start(messageSaveOneMainStep)
                    .build()

    @Bean
    fun notificationSaveOneJob(notificationSaveOneMainStep: Step): Job =
            jobBuilderFactory.get("notificationSaveOneMainStep")
                    .incrementer(RunIdIncrementer())
                    .start(notificationSaveOneMainStep)
                    .build()
}

