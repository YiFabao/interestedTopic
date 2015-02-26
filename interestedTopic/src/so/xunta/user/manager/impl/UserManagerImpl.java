package so.xunta.user.manager.impl;

import java.util.List;

import org.hibernate.Session;

import so.xunta.user.entity.User;
import so.xunta.user.manager.UserManager;
import so.xunta.utils.HibernateUtils;
public class UserManagerImpl implements UserManager {

	public void addUser(User user) {
		// TODO Auto-generated method stub
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public User findOtherUser(String uid) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String query = "from User as u where u.uid=?";
			User user = (User) session.createQuery(query).setParameter(0, uid)
					.uniqueResult();
			session.getTransaction().commit();
			return user;
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public User findUser(String userName, String password) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String query = "from User as u where u.userName=? and u.password=?";
			User user = (User) session.createQuery(query).setParameter(0,
					userName).setParameter(1, password).uniqueResult();
			session.getTransaction().commit();
			return user;
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public User findUser(String xunta_username) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String query = "from User as u where u.xunta_username=?";
			User user = (User) session.createQuery(query).setParameter(0,
					xunta_username).uniqueResult();
			session.getTransaction().commit();
			return user;
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public void updateUser(User user) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			session.update(user);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}

	}
	
	public static void main(String[] args) {
	
			UserManager um=new UserManagerImpl();
			User user=um.checkRegisterUserExist("1019357922@qq.com","962297");
			System.out.println(user);
		
	
	
	}


	@Override
	public User getUser(String token, String mark) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User checkRegisterUserExist(String xunta_username, String password) {
		//登录分三种：本地登录，qq登录，微博登录
		//本地登录,验证用户名，密码即
		System.out.println(xunta_username+" "+password);
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String query = "from User as u where (u.xunta_username=? or u.email=?) and (u.password=?)";
			User user = (User) session.createQuery(query).setParameter(0,xunta_username)
														.setParameter(1, xunta_username)
														.setParameter(2, password).uniqueResult();
			session.getTransaction().commit();
			return user;
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
		
		//qq登录,验证qq_openId是否存在,若存在,通过,不存在,添加，然后引导用户进行本地账号创建
		
		//微博登录，验证weibo_accessToken是否存在,若存在，通过,若不存在,则添加，然后引导用户进行本地账号创建
		
	}

	@Override
	public User findUserByEmail(String email) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String query = "from User as u where u.email=?";
			User user = (User) session.createQuery(query).setParameter(0,
					email).uniqueResult();
			session.getTransaction().commit();
			return user;
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public User findUserbyQQOpenId(String qqOpenId) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String query = "from User as u where u.qq_openId=?";
			User user = (User) session.createQuery(query).setParameter(0,
					qqOpenId).uniqueResult();
			session.getTransaction().commit();
			return user;
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public User findUserByWeiboUid(String weiboUid) {
		Session session = HibernateUtils.openSession();
		try {
			session.beginTransaction();
			String query = "from User as u where u.weibo_uid=?";
			User user = (User) session.createQuery(query).setParameter(0,
					weiboUid).uniqueResult();
			session.getTransaction().commit();
			return user;
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}
	

	@Override
	public List<User> findUserListByUserIdList(List<Long> userIdList) {
		Session session = HibernateUtils.openSession();
		String hql = "from User as u where u.id in (:userIdList)";
		org.hibernate.Query query = session.createQuery(hql);
		query.setParameterList("userIdList", userIdList);
		@SuppressWarnings("unchecked")
		List<User> userList = query.list();
		session.close();
		return userList;
	}

	@Override
	public User findUserById(int userId) {
		Session session = HibernateUtils.openSession();
		String hql = "from User as u where u.id =?";
		org.hibernate.Query query = session.createQuery(hql);
		query.setInteger(0, userId);
		User user = (User) query.uniqueResult();
		session.close();
		return user;
	}

}