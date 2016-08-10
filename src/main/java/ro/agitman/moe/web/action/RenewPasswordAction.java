package ro.agitman.moe.web.action;

import org.mentawai.core.BaseAction;
import ro.agitman.moe.dao.UserDao;
import ro.agitman.moe.dao.VerificationTokenDao;
import ro.agitman.moe.model.User;
import ro.agitman.moe.model.VerificationToken;
import ro.agitman.moe.service.EmailService;

import java.util.Date;
import java.util.UUID;

/**
 * Created by d-uu31cq on 07.07.2016.
 */
public class RenewPasswordAction extends BaseAction {

    private final UserDao userDao;
    private final VerificationTokenDao tokenDao;
    private final EmailService emailService;

    public RenewPasswordAction(UserDao userDao, EmailService emailService, VerificationTokenDao verificationTokenDao) {
        this.userDao = userDao;
        this.tokenDao = verificationTokenDao;
        this.emailService = emailService;
    }

    /**
     * Request new password
     *
     * @return .
     */
    public String request() {

        String email = input().getString("email");

        User user = userDao.findByEmail(email);
        if (user != null) {
            VerificationToken token = new VerificationToken();
            token.setDatecreated(new Date());
            token.setUserid(user.getId());
            token.setVerified(0);
            token.setToken(UUID.randomUUID().toString());
            tokenDao.insert(token);

            emailService.sendRequestNewPassword(token);

            return SUCCESS;
        }

        addError("Adresa de email invalida !");
        return ERROR;
    }

    /**
     * Confirm from email address that you actually requested the change, gather some info about you based on token
     *
     * @return .
     */
    public String confirm() {

        String tokenValue = input().getString("t");

        VerificationToken token = tokenDao.findByToken(tokenValue);

        if (token.getVerified() == 0) {
            output().setValue("token", token);
            return SUCCESS;
        }

        return ERROR;
    }

    /**
     * Confirm the new passeord from web-page
     *
     * @return .
     */
    public String validate() {

        input().getString("token.token");
        input().getString("newp");




        return SUCCESS;
    }

}
