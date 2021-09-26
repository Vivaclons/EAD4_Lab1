package kz.spring.support.controller;

import kz.spring.support.model.Message;
import kz.spring.support.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product/message")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("")
    public List<Message> findAll(){
       return messageService.findAll();
    }

    @PostMapping("/create")
    public Message createMessage(Message message){
        return messageService.saveMessage(message);
    }

//    @GetMapping("")
//    public void deleteMessage(@PathVariable("id") Long id){
//        messageService.deleteById(id);
//    }
//
//    @PostMapping("/update")
//    public Message updateMessage(Message message){
//        return messageService.saveMessage(message);
//    }

    @DeleteMapping("/{id}")
    public void deleteMessageByID(@PathVariable("id") long id) {
        messageService.deleteById(id);
    }

    @PutMapping("")
    public void updateBook(@RequestBody Message message) {
        messageService.update(message);
    }
}
