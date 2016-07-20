package ro.agitman.moe.web.action;

import org.mentawai.action.BaseLoginAction;
import org.mentawai.core.BaseAction;
import ro.agitman.moe.dao.ExamDao;
import ro.agitman.moe.dao.ExamGroupDao;
import ro.agitman.moe.dao.ExamInstanceDao;
import ro.agitman.moe.dao.UserDao;
import ro.agitman.moe.model.Exam;
import ro.agitman.moe.model.ExamGroup;
import ro.agitman.moe.model.ExamInstance;
import ro.agitman.moe.model.User;

import java.util.List;
import java.util.Objects;

/**
 * Created by edi on 7/10/2016.
 */
public class HomeAction extends BaseAction {

    private final UserDao userDao;
    private final ExamDao examDao;
    private final ExamGroupDao examGroupDao;
    private final ExamInstanceDao instanceDao;

    public HomeAction(UserDao userDao, ExamDao examDao, ExamGroupDao examGroupDao, ExamInstanceDao instanceDao) {
        this.userDao = userDao;
        this.examDao = examDao;
        this.examGroupDao = examGroupDao;
        this.instanceDao = instanceDao;
    }

    public String execute() {

        if (isGet()) {
            if ("ADMIN".equals(getRole())) {
                output().setValue("users", userDao.findAll());
            }

            if ("PROFESOR".equals(getRole())) {

                User user = getSessionObj();
                List<Exam> exams = examDao.findForOwner(user.getId());
                List<ExamGroup> examGroups = examGroupDao.findByOwner(user.getId());
                List<ExamInstance> instances = instanceDao.findByOwner(user.getId());
                output().setValue("concepts", exams);
                output().setValue("groups", examGroups);
                output().setValue("instances", instances);
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
