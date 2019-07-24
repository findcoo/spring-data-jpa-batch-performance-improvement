package findcoo.batch.sample

import findcoo.batch.sample.domain.User
import org.springframework.batch.core.annotation.BeforeRead
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.ItemReader
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

data class MessageResource(val to: User,
                           val from: User,
                           val content: String)


@StepScope
@Component
class MessageGenerator : ItemReader<MessageResource> {

    @Value("#{jobParameters['readCount']}")
    var readCount: String? = null

    companion object {
        private var readOffset: Int = 0
    }

    @BeforeRead
    fun beforeRead() {
        readOffset++
    }

    override fun read(): MessageResource? {
        val to: User = User(name = getRandomName())
        val from: User = User(name = getRandomName())
        val count: Int = if (readCount != null)  readCount!!.toInt() else readOffset
        return if (readOffset < count) MessageResource(to, from, getRandomMessage()) else null
    }
}
