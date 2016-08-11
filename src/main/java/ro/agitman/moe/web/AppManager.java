package ro.agitman.moe.web;

import org.mentabean.DBTypes;
import org.mentabean.jdbc.PostgreSQLBeanSession;
import org.mentawai.ajax.renderer.JsonRenderer;
import org.mentawai.core.ActionConfig;
import org.mentawai.core.ApplicationManager;
import org.mentawai.core.Context;
import org.mentawai.core.Props;
import org.mentawai.db.BoneCPConnectionHandler;
import org.mentawai.db.ConnectionHandler;
import org.mentawai.filter.AuthenticationFilter;
import org.mentawai.filter.VOFilter;
import org.mentawai.mail.Email;
import ro.agitman.moe.dao.*;
import ro.agitman.moe.dao.impl.*;
import ro.agitman.moe.model.*;
import ro.agitman.moe.service.EmailService;
import ro.agitman.moe.service.ExamGroupService;
import ro.agitman.moe.service.ExamService;
import ro.agitman.moe.service.StudExamAnswerService;
import ro.agitman.moe.service.impl.EmailServiceImpl;
import ro.agitman.moe.service.impl.ExamGroupServiceImpl;
import ro.agitman.moe.service.impl.ExamServiceImpl;
import ro.agitman.moe.service.impl.StudExamAnswerServiceImpl;
import ro.agitman.moe.web.action.*;
import ro.agitman.moe.web.filter.PerformanceMonitoringFilter;

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
                .on(EDIT, redir("/stud.launchExam.m"))
                .on(SUCCESS, fwd("/jsp/home.jsp"));
//****************************************************************
//---------- PROFESSOR ACTIONS -----------------------------------

//````````````````````````````````````````````````````````````````
//--------------- ACTIONS RELATED TO EXAM ------------------------
        action("/prof", ProfessorAction.class, "saveExam")
                .authorize("PROFESOR")
                .filter(new VOFilter("exam", Exam.class, "exam"))
                .on(SUCCESS, redir("/home.m"));
        action("/prof", ProfessorAction.class, "updateExam")
                .authorize("PROFESOR")
                .on(SUCCESS, ajax(new JsonRenderer()));

//````````````````````````````````````````````````````````````````
//--------------- ACTIONS RELATED TO EXAM ITEM -------------------
        action("/prof", ProfessorAction.class, "addItemsRedir")
                .authorize("PROFESOR")
                .on(SUCCESS, redir("/prof.addItems.m"));
        action("/prof", ProfessorAction.class, "addItemsAnswer")
                .authorize("PROFESOR")
                .on(SUCCESS, ajax(new JsonRenderer()));
        action("/prof", ProfessorAction.class, "getAllAnswers")
                .authorize("PROFESOR")
                .on(SUCCESS, ajax(new JsonRenderer()));
        action("/prof", ProfessorAction.class, "editItem")
                .authorize("PROFESOR")
                .on(AJAX, ajax(new JsonRenderer()))
                .on(SUCCESS, redir("/prof.addItems.m"));
        action("/prof", ProfessorAction.class, "deleteItem")
                .authorize("PROFESOR")
                .on(SUCCESS, redir("/prof.addItems.m"));
        action("/prof", ProfessorAction.class, "removeEditItem")
                .authorize("PROFESOR")
                .on(SUCCESS, redir("/prof.addItems.m"));
        action("/prof", ProfessorAction.class, "addItems")
                .authorize("PROFESOR")
                .filter(new VOFilter("examItem", ExamItem.class, "item"))
                .on(SUCCESS, fwd("/jsp/prof/profAddItems.jsp"))
                .on(CREATED, redir("/prof.addItems.m"));
        action("/prof", ProfessorAction.class, "deleteAnswer")
                .authorize("PROFESOR")
                .on(SUCCESS, ajax(new JsonRenderer()));

//````````````````````````````````````````````````````````````````
//--------------- ACTIONS RELATED TO GROUP -------------------
        action("/prof", ProfessorAction.class, "saveGroup")
                .authorize("PROFESOR")
                .filter(new VOFilter("group", ExamGroup.class, "group"))
                .on(SUCCESS, redir("/home.m"));
        action("/prof", ProfessorAction.class, "editGroup")
                .authorize("PROFESOR")
                .on(SUCCESS, ajax(new JsonRenderer()));
        action("/prof", ProfessorAction.class, "addStudsRedir")
                .authorize("PROFESOR")
                .on(SUCCESS, redir("/prof.addStuds.m"));
        action("/prof", ProfessorAction.class, "addStuds")
                .authorize("PROFESOR")
                .on(SUCCESS, fwd("/jsp/prof/profAddStuds.jsp"))
                .on(CREATED, redir("/prof.addStuds.m"));
        action("/prof", ProfessorAction.class, "deleteStuds")
                .authorize("PROFESOR")
                .on(SUCCESS, redir("/prof.addStuds.m"));

//````````````````````````````````````````````````````````````````
//--------------- ACTIONS RELATED TO EXAM INSTANCE ---------------
        action("/prof", ProfessorAction.class, "addExamInst")
                .authorize("PROFESOR")
                .filter(new VOFilter("exi", ExamInstance.class, "exi"))
                .on(CREATED, redir("/home.m"));
        action("/prof", ProfessorAction.class, "changeInstanceStatus")
                .authorize("PROFESOR")
                .on(SUCCESS, redir("/home.m"));

        action("/prof", ProfessorAction.class, "getExam")
                .authorize("PROFESOR")
                .on(SUCCESS, ajax(new JsonRenderer()));
        action("/prof", ProfessorAction.class, "getAnswersForItem")
                .authorize("PROFESOR")
                .on(SUCCESS, ajax(new JsonRenderer()));
        action("/prof", ProfessorAction.class, "getStudentAnswersForItem")
                .authorize("PROFESOR")
                .on(SUCCESS, ajax(new JsonRenderer()));

        action("/prof", ProfessorAction.class, "reviewExam")
                .authorize("PROFESOR")
                .on(SUCCESS, fwd("/jsp/prof/profReviewExam.jsp"));
        action("/prof", ProfessorAction.class, "viewExamItemResult")
                .authorize("PROFESOR")
                .on(SUCCESS, ajax(new JsonRenderer()));
        action("/prof", ProfessorAction.class, "solveExamInstance")
                .authorize("PROFESOR")
                .on(SUCCESS, redir("/prof.reviewExam.m"));
        action("/prof", ProfessorAction.class, "closeExamInstance")
                .authorize("PROFESOR")
                .on(SUCCESS, redir("/home.m"));
        action("/prof", ProfessorAction.class, "markAnswer")
                .authorize("PROFESOR")
                .on(SUCCESS, ajax(new JsonRenderer()));

        action("/prof", ProfessorAction.class, "viewInstance")
                .authorize("PROFESOR")
                .on(SUCCESS, fwd("/jsp/prof/profViewExam.jsp"));

//****************************************************************
//---------- STUDENT ACTIONS ---------------------------------------
//````````````````````````````````````````````````````````````````
//--------------- ACTIONS TO START EXAM ------------------------

        action("/stud", StudentAction.class, "startExam")
                .authorize("STUDENT")
                .on(SUCCESS, fwd("/jsp/stud/studTakeExam.jsp"));
        action("/stud", StudentAction.class, "launchExam")
                .authorize("STUDENT")
                .on(CREATED, redir("/stud.launchExam.m"))
                .on(SUCCESS, fwd("/jsp/stud/studItemview.jsp"));
        action("/stud", StudentAction.class, "saveAnswer")
                .authorize("STUDENT")
                .on(SUCCESS, redir("/stud.viewResults.m"))
                .on(ADD, redir("/stud.launchExam.m"));
        action("/stud", StudentAction.class, "viewResults")
                .authorize("STUDENT")
                .on(SUCCESS, fwd("/jsp/stud/studExamSummary.jsp"));

//****************************************************************
//---------- ADMIN ACTIONS ---------------------------------------
//````````````````````````````````````````````````````````````````
//--------------- ACTIONS TO MANAGE USERS ------------------------
        action("/admin", AdminAction.class, "editUser")
                .authorize("ADMIN")
                .on(SUCCESS, fwd("/jsp/admin/adminEditUser.jsp"));
        action("/admin", AdminAction.class, "newUser")
                .authorize("ADMIN")
                .on(SUCCESS, fwd("/jsp/admin/adminEditUser.jsp"));
        action("/admin", AdminAction.class, "saveUser")
                .authorize("ADMIN")
                .filter(new VOFilter("user", User.class, "user"))
                .on(SUCCESS, redir("/home.m"));

        action("/Login", LoginAction.class)
                .on(SUCCESS, redir("/home.m"))
                .on(ERROR, fwd("/jsp/index.jsp"));
        action("/Logout", LogoutAction.class)
                .on(SUCCESS, redir("/jsp/index.jsp"));
//        request new password
        action("/RequestRenewPassword", RenewPasswordAction.class, "requestNewPwd")
                .bypassAuthentication()
                .on(SUCCESS, redir("/jsp/index.jsp"))
                .on(ERROR, redir("/jsp/recoverPassword.jsp"));
//        arrive here from email link to put the new password
        action("/ConfirmRenewPassword", RenewPasswordAction.class, "confirm")
                .bypassAuthentication()
                .on(SUCCESS, fwd("/jsp/confirmPassword.jsp"))
                .on(ERROR, redir("/jsp/index.jsp"));
//        save the new password
        action("/ValidateRenewPassword", RenewPasswordAction.class, "validate")
                .bypassAuthentication()
                .on(SUCCESS, redir("/home.m"))
                .on(ERROR, redir("/jsp/confirmPassword.jsp"));
    }

    @Override
    public void loadFilters() {

        filter(new AuthenticationFilter());
        filter(new PerformanceMonitoringFilter());

        on(LOGIN, redir("/jsp/index.jsp"));
        on(ACCESSDENIED, redir("/jsp/error/accessDenied.jsp"));
    }

    @Override
    public void setupIoC() {

        ioc("beanSession", PostgreSQLBeanSession.class);

        ioc(UserDao.class, UserDaoImpl.class);
        ioc(ExamDao.class, ExamDaoImpl.class);
        ioc(ExamItemDao.class, ExamItemDaoImpl.class);
        ioc(ExamItemAnswerDao.class, ExamItemAnswerDaoImpl.class);
        ioc(ExamGroupDao.class, ExamGroupDaoImpl.class);
        ioc(ExamGroupUserDao.class, ExamGroupUserDaoImpl.class);
        ioc(ExamInstanceDao.class, ExamInstanceDaoImpl.class);
        ioc(StudExamInstanceDao.class, StudExamInstanceDaoImpl.class);
        ioc(StudExamAnswerDao.class, StudExamAnswerDaoImpl.class);
        ioc(VerificationTokenDao.class, VerificationTokenDaoImpl.class);

//        services ----------------------------------------------------
        ioc(EmailService.class, EmailServiceImpl.class);
        ioc(ExamService.class, ExamServiceImpl.class);
        ioc(ExamGroupService.class, ExamGroupServiceImpl.class);
        ioc(StudExamAnswerService.class, StudExamAnswerServiceImpl.class);
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
                .field("rawAnswer", DBTypes.STRING)
                .field("solvable", DBTypes.BOOLEAN)
                .field("correct", DBTypes.BOOLEAN)
                .field("reviewed", DBTypes.BOOLEAN)
                .field("points", DBTypes.INTEGER)
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
                .field("correct", DBTypes.BOOLEAN)
                .field("value", DBTypes.STRING)
                .field("itemid", DBTypes.INTEGER)
                .field("datecreated", DBTypes.NOW_ON_INSERT_TIMESTAMP);

        bean(ExamItem.class, "exam_items")
                .pk("id", DBTypes.SEQUENCE)
                .field("title", DBTypes.STRING)
                .field("assertion", DBTypes.STRING)
                .field("difficulty", DBTypes.INTEGER)
                .field("points", DBTypes.INTEGER)
                .field("type", DBTypes.INTEGER)
                .field("ord", DBTypes.INTEGER)
                .field("examid", DBTypes.INTEGER)
                .field("datecreated", DBTypes.NOW_ON_INSERT_TIMESTAMP);

        bean(ExamInstance.class, "exam_instances")
                .pk("id", DBTypes.SEQUENCE)
                .field("name", DBTypes.STRING)
                .field("status", DBTypes.INTEGER)
                .field("startdate", DBTypes.TIMESTAMP)
                .field("enddate", DBTypes.TIMESTAMP)
                .field("examid", DBTypes.INTEGER)
                .field("egroupid", DBTypes.INTEGER)
                .field("owner", DBTypes.INTEGER)
                .field("autoSolved", DBTypes.BOOLEAN)
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
                .field("students", DBTypes.INTEGER)
                .field("locked", DBTypes.BOOLEAN)
                .field("datecreated", DBTypes.NOW_ON_INSERT_TIMESTAMP);

        bean(Exam.class, "exams")
                .pk("id", DBTypes.SEQUENCE)
                .field("name", DBTypes.STRING)
                .field("owner", DBTypes.INTEGER)
                .field("points", DBTypes.INTEGER)
                .field("items", DBTypes.INTEGER)
                .field("difficulty", DBTypes.INTEGER)
                .field("locked", DBTypes.BOOLEAN)
                .field("datecreated", DBTypes.NOW_ON_INSERT_TIMESTAMP);

        bean(User.class, "users")
                .pk("id", DBTypes.SEQUENCE)
                .field("email", DBTypes.STRING)
                .field("name", DBTypes.STRING)
                .field("firstname", DBTypes.STRING)
                .field("lastname", DBTypes.STRING)
                .field("password", DBTypes.STRING)
                .field("dateOfBirth", DBTypes.DATE)
                .field("enabled", DBTypes.BOOLEAN)
                .field("role", DBTypes.STRING)
                .field("datecreated", DBTypes.NOW_ON_INSERT_TIMESTAMP);
    }
}
