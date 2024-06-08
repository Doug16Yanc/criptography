package douglas.cryptography.dto

import douglas.cryptography.domain.Message

data class MessageResponse(
    var id : Long,
    var message : String? = null,
    var token : String? = null
){
    companion object {
        fun fromMessage(message: Message): MessageResponse {
            return MessageResponse(
                id = message.messageId,
                message = message.rawMessage,
                token = message.rawToken
            )
        }
    }

}