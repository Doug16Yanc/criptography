package douglas.cryptography.controller

import douglas.cryptography.dto.CreateMessageRequest
import douglas.cryptography.dto.MessageResponse
import douglas.cryptography.dto.UpdateMessageRequest
import douglas.cryptography.service.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = arrayOf("/messages"))
class MessageController {

    @Autowired
    lateinit var messageService : MessageService


    @PostMapping
    fun create(@RequestBody createMessageRequest: CreateMessageRequest) : ResponseEntity<Void> {
        messageService.create(createMessageRequest)
        return ResponseEntity.ok().build()
    }

    @GetMapping
    fun listAll(@RequestParam(name = "page", defaultValue = "0") page : Int, @RequestParam(name = "pageSize", defaultValue = "10") pageSize : Int): ResponseEntity<Page<MessageResponse>> {
        var body = messageService.listAll(page, pageSize)

        return ResponseEntity.ok(body)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable(value = "id") id : Long): ResponseEntity<MessageResponse> {
        var body = messageService.findById(id)

        return ResponseEntity.ok(body)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable(value = "id") id : Long, @RequestBody updateMessageRequest: UpdateMessageRequest) : ResponseEntity<Void> {
        messageService.update(id, updateMessageRequest)

        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("{id}")
    fun deleteById(@PathVariable(value = "id") id : Long) : ResponseEntity<Void> {
        messageService.deleteById(id)
        return ResponseEntity.noContent().build()
    }

}