package findcoo.batch.sample.domain

import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.*

@Entity
class Notification(
        @Id
        @GeneratedValue(strategy = GenerationType.TABLE)
        var id: Long? = null,

        @ManyToOne(cascade = [ CascadeType.PERSIST])
        var from: User,

        @ManyToOne(cascade = [ CascadeType.PERSIST])
        var to: User,

        var content: String
)

interface NotificationRepository : JpaRepository<Notification, Long>