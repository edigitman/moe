package ro.agitman.moe.web.action;

import org.mentawai.core.BaseAction;
import ro.agitman.moe.dao.*;
import ro.agitman.moe.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by d-uu31cq on 22.07.2016.
 */
public class StudHomeAction extends BaseAction {

    private final ExamDao examDao;
    private final ExamInstanceDao instanceDao;
    private final ExamItemDao examItemDao;
    private final StudExamInstanceDao studEXIDao;
    private final StudExamAnswerDao examAnswerDao;
    private final ExamItemAnswerDao answerDao;

    public StudHomeAction(ExamItemDao examItemDao, ExamDao examDao, ExamInstanceDao instanceDao, StudExamInstanceDao studExamInstanceDao, StudExamAnswerDao studExamAnswerDao, ExamItemAnswerDao answerDao) {
        this.examDao = examDao;
        this.instanceDao = instanceDao;
        this.examItemDao = examItemDao;
        this.studEXIDao = studExamInstanceDao;
        this.examAnswerDao = studExamAnswerDao;
        this.answerDao = answerDao;
    }

    public String startExam() {
        Integer examInstanceId = input().getInt("id");
        session().setAttribute("exiId", examInstanceId);

        ExamInstance instance = instanceDao.findById(examInstanceId);
        Exam exam = examDao.findById(instance.getExamid());
        List<ExamItem> items = examItemDao.findByExamId(exam.getId());

        output().setValue("exam", exam);
        output().setValue("itemsNo", items.size());

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
                List<StudentExamAnswer> studAnswers = examAnswerDao.findByStudent(user.getId());

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

                    answerDao.findForItem(chosenItem.getId()).stream().forEach(a -> {
                        answers.put(a.getId(), a.getValue());
                    });
                    session().setAttribute("itemId", chosenItem.getId());
                    output().setValue("item", chosenItem);
                    output().setValue("answers", answers);
                }

                session().setAttribute("isLast", items.size() == (studAnswers.size() + 1));
            }
        }

        return SUCCESS;
    }

    public String saveAnswer() {

        User user = getSessionObj();
        Boolean isLast = (Boolean) session().getAttribute("isLast");
        Integer studExi = (Integer) session().getAttribute("studExi");
        Integer itemId = (Integer) session().getAttribute("itemId");
        Object studAnswerObj = input().getValue("studAnswer");
        if(studAnswerObj instanceof String[]){
            StringBuilder builder = new StringBuilder();
            for(String s : (String[])studAnswerObj) {
                builder.append(s).append(",");
            }
        }

        session().removeAttribute("itemId");
        session().removeAttribute("isLast");

        StudentExamAnswer answer = new StudentExamAnswer();
        answer.setOwnerId(user.getId());
        answer.setExamItemId(itemId);
        answer.setStudentExamInstanceId(studExi);
        answer.setValue(studAnswerObj.toString());

        examAnswerDao.insert(answer);

        if (isLast){
//          mark exam as completed by student
            StudentExamInstance instance = studEXIDao.findById(studExi);
            instance.setStatus(2);
            studEXIDao.save(instance);

            session().removeAttribute("studExi");
            return SUCCESS;
        }

        return ADD;
    }

    public String viewResults(){
        return SUCCESS;
    }
}
