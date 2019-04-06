package edu.uph.ii.platformy.services;

public interface EmailSender {
    void sendEmail(String to, String subject, String content);
}