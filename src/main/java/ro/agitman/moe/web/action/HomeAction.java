package ro.agitman.moe.web.action;

import org.mentawai.action.BaseLoginAction;
import org.mentawai.core.BaseAction;
import ro.agitman.moe.dao.*;
import ro.agitman.moe.model.*;
import ro.agitman.moe.service.ExamService;
import ro.agitman.moe.web.dto.ExamInstanceDTO;

import java.util.List;

/**
 * Created by edi on 7/10/2016.
 */
public class HomeAction extends BaseAction {

    private final UserDao userDao;
    private final ExamService examService;
    private final ExamGroupDao examGroupDao;
    private final ExamInstanceDao instanceDao;
    private final StudExamInstanceDao studEXIDao;

    public HomeAction(UserDao userDao, ExamService examService, ExamGroupDao examGroupDao, ExamInstanceDao instanceDao, StudExamInstanceDao studEXIDao) {
        this.userDao = userDao;
        this.examService = examService;
        this.examGroupDao = examGroupDao;
        this.instanceDao = instanceDao;
        this.studEXIDao = studEXIDao;
    }

    public String execute() {

        if (isGet()) {
            String role = getRole();
            if ("ADMIN".equals(role)) {
                output().setValue("users", userDao.findAll());
            }

            if ("PROFESOR".equals(role)) {

                User user = getSessionObj();
                List<Exam> exams = examService.findForOwner(user.getId());
                List<ExamGroup> examGroups = examGroupDao.findByOwner(user.getId());
                List<ExamInstanceDTO> instances = instanceDao.findByOwnerToDTO(user.getId());

                output().setValue("concepts", exams);
                output().setValue("groups", examGroups);
                output().setValue("instances", instances);
            }

            if ("STUDENT".equals(role)) {
                User user = getSessionObj();

                // if student is in the middle of an exam, continue it
                StudentExamInstance sexi = studEXIDao.findActiveByOwner(user.getId());
                if (sexi != null) {
                    session().setAttribute("studExi", sexi.getId());
                    return EDIT;
                }

                List<ExamInstance> instances = instanceDao.findByStudent(user.getId());
                List<StudentExamInstance> studInstances = studEXIDao.findByOwner(user.getId());

                for(ExamInstance instance : instances){
                    for(StudentExamInstance studInstance : studInstances){
                        //examen in derulare, dar studentul a terminat examenul
                        if(studInstance.getExamid().equals(instance.getId()) && instance.getStatus().equals(2)){
                            instance.setStatus(4);
                        }
                    }
                }

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
