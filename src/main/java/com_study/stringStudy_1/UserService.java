package com_study.stringStudy_1;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class UserService implements UserLevelUpgradePolicy {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private PlatformTransactionManager transactionManager;

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	private MailSender mailSender;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

//	public void upgradeLevels() {
//		List<User> users = userDao.getAll();
//		for (User user : users) {
//			Boolean changed = null;
//			if (user.getLevelInt() == Level.BASIC && user.getLogin() >= 50) {
//				user.setLevelInt(Level.SILVER);
//				changed = true;
//			} else if (user.getLevelInt() == Level.SILVER && user.getRecommend() >= 30) {
//				user.setLevelInt(Level.GOLD);
//				changed = true;
//
//			} else if (user.getLevelInt() == Level.GOLD) {
//				changed = false;
//			} else {
//				changed = false;
//			}
//			if (changed) {
//				userDao.update(user);
//			}
//
//		}
//	}

	public void add(User user) {
		if (user.getLevelInt() == null)
			user.setLevelInt(Level.BASIC);
		userDao.add(user);
	}

	// 5장 서비스 추상화 리스트 5-23
	public void upgradeLevels() {
		// JTA로 바꾸려면
		// PlatformTransactionManager txManager = new JTATrasactionManager();
		TransactionStatus status = this.transactionManager.getTransaction(new DefaultTransactionDefinition());

		// 모든 사용자 정보를 가져와 한 명씩 업그레이드가 가능한지 확인하고,
		// 가능하면 업그레이드를 한다
		try {
			List<User> users = userDao.getAll();
			for (User user : users) {
				if (canUpgradeLevel(user)) {
					upgradeLevel(user);
				}

			}
			this.transactionManager.commit(status);
		} catch (RuntimeException e) {
			this.transactionManager.rollback(status);
			throw e;
		}
	}

	// 5장 서비스 추상화 리스트 5-25
	public void upgradeLevel(User user) {

//		if(user.getLevelInt() == Level.BASIC) user.setLevelInt(Level.SILVER);
//		else if(user.getLevelInt() == Level.SILVER) user.setLevelInt(Level.GOLD);
//		userDao.update(user);
		user.upgradeLevel();
		userDao.update(user);
		sendUpgradeEmail(user);
	}

	private void sendUpgradeEmail(User user) {
//		Properties props = new Properties();
//		props.put("mail.smtp.host", "mail.ksug.org");
//		Session s = Session.getInstance(props, null);
//
//		MimeMessage message = new MimeMessage(s);

//		try {
//			message.setFrom(new InternetAddress("useradmin@ksug.org"));
//			message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
//			message.setSubject("Upgrade 안내");
//			message.setText("사용자님의 등급이 " + user.getLevelInt().name() + "로 업그레이드 되었습니다.");
//
//			Transport.send(message);
//			
//		} catch (AddressException e) {
//			throw new RuntimeException(e);
//			
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//			
//	} 
//		catch (UnsupportedEncodingException e) {
//			throw new RuntimeException(e);
//		}
//		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//		mailSender.setHost("mail.server.com");

		SimpleMailMessage mailMessage = new SimpleMailMessage();

		mailMessage.setTo(user.getEmail());
		mailMessage.setFrom("useradmin@ksug.org");
		mailMessage.setSubject("Upgrade 안내");
		mailMessage.setText("사용자님의 등급이 " + user.getLevelInt().name());

		this.mailSender.send(mailMessage);
	}

	// 5장 서비스 추상화 리스트 5-24
//	private boolean canUpgradeLevel(User user) {
//		Level currentLevel = user.getLevelInt();
//
//		switch (currentLevel) {
//
//		case BASIC:
//			return (user.getLogin() >= 50);
//		case SILVER:
//			return (user.getRecommend() >= 30);
//		case GOLD:
//			return false;
//		default:
//			throw new IllegalArgumentException("Unknown Level " + currentLevel);
//		}
//
//	}
	// 5장 서비스 추상화 리스트 5-24

	public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
	public static final int MIN_LOGCOUNT_FOR_GOLD = 30;

	public boolean canUpgradeLevel(User user) {
		Level currentLevel = user.getLevelInt();

		switch (currentLevel) {

		case BASIC:
			return (user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER);
		case SILVER:
			return (user.getRecommend() >= MIN_LOGCOUNT_FOR_GOLD);
		case GOLD:
			return false;
		default:
			throw new IllegalArgumentException("Unknown Level " + currentLevel);
		}

	}

	public class DummyMaileSender implements MailSender {
		public void send(SimpleMailMessage mailMessage) throws MailException {

		}

		public void send(SimpleMailMessage[] mailMessage) throws MailException {

		}
	}

	

}