package douglas.cryptography.domain

import douglas.cryptography.service.CryptoService
import jakarta.persistence.*
import kotlin.jvm.Transient

@Entity
@Table(name = "tb_messages")
class Message(rawMessage: String, rawToken: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    val messageId: Long = 0

    @Column(name = "message_encrypted")
    var messageEncrypted: String? = null

    @Column(name = "token")
    var token: String? = null

    @Transient
    var rawMessage: String? = rawMessage

    @Transient
    var rawToken: String? = rawToken


    fun setMessage(rawMessage: String) {
        this.rawMessage = rawMessage
    }
    @PrePersist
    fun prePersist() {
        messageEncrypted = CryptoService.encrypt(rawMessage ?: "")
        token = CryptoService.encrypt(rawToken ?: "")
    }

    @PostLoad
    fun postLoad() {
        messageEncrypted = CryptoService.encrypt(rawMessage ?: "")
        token = CryptoService.encrypt(rawToken ?: "")
    }
}
