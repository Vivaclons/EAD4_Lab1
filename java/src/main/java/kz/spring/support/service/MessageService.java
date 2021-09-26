package kz.spring.support.service;

import kz.spring.support.model.Message;
import kz.spring.support.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message findById(Long id){
        return messageRepository.getById(id);
    }

    public List<Message> findAll(){
        return messageRepository.findAll();
    }

    public Message saveMessage(Message message){
        return messageRepository.save(message);
    }

    public void deleteById(Long id){
        messageRepository.deleteById(id);
    }

    public void update (Message message) {
        messageRepository.save(message);
    }
}
