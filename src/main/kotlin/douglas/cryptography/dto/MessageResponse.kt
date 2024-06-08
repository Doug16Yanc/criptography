package douglas.cryptography.dto

import douglas.cryptography.domain.Message

data class MessageResponse(
    var id : Long,
    var message : String,
    var token : String
){
    companion object{
        fun fromMessage(message: Message) : MessageResponse? {
            return message.rawMessage?.let {
                message.rawToken?.let { it1 ->
                    MessageResponse(
                        message.messageId,
                        it,
                        it1
                    )
                }
            }
        }
    }
}