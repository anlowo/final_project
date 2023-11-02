//package com.example.final_project.configuration;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//import java.util.Properties;
//
//public class MailConfig {
//    @Value("${spring.mail.host}")
//    private String host;
//    @Value("${spring.mail.password}")
//    private String username;
//    @Value("${spring.mail.port}")
//    private int port;
//    @Value("${spring.mail.password}")
//    private String password;
//    @Value("${spring.mail.protocol}")
//    private String protocol;
//    @Value("${mail.debug}")
//    private String debug;
//
//    @Bean
//    public JavaMailSender getMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//
//        mailSender.setHost(host);
//        mailSender.setUsername(username);
//        mailSender.setPassword(password);
//        mailSender.setPort(port);
//
//        Properties properties = mailSender.getJavaMailProperties();
//
//        properties.setProperty("mail.transport.protocol", protocol);
//        properties.setProperty("mail.debug", debug);
//
//        return mailSender;
//    }
//}
