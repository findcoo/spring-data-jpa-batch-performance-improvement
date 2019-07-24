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
class SaveAllBatchConfiguration(private val stepBuilderFactory: StepBuilderFactory,
                                private val jobBuilderFactory: JobBuilderFactory) {

    @Bean
    fun messageSaveAllItemWriter(messageRepository: MessageRepository): ItemWriter<MessageResource> = ItemWriter {
        items ->
            val messageList: List<Message> = items.map { Message(to = it.to, from = it.from , content = it.content) }
            messageRepository.saveAll(messageList)
    }

    @Bean
    fun notificationSaveAllItemWriter(notificationRepository: NotificationRepository): ItemWriter<MessageResource> = ItemWriter {
        items ->
            val notificationList: List<Notification> = items.map { Notification(to = it.to, from = it.from, content = it.content) }
            notificationRepository.saveAll(notificationList)
    }

    @Bean
    fun messageSaveAllMainStep(messageGenerator: ItemReader<MessageResource>, messageSaveAllItemWriter: ItemWriter<MessageResource>): Step =
            stepBuilderFactory.get("messageSaveAllMainStep")
                    .chunk<MessageResource, MessageResource>(5000)
                    .reader(messageGenerator)
                    .writer(messageSaveAllItemWriter)
                    .build()

    @Bean
    fun notificationSaveAllMainStep(messageGenerator: ItemReader<MessageResource>, notificationSaveAllItemWriter: ItemWriter<MessageResource>): Step =
            stepBuilderFactory.get("notificationSaveAllMainStep")
                    .chunk<MessageResource, MessageResource>(5000)
                    .reader(messageGenerator)
                    .writer(notificationSaveAllItemWriter)
                    .build()


    @Bean
    fun messageSaveAllJob(messageSaveAllMainStep: Step): Job =
            jobBuilderFactory.get("messageSaveAllJob")
                    .incrementer(RunIdIncrementer())
                    .start(messageSaveAllMainStep)
                    .build()

    @Bean
    fun notificationSaveAllJob(notificationSaveAllMainStep: Step): Job =
            jobBuilderFactory.get("notificationSaveAllJob")
                    .incrementer(RunIdIncrementer())
                    .start(notificationSaveAllMainStep)
                    .build()
}