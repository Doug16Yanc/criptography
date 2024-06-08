package douglas.cryptography.repository

import douglas.cryptography.domain.Message
import org.springframework.data.jpa.repository.JpaRepository

interface MessageRepository : JpaRepository<Message, Long> {
}