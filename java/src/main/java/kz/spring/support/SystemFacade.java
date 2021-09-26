package kz.spring.support;

import kz.spring.support.controller.MessageController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SystemFacade {

    Scanner in;

    @Autowired
    private MessageController messageController;

    public void menu(){
        System.out.println("1 - show all message");
        messageController.findAll();
    }
}
