package ro.agitman.moe.web.action;

import org.mentawai.action.BaseLoginAction;
import org.mentawai.core.BaseAction;
import ro.agitman.moe.dao.ExamDao;
import ro.agitman.moe.dao.UserDao;
import ro.agitman.moe.model.Exam;
import ro.agitman.moe.model.User;

import java.util.List;
import java.util.Objects;

/**
 * Created by edi on 7/10/2016.
 */
public class HomeAction extends BaseAction {

    private final UserDao userDao;
    private final ExamDao examDao;

    public HomeAction(UserDao userDao, ExamDao examDao) {
        this.userDao = userDao;
        this.examDao = examDao;
    }

    public String execute() {

        if (isGet()) {
            if ("ADMIN".equals(getRole())) {
                output.setValue("users", userDao.findAll());
            }

            if ("PROFESOR".equals(getRole())) {

                User user = getSessionObj();
                List<Exam> exams = examDao.findForOwner(user.getId());


                output.setValue("concepts", exams);
            }



            return SUCCESS;
        }

        return BLOCKED;
    }

    private String getRole() {
        List<Object> groups = (List<Object>) getSession().getAttribute(BaseLoginAction.GROUPS_KEY);

        if (groups != null && !groups.isEmpty()) {
            return groups.get(0).toString();
        }
        throw new IllegalStateException("Cannot get roles for user");
    }


}
