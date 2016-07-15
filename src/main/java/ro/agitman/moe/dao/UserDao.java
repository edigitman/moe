package ro.agitman.moe.dao;

import ro.agitman.moe.model.Exam;
import ro.agitman.moe.model.User;

import java.util.List;

/**
 * Created by d-uu31cq on 07.07.2016.
 */
public interface UserDao extends GenericDao<User>{

    User findByEmail(String email);

    List<User> findAllStudents();
}
