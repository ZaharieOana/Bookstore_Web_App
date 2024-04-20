package com.example.Bookstore.functionalities.cronjob;

import com.example.Bookstore.functionalities.EmailService;
import com.example.Bookstore.model.User;
import com.example.Bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CronJobService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public void doTask(){
        List<User> users = userRepository.findAllByNewsletter(true);
        System.out.println("Until now (" + LocalDateTime.now() + ") there are " + users.size() + " subscribers");
        String body = "Until now (" + LocalDateTime.now() + ") there are " + users.size() + " subscribers";
        emailService.sendEmail("zaharieoanadenisa@gmail.com", "Subscribers count", body);
    }
}
