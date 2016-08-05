package ro.agitman.moe.web.action;

import org.mentawai.core.BaseAction;
import ro.agitman.moe.dao.*;
import ro.agitman.moe.model.*;
import ro.agitman.moe.service.ExamService;
import ro.agitman.moe.service.StudExamAnswerService;
import ro.agitman.moe.web.dto.StudentItemAnswerDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by d-uu31cq on 22.07.2016.
 */
public class StudentAction extends BaseAction {

    private final ExamService examService;
    private final ExamInstanceDao instanceDao;
    private final ExamItemDao examItemDao;
    private final StudExamInstanceDao studEXIDao;
    private final StudExamAnswerService examAnswerService;
    private final ExamItemAnswerDao answerDao;

    public StudentAction(ExamItemDao examItemDao, ExamService examService, ExamInstanceDao instanceDao, StudExamInstanceDao studExamInstanceDao, StudExamAnswerService studExamAnswerService, ExamItemAnswerDao answerDao) {
        this.examService = examService;
        this.instanceDao = instanceDao;
        this.examItemDao = examItemDao;
        this.studEXIDao = studExamInstanceDao;
        this.examAnswerService = studExamAnswerService;
        this.answerDao = answerDao;
    }

    public String startExam() {
        Integer examInstanceId = input().getInt("id");
        session().setAttribute("exiId", examInstanceId);

        ExamInstance instance = instanceDao.findById(examInstanceId);
        Exam exam = examService.findById(instance.getExamid());
        List<ExamItem> items = examItemDao.findByExamId(exam.getId());

        output().setValue("exam", exam);
        session.setAttribute("itemsNo", items.size());

        return SUCCESS;
    }

    public String launchExam() {

        if (isPost()) {
            User user = getSessionObj();
            Integer exiId = (Integer) session().getAttribute("exiId");
            session().removeAttribute("exiId");
            ExamInstance instance = instanceDao.findById(exiId);

            StudentExamInstance studentExamInstance = new StudentExamInstance();
            studentExamInstance.setStudid(user.getId());
            studentExamInstance.setExamid(instance.getId());
            studentExamInstance.setStatus(1);
            studentExamInstance = studEXIDao.insert(studentExamInstance);

            session().setAttribute("studExi", studentExamInstance.getId());

            return CREATED;

        } else {
            if (isGet()) {

                User user = getSessionObj();
                Integer studExiId = (Integer) session().getAttribute("studExi");
                StudentExamInstance studExi = studEXIDao.findById(studExiId);

//              todo make a query to optimise this
                List<ExamItem> items = examItemDao.findByExamId(studExi.getExamid());
                List<StudentExamAnswer> studAnswers = examAnswerService.findByStudent(user.getId());

                ExamItem chosenItem = null;
                for (ExamItem item : items) {
                    boolean found = false;
                    for (StudentExamAnswer answer : studAnswers) {
                        if (item.getId().equals(answer.getExamItemId())) {
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        chosenItem = item;
                        break;
                    }
                }

                if (chosenItem != null) {
                    Map<Integer, String> answers = new HashMap<>();

                    answerDao.findForItem(chosenItem.getId()).forEach(a -> {
                        answers.put(a.getId(), a.getValue());
                    });
                    session().setAttribute("itemId", chosenItem.getId());
                    output().setValue("item", chosenItem);
                    output().setValue("answers", answers);
                    output().setValue("itemIndex", studAnswers.size() + 1);
                }

                session().setAttribute("isLast", items.size() == (studAnswers.size() + 1));
            }
        }

        return SUCCESS;
    }

    public String saveAnswer() {

        User user = getSessionObj();
        Boolean skip = input().getBoolean("skip");
        Boolean isLast = (Boolean) session().getAttribute("isLast");
        Integer studExi = (Integer) session().getAttribute("studExi");
        Integer itemId = (Integer) session().getAttribute("itemId");

        session().removeAttribute("itemId");
        session().removeAttribute("isLast");

        examAnswerService.createAnswer(input().getValue("studAnswer"), skip, user.getId(), studExi, itemId, isLast);

        return  isLast ? SUCCESS : ADD;
    }

    public String viewResults() {

        Integer studExi = (Integer) session().getAttribute("studExi");
        List<StudentItemAnswerDTO> results = examAnswerService.loadStudentItemAsnwers(studExi);

        output().setValue("results", results);

        return SUCCESS;
    }
}
