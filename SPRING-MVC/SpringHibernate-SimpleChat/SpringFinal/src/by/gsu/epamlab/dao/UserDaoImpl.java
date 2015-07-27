package by.gsu.epamlab.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.gsu.epamlab.entity.UserEntity;
import by.gsu.epamlab.utils.StringUtils;

@Repository
public class UserDaoImpl implements UserDAO {

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public void addUser(UserEntity usr) {
		String login = usr.getLogin();
		String pass = usr.getPassword();
		StringUtils.getSafe(login);
		StringUtils.getSafe(pass);
		if(StringUtils.checked(login) && StringUtils.checked(pass) && this.sessionFactory.getCurrentSession().createQuery("from UserEntity WHERE login='"+usr.getLogin()+"' AND password='"+usr.getPassword()+"' ").list().size()==0){
			this.sessionFactory.getCurrentSession().save(usr);
		}
	}
	@Override
	public boolean hasLogin(UserEntity usr) {
	    if(this.sessionFactory.getCurrentSession().createQuery("from UserEntity WHERE login='"+usr.getLogin()+"' AND password='"+usr.getPassword()+"' ").list().size()!=0){
			return true;	
		}
		return false;
	}
	@Override
	public UserEntity getFullyUser(UserEntity usr) {
		UserEntity fullyUser = (UserEntity) this.sessionFactory.getCurrentSession().createQuery("from UserEntity WHERE login='"+usr.getLogin()+"' AND password='"+usr.getPassword()+"' ").list().get(0);
		return fullyUser;
	}

}
