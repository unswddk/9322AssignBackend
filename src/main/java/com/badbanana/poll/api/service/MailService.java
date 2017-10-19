package com.badbanana.poll.api.service;

public interface MailService {

    void sendMail(String to, String subject, String content);

}