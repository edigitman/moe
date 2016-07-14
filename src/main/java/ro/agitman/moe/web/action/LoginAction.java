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

        if (!user.getEnabled()) {
            addError("bad_credentials");
            return ERROR;
        }

        setSessionObj(user);
        setSessionGroup(user.getRole());

        addMessage("login_successfully");

        return SUCCESS;
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
