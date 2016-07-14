package ro.agitman.moe.web;

import org.mentabean.DBTypes;
import org.mentabean.jdbc.MySQLBeanSession;
import org.mentabean.jdbc.PostgreSQLBeanSession;
import org.mentabean.util.SQLUtils;
import org.mentawai.ajax.renderer.JsonRenderer;
import org.mentawai.core.ApplicationManager;
import org.mentawai.core.Context;
import org.mentawai.core.Props;
import org.mentawai.db.BoneCPConnectionHandler;
import org.mentawai.db.ConnectionHandler;
import org.mentawai.filter.AuthenticationFilter;
import org.mentawai.filter.OVFilter;
import org.mentawai.filter.VOFilter;
import org.mentawai.mail.Email;
import ro.agitman.moe.custom.MyBooleanStringType;
import ro.agitman.moe.dao.ExamDao;
import ro.agitman.moe.dao.ExamItemAnswerDao;
import ro.agitman.moe.dao.ExamItemDao;
import ro.agitman.moe.dao.UserDao;
import ro.agitman.moe.dao.impl.ExamDaoImpl;
import ro.agitman.moe.dao.impl.ExamItemAnswerDaoImpl;
import ro.agitman.moe.dao.impl.ExamItemDaoImpl;
import ro.agitman.moe.dao.impl.UserDaoImpl;
import ro.agitman.moe.model.*;
import ro.agitman.moe.web.action.*;
import ro.agitman.moe.web.filter.PerformanceMonitoringFilter;

import java.sql.Timestamp;

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

        action("/home", HomeAction.class)
                .filter(new AuthenticationFilter())
                .on(SUCCESS, fwd("/jsp/home.jsp"));

        action("/ProfHome", ProfHomeAction.class, "newExam")
                .authorize("PROFESOR")
                .on(SUCCESS, fwd("/jsp/profEditExam.jsp"));
        action("/ProfHome", ProfHomeAction.class, "editExam")
                .authorize("PROFESOR")
                .on(SUCCESS, fwd("/jsp/profEditExam.jsp"));
        action("/ProfHome", ProfHomeAction.class, "saveExam")
                .authorize("PROFESOR")
                .filter(new VOFilter("exam", Exam.class, "exam"))
                .on(SUCCESS, redir("/home.m"));

        action("/ProfHome", ProfHomeAction.class, "addItemsRedir")
                .authorize("PROFESOR")
                .on(SUCCESS, redir("/ProfHome.addItems.m"));
        action("/ProfHome", ProfHomeAction.class, "addItemsAnswer")
                .authorize("PROFESOR")
                .filter(new VOFilter("answer", ExamItemAnswer.class, "answer"))
                .on(SUCCESS, redir("/ProfHome.addItems.m"));
        action("/ProfHome", ProfHomeAction.class, "editItem")
                .authorize("PROFESOR")
                .on(AJAX, ajax(new JsonRenderer()))
                .on(SUCCESS, redir("/ProfHome.addItems.m"));
        action("/ProfHome", ProfHomeAction.class, "deleteItem")
                .authorize("PROFESOR")
                .on(SUCCESS, redir("/ProfHome.addItems.m"));
        action("/ProfHome", ProfHomeAction.class, "removeEditItem")
                .authorize("PROFESOR")
                .on(SUCCESS, redir("/ProfHome.addItems.m"));
        action("/ProfHome", ProfHomeAction.class, "addItems")
                .authorize("PROFESOR")
                .filter(new VOFilter("examItem", ExamItem.class, "item"))
                .on(SUCCESS, fwd("/jsp/profAddItems.jsp"))
                .on(CREATED, redir("/ProfHome.addItems.m"));




        action("/EditUser", AdminHomeAction.class, "editUser")
                .authorize("ADMIN")
                .on(SUCCESS, fwd("/jsp/adminEditUser.jsp"));
        action("/EditUser", AdminHomeAction.class, "newUser")
                .authorize("ADMIN")
                .on(SUCCESS, fwd("/jsp/adminEditUser.jsp"));
        action("/EditUser", AdminHomeAction.class, "saveUser")
                .authorize("ADMIN")
                .filter(new VOFilter("user", User.class, "user"))
                .on(SUCCESS, redir("/home.m"));

        action("/Login", LoginAction.class)
                .on(SUCCESS, redir("/home.m"))
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
                .on(SUCCESS, redir("/home.m"))
                .on(ERROR, redir("/jsp/confirmPassword.jsp"));
    }

    @Override
    public void loadFilters() {

        filter(new AuthenticationFilter());
        filter(new PerformanceMonitoringFilter());
        on(LOGIN, redir("/jsp/index.jsp"));
    }

    @Override
    public void setupIoC() {

        ioc("beanSession", PostgreSQLBeanSession.class);

        ioc(UserDao.class, UserDaoImpl.class);
        ioc(ExamDao.class, ExamDaoImpl.class);
        ioc(ExamItemDao.class, ExamItemDaoImpl.class);
        ioc(ExamItemAnswerDao.class, ExamItemAnswerDaoImpl.class);
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

        bean(VerificationToken.class, "verification_token")
                .pk("id", DBTypes.SEQUENCE)
                .field("token", DBTypes.STRING)
                .field("verified", DBTypes.INTEGER)
                .field("userid", DBTypes.INTEGER)
                .field("expiredate", DBTypes.TIMESTAMP)
                .field("datecreated", DBTypes.NOW_ON_INSERT_TIMESTAMP);

        bean(StudentExamAnswer.class, "student_exam_answers")
                .pk("id", DBTypes.SEQUENCE)
                .field("studentExamInstanceId", DBTypes.INTEGER)
                .field("examItemId", DBTypes.INTEGER)
                .field("ownerId", DBTypes.INTEGER)
                .field("value", DBTypes.STRING)
                .field("datecreated", DBTypes.NOW_ON_INSERT_TIMESTAMP);

        bean(StudentExamInstance.class, "student_exam_instances")
                .pk("id", DBTypes.SEQUENCE)
                .field("examid", DBTypes.INTEGER)
                .field("studid", DBTypes.INTEGER)
                .field("status", DBTypes.INTEGER)
                .field("dateupdated", DBTypes.TIMESTAMP)
                .field("datecreated", DBTypes.NOW_ON_INSERT_TIMESTAMP);

        bean(ExamItemAnswer.class, "exam_item_answers")
                .pk("id", DBTypes.SEQUENCE)
                .field("correct", new MyBooleanStringType())
                .field("value", DBTypes.STRING)
                .field("itemid", DBTypes.INTEGER)
                .field("datecreated", DBTypes.NOW_ON_INSERT_TIMESTAMP);

        bean(ExamItem.class, "exam_items")
                .pk("id", DBTypes.SEQUENCE)
                .field("assertion", DBTypes.STRING)
                .field("difficulty", DBTypes.INTEGER)
                .field("points", DBTypes.LONG)
                .field("type", DBTypes.INTEGER)
                .field("examid", DBTypes.INTEGER)
                .field("datecreated", DBTypes.NOW_ON_INSERT_TIMESTAMP);

        bean(ExamInstance.class, "exam_instances")
                .pk("id", DBTypes.SEQUENCE)
                .field("name", DBTypes.STRING)
                .field("status", DBTypes.INTEGER)
                .field("startdate", DBTypes.TIMESTAMP)
                .field("enddate", DBTypes.TIMESTAMP)
                .field("points", DBTypes.LONG)
                .field("examid", DBTypes.INTEGER)
                .field("egroupid", DBTypes.INTEGER)
                .field("datecreated", DBTypes.NOW_ON_INSERT_TIMESTAMP);

        bean(ExamGroupUser.class, "exam_group_user")
                .pk("id", DBTypes.SEQUENCE)
                .field("groupid", DBTypes.INTEGER)
                .field("studentid", DBTypes.INTEGER)
                .field("datecreated", DBTypes.NOW_ON_INSERT_TIMESTAMP);

        bean(ExamGroup.class, "exam_groups")
                .pk("id", DBTypes.SEQUENCE)
                .field("name", DBTypes.STRING)
                .field("owner", DBTypes.INTEGER)
                .field("datecreated", DBTypes.NOW_ON_INSERT_TIMESTAMP);

        bean(Exam.class, "exams")
                .pk("id", DBTypes.SEQUENCE)
                .field("name", DBTypes.STRING)
                .field("owner", DBTypes.INTEGER)
                .field("points", DBTypes.LONG)
                .field("difficulty", DBTypes.INTEGER)
                .field("datecreated", DBTypes.NOW_ON_INSERT_TIMESTAMP);

        bean(User.class, "users")
                .pk("id", DBTypes.SEQUENCE)
                .field("email", DBTypes.STRING)
                .field("name", DBTypes.STRING)
                .field("firstname", DBTypes.STRING)
                .field("lastname", DBTypes.STRING)
                .field("password", DBTypes.STRING)
                .field("dateOfBirth", DBTypes.DATE)
                .field("enabled", new MyBooleanStringType())
                .field("role", DBTypes.STRING)
                .field("datecreated", DBTypes.NOW_ON_INSERT_TIMESTAMP);
    }
}
