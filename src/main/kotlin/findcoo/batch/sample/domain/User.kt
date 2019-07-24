package findcoo.batch.sample.domain

import org.hibernate.annotations.GenericGenerator
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        //@GenericGenerator(name = "native", strategy = "native")
        var id: Long? = null,
        var name: String
)

interface UserRepository : JpaRepository<User, Long>