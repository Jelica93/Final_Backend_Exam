package com.iktpreobuka.dnevnik.services;

import com.iktpreobuka.dnevnik.entities.EmailObject;

public interface EmailService {

	void sendSimpleMessage(EmailObject object);
	void sendTemplateMessage(EmailObject object) throws Exception;
	void sendMessageWithAttachment(EmailObject object, String pathToAttachment) throws Exception;
}
