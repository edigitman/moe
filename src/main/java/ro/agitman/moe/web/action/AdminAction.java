package ro.agitman.moe.web.action;

import org.mentawai.core.BaseAction;
import ro.agitman.moe.dao.UserDao;
import ro.agitman.moe.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by d-uu31cq on 08.07.2016.
 */
public class AdminAction extends BaseAction {

    private final UserDao userDao;
    private final List<String> roles = new ArrayList<String>(Arrays.asList("STUDENT", "PROFESOR", "ADMIN"));

    public AdminAction(UserDao userDao) {
        this.userDao = userDao;
    }

    public String saveUser() {

        if (isPost()) {
            User user = (User) input.getValue("user");
            if (user == null) {
                return ERROR;
            }
            if (user.getId() != null) {
                userDao.update(user);
            } else {
                userDao.insert(user);
            }
        }
        return SUCCESS;
    }

    public String editUser() {
        Integer userId = input.getInt("id");

        if (userId < 1000) {
            return ERROR;
        }

        User user = userDao.findById(userId);
        if (user == null) {
            return ERROR;
        }

        output.setValue("user", user);
        output.setValue("roles", roles);

        return SUCCESS;
    }

    public String newUser() {
        output.setValue("roles", roles);
        return SUCCESS;
    }

}
