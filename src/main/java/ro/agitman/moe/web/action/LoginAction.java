package ro.agitman.moe.web.action;

import com.google.common.base.Strings;
import org.mentawai.action.BaseLoginAction;
import ro.agitman.moe.dao.UserDao;
import ro.agitman.moe.model.User;

/**
 * Created by d-uu31cq on 07.07.2016.
 */
public class LoginAction extends BaseLoginAction {

    private final UserDao userDao;

    public LoginAction(UserDao userDao) {
        this.userDao = userDao;
    }

    public String execute() throws Exception {
        String email = input.getString("email");
        String pass = input.getString("password");

        if (getSessionObj() != null) {
            loadDefaultDataForUser((String) getUserGroups().get(0));
            return SUCCESS;
        }

        if (Strings.isNullOrEmpty(email)) {
            addError("unavailable_account");
            return ERROR;
        }

        if (Strings.isNullOrEmpty(pass)) {
            addError("bad_credentials");
            return ERROR;
        }

        User user = userDao.findByEmail(email);

        if (user == null) {
            addError("unavailable_account");
            return ERROR;
        }

        if (!user.getPassword().equals(encriptPass(pass))) {
            addError("bad_credentials");
            return ERROR;
        }

        if (user.getEnabled() == 0) {
            addError("bad_credentials");
            return ERROR;
        }

        setSessionObj(user);
        setSessionGroup(user.getRole());

        addMessage("login_successfully");

        loadDefaultDataForUser(user.getRole());

        return SUCCESS;
    }

    /**
     * Load default data for each user type
     * ADMIN - list of users
     * Student - list of student exams instances
     * Professor - list of exams, list of exam instances
     *
     * @param role
     */
    private void loadDefaultDataForUser(String role) {

        if ("ADMIN".equals(role)) {
            output.setValue("users", userDao.findAll());
        }

    }

    private String encriptPass(String pass) {
        return pass;
//        HashFunction hf = Hashing.md5();
//        HashCode hc = hf.newHasher()
//                .putString(pass, Charsets.UTF_8)
//                .hash();
//
//        return new String(hc.asBytes());
    }
}
