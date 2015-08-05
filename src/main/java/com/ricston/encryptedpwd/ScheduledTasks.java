package com.ricston.encryptedpwd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.DataSourceHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	private static final Logger LOG = LoggerFactory.getLogger(ScheduledTasks.class);

	private static boolean emailSent = false;

	@Autowired
	ApplicationContext appContext;

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	SimpleMailMessage mailMessage;

	@Value("${email.to}")
	private String toAddress;

	@Scheduled(fixedDelay = 7000)
	public void detectLostDbConnection() {
		DataSourceHealthIndicator dshi = appContext.getBean(DataSourceHealthIndicator.class);
		Health health = dshi.health();
		Status status = health.getStatus();

		if (status != null && "DOWN".equals(status.getCode())) {
			if (!emailSent) {
				LOG.error("Database connection lost");
				try {
					mailSender.send(dbConnectionLostMailMessage());
					emailSent = true;
				} catch (MailException ex) {
					LOG.error("'Database connection lost' email notification failed");
				}
			}
		} else {
			emailSent = false;
		}
	}

	private SimpleMailMessage dbConnectionLostMailMessage() {
		SimpleMailMessage msg = new SimpleMailMessage(this.mailMessage);
		msg.setSubject("Database connection lost");
		msg.setTo(toAddress);
		msg.setText("Houston, we have a problem");

		return msg;
	}

}
