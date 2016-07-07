package ro.agitman.moe.dao.impl;

import org.mentabean.BeanSession;
import ro.agitman.moe.dao.UserDao;
import ro.agitman.moe.model.User;

/**
 * Created by d-uu31cq on 07.07.2016.
 */
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {


    public UserDaoImpl(BeanSession beanSession) {
        super(User.class, beanSession);
    }

    public User findByEmail(String email){
        User u = new User(email);
        return beanSession.loadUnique(u);
    }

}
