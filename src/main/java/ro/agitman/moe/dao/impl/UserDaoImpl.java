package ro.agitman.moe.dao.impl;

import org.mentabean.BeanSession;
import ro.agitman.moe.dao.UserDao;
import ro.agitman.moe.model.User;

import java.util.List;

/**
 * Created by d-uu31cq on 07.07.2016.
 */
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {


    public UserDaoImpl(BeanSession beanSession) {
        super(User.class, beanSession);
    }

    @Override
    public User findByEmail(String email) {
        User u = new User(email);
        return beanSession.loadUnique(u);
    }

    @Override
    public User findById(Integer id) {
        User user = new User(id);
        boolean loaded = beanSession.load(user);
        if (!loaded) {
            throw new IllegalStateException("Cannot load user by id " + id);
        }
        return user;
    }

    public List<User> findAllStudents(){
        User user = new User();
        user.setRole("STUDENT");

        return beanSession.loadList(user);
    }
}
