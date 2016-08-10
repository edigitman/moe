package ro.agitman.moe.service;

import ro.agitman.moe.model.User;
import ro.agitman.moe.model.VerificationToken;

/**
 * Created by d-uu31cq on 22.07.2016.
 */
public interface EmailService {

    void sendRequestNewPassword(VerificationToken token);

    void sendAccountCreated();

    void sendAccountEnabled();

    void sendExamCreated(User user);

}
