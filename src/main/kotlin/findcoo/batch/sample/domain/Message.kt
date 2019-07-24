package findcoo.batch.sample.domain

import org.hibernate.annotations.GenericGenerator
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.*

@Entity
class Message(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        // @GenericGenerator(name = "native", strategy = "native")
        var id: Long? = null,

        @ManyToOne(cascade = [ CascadeType.PERSIST ])
        var from: User,

        @ManyToOne(cascade = [ CascadeType.PERSIST ])
        var to: User,

        var content: String
)

interface  MessageRepository : JpaRepository<Message, Long>

