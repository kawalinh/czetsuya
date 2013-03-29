package com.czetsuya.commons.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Edward P. Legaspi
 * @since Nov 18, 2012
 **/
public class MailUtils {
	/**
	 * Send a MIME type email message without attachments
	 * 
	 * @param from
	 *            Email sender address
	 * @param to
	 *            A list of email recipients
	 * @param bcc
	 *            A list of email BCC recipients
	 * @param subject
	 *            Email subject
	 * @param message
	 *            Email message body
	 * @param messageHtml
	 *            Email message body in HTML
	 * @param session
	 *            Email session
	 * @throws MessagingException
	 */
	public static void send(String from, String[] to, String[] bcc,
			String subject, String message, String messageHtml, Session session)
			throws MessagingException {
		sendMessage(from, to, new String[0], bcc, subject, message,
				messageHtml, null, session);
	}

	/**
	 * Send a MIME type email message with attachments
	 * 
	 * @param from
	 *            Email sender address
	 * @param to
	 *            A list of email recipients
	 * @param bcc
	 *            A list of email BCC recipients
	 * @param subject
	 *            Email subject
	 * @param message
	 *            Email message body
	 * @param messageHtml
	 *            Email message body in HTML
	 * @param attachments
	 *            A list of attachments as a list of files
	 * @param session
	 *            Email session
	 * @throws MessagingException
	 */
	public static void send(String from, String[] to, String[] bcc,
			String subject, String message, String messageHtml,
			File[] attachments, Session session) throws MessagingException {

		send(from, to, new String[0], bcc, subject, message, messageHtml,
				attachments, session);

	}

	/**
	 * Send a MIME type email message with attachments
	 * 
	 * @param from
	 *            Email sender address
	 * @param to
	 *            A list of email recipients
	 * @param bcc
	 *            A list of email BCC recipients
	 * @param subject
	 *            Email subject
	 * @param message
	 *            Email message body
	 * @param messageHtml
	 *            Email message body in HTML
	 * @param attachments
	 *            A list of attachments as a list of files
	 * @param session
	 *            Email session
	 * @throws MessagingException
	 */
	public static void send(String from, String[] to, String[] cc,
			String[] bcc, String subject, String message, String messageHtml,
			File[] attachments, Session session) throws MessagingException {

		// Construct attachments as mime message body parts
		List<BodyPart> attachmentBodies = null;
		if ((attachments != null) && (attachments.length > 0)) {
			attachmentBodies = new Vector<BodyPart>();
			for (File f : attachments) {
				DataSource source = new FileDataSource(f);
				BodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(f.getName());
				attachmentBodies.add(messageBodyPart);
			}
		}

		sendMessage(from, to, cc, bcc, subject, message, messageHtml,
				attachmentBodies, session);
	}

	/**
	 * Send a MIME type email message with attachments
	 * 
	 * @param msg
	 *            An email message with From, To, BCC, Subject and other headers
	 *            set up when used in reply to another message
	 * @param message
	 *            Email message body
	 * @param messageHtml
	 *            Email message body in HTML
	 * @param attachments
	 *            A list of attachments as a list of files
	 * @param session
	 *            Email session
	 * @throws MessagingException
	 */
	public static void send(Message msg, String message, String messageHtml,
			File[] attachments, Session session) throws MessagingException {

		// Construct attachments as mime message body parts
		List<BodyPart> attachmentBodies = null;
		if ((attachments != null) && (attachments.length > 0)) {
			attachmentBodies = new Vector<BodyPart>();
			for (File f : attachments) {
				DataSource source = new FileDataSource(f);
				BodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(f.getName());
				attachmentBodies.add(messageBodyPart);
			}
		}

		sendMessage(msg, message, messageHtml, attachmentBodies, session);
	}

	/**
	 * Send a MIME type email message with attachments
	 * 
	 * @param from
	 *            Email sender address
	 * @param to
	 *            A list of email recipients
	 * @param bcc
	 *            A list of email BCC recipients
	 * @param subject
	 *            Email subject
	 * @param message
	 *            Email message body
	 * @param messageHtml
	 *            Email message body in HTML
	 * @param attachments
	 *            A list of attachments as an array of the following format:
	 *            attachment byte array, attachment MIME type, attachment
	 *            filename
	 * @param session
	 *            Email session
	 * @throws MessagingException
	 */
	public static void send(String from, String[] to, String[] bcc,
			String subject, String message, String messageHtml,
			Object[][] attachments, Session session) throws MessagingException {

		// Construct attachments as mime message body parts
		List<BodyPart> attachmentBodies = null;
		if ((attachments != null) && (attachments.length > 0)) {
			attachmentBodies = new Vector<BodyPart>();
			for (Object[] attachmentInfo : attachments) {
				DataSource source = new ByteArrayDataSource(
						(byte[]) attachmentInfo[0], (String) attachmentInfo[1]);
				BodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName((String) attachmentInfo[2]);
				attachmentBodies.add(messageBodyPart);
			}
		}

		sendMessage(from, to, new String[0], bcc, subject, message,
				messageHtml, attachmentBodies, session);
	}

	/**
	 * Send a MIME type email message with attachments
	 * 
	 * @param msg
	 *            An email message with From, To, BCC, Subject and other headers
	 *            set up when used in reply to another message
	 * @param message
	 *            Email message body
	 * @param messageHtml
	 *            Email message body in HTML
	 * @param attachments
	 *            A list of attachments as an array of the following format:
	 *            attachment byte array, attachment MIME type, attachment
	 *            filename
	 * @param session
	 *            Email session
	 * @throws MessagingException
	 * @throws IOException
	 */
	public static void send(Message msg, String message, String messageHtml,
			Object[][] attachments, Session session) throws MessagingException,
			IOException {

		// Construct attachments as mime message body parts
		List<BodyPart> attachmentBodies = null;
		if ((attachments != null) && (attachments.length > 0)) {
			attachmentBodies = new Vector<BodyPart>();
			for (Object[] attachmentInfo : attachments) {
				DataSource source = null;
				if (attachmentInfo[0] instanceof byte[]) {
					source = new ByteArrayDataSource(
							(byte[]) attachmentInfo[0],
							(String) attachmentInfo[1]);
				} else if (attachmentInfo[0] instanceof InputStream) {
					source = new ByteArrayDataSource(
							(InputStream) attachmentInfo[0],
							(String) attachmentInfo[1]);
				}
				BodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName((String) attachmentInfo[2]);
				attachmentBodies.add(messageBodyPart);
			}
		}

		sendMessage(msg, message, messageHtml, attachmentBodies, session);
	}

	/**
	 * Send a MIME type email message with optional attachments
	 * 
	 * @param from
	 *            Email sender address
	 * @param to
	 *            A list of email recipients
	 * @param bcc
	 *            A list of email BCC recipients
	 * @param subject
	 *            Email subject
	 * @param message
	 *            Email message body
	 * @param messageHtml
	 *            Email message body in HTML
	 * @param attachments
	 *            A list of attachments in MIME body part form
	 * @param session
	 *            Email session
	 * @throws MessagingException
	 */
	private static void sendMessage(String from, String[] to, String[] cc,
			String[] bcc, String subject, String message, String messageHtml,
			List<BodyPart> attachments, Session session)
			throws MessagingException {

		Message msg = null;

		try {

			// create a message
			msg = new MimeMessage(session);

			// set the from and to address
			InternetAddress addressFrom = new InternetAddress(from);
			msg.setFrom(addressFrom);

			InternetAddress[] addressTo = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++) {
				addressTo[i] = new InternetAddress(to[i]);
			}
			msg.setRecipients(Message.RecipientType.TO, addressTo);

			if (bcc != null && bcc.length > 0) {
				InternetAddress[] addressBcc = new InternetAddress[bcc.length];
				for (int i = 0; i < bcc.length; i++) {
					addressBcc[i] = new InternetAddress(bcc[i]);
				}
				msg.setRecipients(Message.RecipientType.BCC, addressBcc);
			}

			if (cc != null && cc.length > 0) {
				InternetAddress[] addressCC = new InternetAddress[cc.length];
				for (int i = 0; i < cc.length; i++) {
					addressCC[i] = new InternetAddress(cc[i]);
				}
				msg.setRecipients(Message.RecipientType.CC, addressCC);
			}

			// Setting the Subject and Content Type
			msg.setSubject(subject);

		} catch (MessagingException ex) {
			Logger log = LoggerFactory.getLogger(MailUtils.class);
			log.error(
					"[MAIL COMPONENT] Error when trying to send email : From={}; To={}; subject={}",
					new Object[] { ex, from, to, subject });
			throw ex;
		}

		sendMessage(msg, message, messageHtml, attachments, session);

	}

	/**
	 * Send a MIME type email message with optional attachments
	 * 
	 * @param msg
	 *            An email message with From, To, BCC, Subject and other headers
	 *            set up
	 * @param message
	 *            Email message body
	 * @param messageHtml
	 *            Email message body in HTML
	 * @param attachments
	 *            A list of attachments in MIME body part form
	 * @param session
	 *            Email session
	 * @throws MessagingException
	 */
	private static void sendMessage(Message msg, String message,
			String messageHtml, List<BodyPart> attachments, Session session)
			throws MessagingException {

		Logger log = LoggerFactory.getLogger(MailUtils.class);

		Address[] from = msg.getFrom();
		Address[] to = msg.getRecipients(RecipientType.TO);
		String subject = msg.getSubject();

		log.debug(
				"[MAIL COMPONENT] New email from={}, to={}, subject={}",
				new Object[] { InternetAddress.toString(from),
						InternetAddress.toString(to), subject });

		try {
			Multipart rootPartMixed = new MimeMultipart("related");
			msg.setContent(rootPartMixed);

			// Create the content multipart (for text and HTML)
			MimeMultipart mpContent = new MimeMultipart("alternative");

			// Create a body part to house the multipart/alternative Part
			MimeBodyPart contentPartRoot = new MimeBodyPart();
			contentPartRoot.setContent(mpContent);

			// Add the root body part to the root multipart
			rootPartMixed.addBodyPart(contentPartRoot);

			if (message != null) {
				MimeBodyPart msgPart = new MimeBodyPart();
				msgPart.setContent(message, "text/plain; charset=UTF-8");
				mpContent.addBodyPart(msgPart);
			}

			if (messageHtml != null) {
				MimeBodyPart msgHtmlPart = new MimeBodyPart();

				msgHtmlPart.setContent(messageHtml, "text/html; charset=UTF-8");
				mpContent.addBodyPart(msgHtmlPart);
			}

			if (attachments != null) {
				for (BodyPart bodyPart : attachments) {
					rootPartMixed.addBodyPart(bodyPart);
				}
			}

			Transport.send(msg);

			log.debug(
					"[MAIL COMPONENT] mail is gived to smtp server. it will try to send it : From={}; To={}; subject={}",
					new Object[] { InternetAddress.toString(from),
							InternetAddress.toString(to), subject });

		} catch (MessagingException ex) {
			log.error(
					"[MAIL COMPONENT] Error when trying to send email : From={}; To={}; subject={}",
					new Object[] { ex, InternetAddress.toString(from),
							InternetAddress.toString(to), subject });
			throw ex;
		}
	}
}
