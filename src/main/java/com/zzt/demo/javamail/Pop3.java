package com.zzt.demo.javamail;

import java.io.IOException;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeUtility;
import javax.mail.search.FlagTerm;
/**
 * @Description:  imaps 邮件接收，接收未读，标识为已读
 * @Author: zhouzhengtao
 * @Date: 2020/11/04
 * @param null:
 * @return: null
 **/
public class Pop3 {

	final String popHost;
	final String username;
	final String password;
	final boolean debug;

	public Pop3(String popHost, String username, String password) {
		this.popHost = popHost;
		this.username = username;
		this.password = password;
		this.debug = true;
	}

	public static void main(String[] args) throws Exception {
		//Pop3 pop = new Pop3("smtp.qq.com", "3164990661@qq.com", "gvlqaehychwrdddf");
		Pop3 pop = new Pop3("smtp.qq.com", "1403407885@qq.com", "pvwvzzrcslycgdfa");
        Folder folder = null;
        Store store = null;
		try {
		    store = pop.createSSLStore();
            store.connect("smtp.qq.com", 993, "1403407885@qq.com", "gmujcqpuxshkgghg");
            //INBOX 表示收件箱
            folder = store.getFolder("INBOX");
			// 以读写方式打开:
			folder.open(Folder.READ_WRITE);
			// 打印邮件总数/新邮件数量/未读数量/已删除数量:
			System.out.println("Total messages: " + folder.getMessageCount());
			System.out.println("New messages: " + folder.getNewMessageCount());
			System.out.println("Unread messages: " + folder.getUnreadMessageCount());
			System.out.println("Deleted messages: " + folder.getDeletedMessageCount());
			//false代表未读，true代表已读
            FlagTerm ft = new FlagTerm(new Flags(Flags.Flag.SEEN), false);

            //如果服务器支持搜索（很多IMAP服务器支持，而大多数POP服务器不支持），就很容易在文件夹中搜索满足条件的消息。可以搜索邮件的很多的方面
			Message[] messages = folder.search(ft);
			for (Message message : messages) {
				//String uid = inbox.getUID(message);
				//System.out.println("uid: 为"+uid);
                Flags flags = message.getFlags();
                if (flags.contains(Flags.Flag.SEEN)) {
                    System.out.println("这是一封已读邮件");
                }
                else {
                    printMessage((MimeMessage) message);
                    System.out.println("未读邮件");
                    message.setFlag(Flags.Flag.SEEN, true);
                }

            }
		} finally {
			if (folder != null) {
				try {
					folder.close(true);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
			if (store != null) {
				try {
					store.close();
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Store createSSLStore() throws MessagingException {
		/*Properties props = new Properties();
		props.setProperty("mail.store.protocol", "pop3");
		props.setProperty("mail.pop3.port", "995"); // 主机端口号
		props.setProperty("mail.pop3.host", this.popHost);// POP3主机名
		// 启动SSL:
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.port", "995");
		URLName url = new URLName("pop3", this.popHost, 995, "", this.username, this.password);
		Session session = Session.getInstance(props, null);*/
		//session.setDebug(this.debug); // 显示调试信息
        Session session = Session.getDefaultInstance(new Properties());

        return session.getStore("imaps"); // 使用imap会话机制，连接服务器
	}

	static void printMessage(MimeMessage msg) throws IOException, MessagingException {
		System.out.println("Subject: " + MimeUtility.decodeText(msg.getSubject()));
		System.out.println("From: " + getFrom(msg));
		System.out.println("To: " + getTo(msg));
		System.out.println("Sent: " + msg.getSentDate());
		System.out.println("Seen: " + msg.getFlags().contains(Flags.Flag.SEEN));
		System.out.println("Priority: " + getPriority(msg));
		System.out.println("Size: " + msg.getSize() / 1024 + "kb");
		/*Enumeration<Header> allHeaders = msg.getAllHeaders();
		while (allHeaders.hasMoreElements()) {
			Header header = allHeaders.nextElement();
			System.out.println("*****:  "+header.getName() + header.getValue());
		}*/
		System.out.println("Body: " + getBody(msg));
		System.out.println("message_id+++++"+msg.getMessageID());
        System.out.println("----------------------------------");
	}

	static String getFrom(MimeMessage msg) throws IOException, MessagingException {
		Address[] froms = msg.getFrom();
		return addressToString(froms[0]);
	}

	static String getTo(MimeMessage msg) throws MessagingException, IOException {
		// 使用 msg.getAllRecipients() 获取所有收件人
			Address[] tos = msg.getRecipients(RecipientType.TO);
		List<String> list = new ArrayList<>();
		for (Address to : tos) {
			list.add(addressToString(to));
		}
		return String.join(", ", list);
	}

	static String addressToString(Address addr) throws IOException {
		InternetAddress address = (InternetAddress) addr;
		String personal = address.getPersonal();
		return personal == null ? address.getAddress()
				: (MimeUtility.decodeText(personal) + " <" + address.getAddress() + ">");
	}

	static String getPriority(MimeMessage msg) throws MessagingException {
		String priority = "Normal";
		String[] headers = msg.getHeader("X-Priority");
		if (headers != null) {
			String header = headers[0];
			if ("1".equals(header) || "high".equalsIgnoreCase(header)) {
				priority = "High";
			} else if ("5".equals(header) || "low".equalsIgnoreCase(header)) {
				priority = "Low";
			}
		}
		return priority;
	}

	static String getBody(Part part) throws MessagingException, IOException {
		if (part.isMimeType("text/*")) {

			return part.getContent().toString();
		}
		if (part.isMimeType("multipart/*")) {
			Multipart multipart = (Multipart) part.getContent();
			for (int i = 0; i < multipart.getCount(); i++) {
				BodyPart bodyPart = multipart.getBodyPart(i);
				String body = getBody(bodyPart);
				if (!body.isEmpty()) {
					return body;
				}
			}
		}
		return "";
	}
}
