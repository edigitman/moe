package ro.agitman.moe.web;

import org.mentabean.DBTypes;
import org.mentabean.jdbc.MySQLBeanSession;
import org.mentabean.jdbc.PostgreSQLBeanSession;
import org.mentawai.core.ApplicationManager;
import org.mentawai.core.Context;
import org.mentawai.core.Props;
import org.mentawai.db.BoneCPConnectionHandler;
import org.mentawai.db.ConnectionHandler;
import org.mentawai.filter.AuthenticationFilter;
import org.mentawai.mail.Email;
import ro.agitman.moe.dao.UserDao;
import ro.agitman.moe.dao.impl.UserDaoImpl;
import ro.agitman.moe.model.Exam;
import ro.agitman.moe.model.ExamGroup;
import ro.agitman.moe.model.User;
import ro.agitman.moe.web.action.HelloAction;
import ro.agitman.moe.web.action.LoginAction;
import ro.agitman.moe.web.action.LogoutAction;
import ro.agitman.moe.web.action.RenewPasswordAction;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by edi on 7/6/2016.
 */
public class AppManager extends ApplicationManager {

    @Override
    public void init(Context context) {
        Props props = getProps();

        ////////////////////////////////////////////
        // TURN ON/OFF DEBUG MODE
        ////////////////////////////////////////////
        setDebugMode(props.getBoolean("debug_mode"));

        ///////////////////////////////////////////////////
        // TURN ON/OFF APP MANAGER AUTO-REDEPLOY FEATURE
        // OBS: Requires http://www.javarebel.com to work
        ///////////////////////////////////////////////////
        setReloadable(props.getBoolean("auto_reload"));

        //////////////////////////////////////////
        // FOR SENDING EMAIL
        //////////////////////////////////////////
        if (props.getBoolean("email.send_email")) {

            Email.setDefaultHostName(props.getString("email.host"));
            Email.setDefaultSslConnection(props.getBoolean("email.ssl"));
            Email.setDefaultPort(props.getInt("email.port"));

            if (props.getBoolean("email.use_authentication")) {
                Email.setDefaultAuthentication(props.getString("email.user"), props.getString("email.pass"));
            }

            Email.setDefaultFrom(props.getString("email.from_email"), props.getString("email.from_name"));
        } else {
            Email.setSendEmail(false);
        }
    }

    @Override
    public void loadLocales() {
        addLocales("ro_RO");
    }

    @Override
    public void loadActions() {

        action("/Login", LoginAction.class)
                .on(SUCCESS, redir("/jsp/home.jsp"))
                .on(ERROR, fwd("/jsp/index.jsp"));
        action("/Logout", LogoutAction.class)
                .on(SUCCESS, redir("/jsp/index.jsp"));
//        request new password
        action("/RequestRenewPassword", RenewPasswordAction.class, "request")
                .on(SUCCESS, redir("/jsp/index.jsp"))
                .on(ERROR, redir("/jsp/recoverPassword.jsp"));
//        arrive here from email link to put the new password
        action("/ConfirmRenewPassword", RenewPasswordAction.class, "confirm")
                .on(SUCCESS, redir("/jsp/confirmPassword.jsp"))
                .on(ERROR, redir("/jsp/index.jsp"));
//        save the new password
        action("/ValidateRenewPassword", RenewPasswordAction.class, "validate")
                .on(SUCCESS, redir("/jsp/home.jsp"))
                .on(ERROR, redir("/jsp/confirmPassword.jsp"));



    }

    @Override
    public void loadFilters() {

        filter(new AuthenticationFilter());
        on(LOGIN, redir("/jsp/index.jsp"));
    }

    @Override
    public void setupIoC() {

        ioc("beanSession", PostgreSQLBeanSession.class);

        ioc(UserDao.class, UserDaoImpl.class);
    }


    @Override
    public ConnectionHandler createConnectionHandler() {

        Props props = getProps();

        String driver = props.getString("jdbc.driver");
        String url = props.getString("jdbc.url");
        String user = props.getString("jdbc.user");
        String pass = props.getString("jdbc.pass");

        return new BoneCPConnectionHandler(driver, url, user, pass);
    }

    @Override
    public void loadBeans() {

        bean(ExamGroup.class, "verificationtoken")
                .pk("id", DBTypes.AUTOINCREMENT)
                .field("token", DBTypes.STRING)
                .field("verified", DBTypes.INTEGER)
                .field("userid", DBTypes.INTEGER)
                .field("expiredate", DBTypes.TIMESTAMP)
                .field("datecreated", DBTypes.NOW_ON_INSERT_TIMESTAMP);

        bean(ExamGroup.class, "studentexaminstances")
                .pk("id", DBTypes.AUTOINCREMENT)
                .field("examid", DBTypes.INTEGER)
                .field("studid", DBTypes.INTEGER)
                .field("status", DBTypes.INTEGER)
                .field("dateupdated", DBTypes.TIMESTAMP)
                .field("datecreated", DBTypes.NOW_ON_INSERT_TIMESTAMP);

        bean(ExamGroup.class, "examitemanswers")
                .pk("id", DBTypes.AUTOINCREMENT)
                .field("correct", DBTypes.INTEGER)
                .field("value", DBTypes.STRING)
                .field("itemid", DBTypes.INTEGER)
                .field("datecreated", DBTypes.NOW_ON_INSERT_TIMESTAMP);

        bean(ExamGroup.class, "examitems")
                .pk("id", DBTypes.AUTOINCREMENT)
                .field("assertion", DBTypes.STRING)
                .field("difficulty", DBTypes.INTEGER)
                .field("points", DBTypes.LONG)
                .field("type", DBTypes.INTEGER)
                .field("examid", DBTypes.INTEGER)
                .field("datecreated", DBTypes.NOW_ON_INSERT_TIMESTAMP);

        bean(ExamGroup.class, "examsinstance")
                .pk("id", DBTypes.AUTOINCREMENT)
                .field("name", DBTypes.STRING)
                .field("status", DBTypes.INTEGER)
                .field("startdate", DBTypes.TIMESTAMP)
                .field("enddate", DBTypes.TIMESTAMP)
                .field("points", DBTypes.LONG)
                .field("examid", DBTypes.INTEGER)
                .field("egroupid", DBTypes.INTEGER)
                .field("datecreated", DBTypes.NOW_ON_INSERT_TIMESTAMP);

        bean(ExamGroup.class, "examgroupuser")
                .pk("id", DBTypes.AUTOINCREMENT)
                .field("groupid", DBTypes.INTEGER)
                .field("studentid", DBTypes.INTEGER)
                .field("datecreated", DBTypes.NOW_ON_INSERT_TIMESTAMP);

        bean(ExamGroup.class, "examgroups")
                .pk("id", DBTypes.AUTOINCREMENT)
                .field("name", DBTypes.STRING)
                .field("owner", DBTypes.INTEGER)
                .field("datecreated", DBTypes.NOW_ON_INSERT_TIMESTAMP);

        bean(Exam.class, "exams")
                .pk("id", DBTypes.AUTOINCREMENT)
                .field("name", DBTypes.STRING)
                .field("owner", DBTypes.INTEGER)
                .field("difficulty", DBTypes.STRING)
                .field("datecreated", DBTypes.NOW_ON_INSERT_TIMESTAMP);

        bean(User.class, "users")
                .pk("id", DBTypes.AUTOINCREMENT)
                .field("email", DBTypes.STRING)
                .field("name", DBTypes.STRING)
                .field("firstname", DBTypes.STRING)
                .field("lastname", DBTypes.STRING)
                .field("password", DBTypes.STRING)
                .field("dateOfBirth", DBTypes.DATE)
                .field("enabled", DBTypes.INTEGER)
                .field("role", DBTypes.STRING)
                .field("datecreated", DBTypes.NOW_ON_INSERT_TIMESTAMP);
    }
}
