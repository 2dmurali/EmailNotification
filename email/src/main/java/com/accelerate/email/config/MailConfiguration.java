package com.accelerate.email.config;

import java.util.Map;
import java.util.Properties;

import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfiguration {
	@Autowired
	private MailProperties properties;

	@Autowired(required = false)
	private Session session;

	@Bean(name="mailSender")
	public JavaMailSenderImpl mailSender() {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		if (this.session != null) {
			sender.setSession(this.session);
		}
		else {
			applyProperties(sender);
		}
		return sender;
	}
	private void applyProperties(JavaMailSenderImpl sender) {
		sender.setHost(this.properties.getHost());
		if (this.properties.getPort() != null) {
			sender.setPort(this.properties.getPort());
		}
		sender.setUsername(this.properties.getUsername());
		sender.setPassword(this.properties.getPassword());
		sender.setProtocol(this.properties.getProtocol());
		if (this.properties.getDefaultEncoding() != null) {
			sender.setDefaultEncoding(this.properties.getDefaultEncoding().name());
		}
		if (!this.properties.getProperties().isEmpty()) {
			sender.setJavaMailProperties(asProperties(this.properties.getProperties()));
		}
	}
	private Properties asProperties(Map<String, String> source) {
		Properties properties = new Properties();
		properties.putAll(source);
		return properties;
	}
}
