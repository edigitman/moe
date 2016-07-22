package ro.agitman.moe.service;

import ro.agitman.moe.model.User;

/**
 * Created by d-uu31cq on 22.07.2016.
 */
public interface EmailService {

    void sendRequestNewPassword();

    void sendAccountCreated();

    void sendAccountEnabled();

    void sendExamCreated(User user);

}
