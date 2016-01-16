package cn.com.cms.user.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;

import com.mysql.jdbc.interceptors.SessionAssociationInterceptor;

import cn.com.cms.user.dao.UserDao;
import cn.com.cms.user.domain.User;
import cn.com.cms.user.exception.UserException;
import cn.itcast.commons.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;

/**
 * �û�ģ��ҵ���
 * @author qdmmy6
 *
 */

public class UserService {
	private UserDao userDao = new UserDao();
	
	/**
	 * У���û����Ƿ�ע��
	 * @param loginname
	 * @return
	 * @throws SQLException 
	 */
	public boolean ajaxValidateLoginname(String loginname) {
		try {
			return userDao.ajaxValidateLoginname(loginname);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * У��Email�Ƿ�ע��
	 * @param email
	 * @return
	 */
	public boolean ajaxValidateEmail(String email) {
		try {
			return userDao.ajaxValidateEmail(email);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ע�Ṧ��
	 * @param user
	 */
	public void regist(User user) {
		/*
		 * 1.���ݵĲ���
		 */
		user.setUid(CommonUtils.uuid());
		user.setStatus(false);
		user.setActivationCode(CommonUtils.uuid() + CommonUtils.uuid());
		
		/*
		 * 2.�����ݿ����
		 */
		try {
			userDao.add(user);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		/*
		 * 3.�����ʼ�
		 */
		/*
		 * �������ļ����ص�prop��
		 */
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
		/*
		 * ��¼������
		 */
		String host = prop.getProperty("host");//������
		String name = prop.getProperty("username");//�û���
		String pass = prop.getProperty("password");//����
		Session session = MailUtils.createSession(host, name, pass);
		/*
		 * ����Mail����
		 */
		String from = prop.getProperty("from");
		String to = user.getEmail();
		String subject = prop.getProperty("subject");
		String content = MessageFormat.format(prop.getProperty("content"), user.getActivationCode());
		Mail mail = new Mail(from, to, subject, content);
		/*
		 * �����ʼ�
		 */
		try {
			MailUtils.send(session, mail);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * �����
	 * @param code
	 * @return
	 * @throws UserException 
	 */
	public void activation(String code) throws UserException {
		/*
		 * 1. ͨ���������ѯ�û�
		 * 2. ���UserΪnull��˵������Ч�����룬�׳��쳣�������쳣��Ϣ����Ч�����룩
		 * 3. �鿴�û�״̬�Ƿ�Ϊtrue�����Ϊtrue���׳��쳣�������쳣��Ϣ���벻Ҫ���μ��
		 * 4. �޸��û�״̬Ϊtrue
		 */
		try {
			User user = userDao.findByCode(code);
			if (user == null) {
				throw new UserException("��Ч�ļ����룡");
			}
			if (user.isStatus()) {
				throw new UserException("���Ѿ������ˣ���Ҫ���μ��");
			}
			userDao.updateStatus(user.getUid(), true);//�޸�״̬
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ��¼����
	 * @param user
	 * @return
	 */
	public User login(User user) {
		try {
			return userDao.findByLoginnameAndLoginpass(user.getLoginname(), user.getLoginpass());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
