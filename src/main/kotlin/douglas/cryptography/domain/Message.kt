package douglas.cryptography.domain

import jakarta.persistence.*
import kotlin.jvm.Transient

@Entity
@Table(name = "tb_messages")
class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    val messageId: Long = 0

    @Column(name = "message_encrypted")
    lateinit var messageEncrypted: String

    @Column(name = "token")
    lateinit var token: String

    @Transient
    var rawMessage: String? = null

    @Transient
    var rawToken: String? = null


    constructor(rawMessage: String, rawToken: String) {
        this.rawMessage = rawMessage
        this.rawToken = rawToken
    }

    fun setMessage(rawMessage: String) {
        this.rawMessage = rawMessage
    }
}
