package ro.agitman.moe.service.impl;

import org.mentawai.mail.HtmlEmail;
import org.mentawai.mail.Letter;
import org.mentawai.mail.VelocityLetter;
import ro.agitman.moe.model.User;
import ro.agitman.moe.service.EmailService;

/**
 * Created by d-uu31cq on 22.07.2016.
 */
public class EmailServiceImpl implements EmailService{
    @Override
    public void sendRequestNewPassword() {

    }

    @Override
    public void sendAccountCreated() {

    }

    @Override
    public void sendAccountEnabled() {

    }

    @Override
    public void sendExamCreated(User user) {
        Letter letter = new VelocityLetter("examCreatedForProf.html");

        letter.setAttribute("stud", user.getName());

        try {
            String  text = letter.getText();
            System.out.println(text);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
