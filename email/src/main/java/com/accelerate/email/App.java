package com.accelerate.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
public class App 
{
	@Autowired
	private static MailSender mailSender;
    public static void main( String[] args )
    {
    	System.out.println(mailSender);
        System.out.println( "Hello World!" );
    }
}
