package ro.agitman.moe.web.action;

import org.mentawai.core.BaseAction;
import ro.agitman.moe.dao.UserDao;
import ro.agitman.moe.dao.VerificationTokenDao;
import ro.agitman.moe.model.User;
import ro.agitman.moe.model.VerificationToken;
import ro.agitman.moe.service.EmailService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    public String requestNewPwd() {

        String email = input().getString("email");

        User user = userDao.findByEmail(email);
        if (user != null) {
            VerificationToken token = new VerificationToken();
            token.setDatecreated(new Date());
            token.setUserid(user.getId());
            token.setVerified(0);
            token.setToken(UUID.randomUUID().toString());
            tokenDao.insert(token);

            emailService.sendRequestNewPassword(md5(user.getEmail()), token);

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
        String email = input().getString("m");

        VerificationToken token = tokenDao.findByToken(tokenValue);

        if (token != null && token.getVerified() == 0) {

            User user = userDao.findById(token.getUserid());
            if(md5(user.getEmail()).equalsIgnoreCase(email)){
                session().setAttribute("token", token);
                return SUCCESS;
            }
        }

        return ERROR;
    }

    /**
     * Confirm the new passeord from web-page
     *
     * @return .
     */
    public String validate() {

        String pass = input().getString("password");
        String tokenValue = (String) session().getAttribute("token");
        VerificationToken token = tokenDao.findByToken(tokenValue);

        User user = userDao.findById(token.getUserid());
        user.setPassword(pass);

        userDao.save(user);

        return SUCCESS;
    }


    private String md5(String value) {
        try {
            byte[] md5Bytes = MessageDigest.getInstance("MD5").digest(value.getBytes());

            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < md5Bytes.length; i++) {
                String hex = Integer.toHexString(0xFF & md5Bytes[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
}
