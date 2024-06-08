package douglas.cryptography.dto

data class CreateMessageRequest(
    var messageContent : String,
    var token : String
)
