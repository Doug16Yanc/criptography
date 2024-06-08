package douglas.cryptography.service

import douglas.cryptography.domain.Message
import douglas.cryptography.dto.CreateMessageRequest
import douglas.cryptography.dto.MessageResponse
import douglas.cryptography.dto.UpdateMessageRequest
import douglas.cryptography.repository.MessageRepository

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class MessageService ( private var messageRepository : MessageRepository) {


   fun create(createMessageRequest: CreateMessageRequest) {
       var message = Message(createMessageRequest.messageContent, createMessageRequest.token)

       messageRepository.save(message)
   }

    fun findById(id : Long) : MessageResponse? {

        var message = messageRepository.findById(id)
            .orElseGet { throw ResponseStatusException(HttpStatus.NO_CONTENT)}

        return MessageResponse.fromMessage(message)
    }

    fun listAll(page: Int, pageSize: Int) : Page<MessageResponse>{
        val content = messageRepository.findAll(PageRequest.of(page, pageSize))

        return content.map { MessageResponse.fromMessage(it) }
    }

    fun update(id : Long, updateMessageRequest: UpdateMessageRequest) {

        var message = messageRepository.findById(id)
            .orElseGet  { throw ResponseStatusException(HttpStatus.NO_CONTENT)}

        message.setMessage(updateMessageRequest.message)

        messageRepository.save(message)

    }

    fun deleteById(id : Long) {
        var message = messageRepository.findById(id)
            .orElseGet { throw ResponseStatusException(HttpStatus.NO_CONTENT)}

        messageRepository.deleteById(message.messageId)
    }
}