package ro.agitman.moe.web.action;

import org.mentawai.core.BaseAction;
import ro.agitman.moe.dao.*;
import ro.agitman.moe.model.*;
import ro.agitman.moe.service.ExamService;

import java.util.Arrays;
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
    private final StudExamAnswerDao examAnswerDao;
    private final ExamItemAnswerDao answerDao;

    public StudentAction(ExamItemDao examItemDao, ExamService examService, ExamInstanceDao instanceDao, StudExamInstanceDao studExamInstanceDao, StudExamAnswerDao studExamAnswerDao, ExamItemAnswerDao answerDao) {
        this.examService = examService;
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
        Boolean isLast = (Boolean) session().getAttribute("isLast");
        Integer studExi = (Integer) session().getAttribute("studExi");
        Integer itemId = (Integer) session().getAttribute("itemId");

        ExamItem item = examItemDao.findById(itemId);
        List<ExamItemAnswer> answers = answerDao.findForItem(itemId);

        StudentExamAnswer answer = new StudentExamAnswer();
        answer.setOwnerId(user.getId());
        answer.setExamItemId(itemId);
        answer.setStudentExamInstanceId(studExi);

        Object studAnswerObj = input().getValue("studAnswer");
        StringBuilder actualAnswer = new StringBuilder();

        // in case of free text selected
        if (item.getType().equals(3)) {
            actualAnswer.append(studAnswerObj.toString());
            answer.setSolvable(false);
        } else {
            answer.setSolvable(true);
            // in case of multiple options, checkbox
            if (studAnswerObj instanceof String[]) {
                String[] studAnswerArr = (String[]) studAnswerObj;
                answer.setRawAnswer(buildAnswerArray(studAnswerArr));

                for (String s : studAnswerArr) {
                    ExamItemAnswer foundAnswer = matchWithAnswer(s, answers);
                    actualAnswer.append(foundAnswer.getValue()).append("#$");
                }
            } else {
                // in case only one answer selected, radiobox
                answer.setRawAnswer(studAnswerObj.toString());

                ExamItemAnswer foundAnswer = matchWithAnswer(studAnswerObj, answers);
                actualAnswer.append(foundAnswer.getValue()).append("#$");
            }

        }

        session().removeAttribute("itemId");
        session().removeAttribute("isLast");

        answer.setValue(actualAnswer.toString());
        examAnswerDao.insert(answer);

        if (isLast) {
//          mark exam as completed by student
            StudentExamInstance instance = studEXIDao.findById(studExi);
            instance.setStatus(2);
            studEXIDao.save(instance);

            session().removeAttribute("studExi");
            return SUCCESS;
        }

        return ADD;
    }

    private String buildAnswerArray(String[] keys){
        StringBuilder sb = new StringBuilder();
        for(String k : keys){
            sb.append(k).append("-");
        }

        return sb.toString();
    }

    private ExamItemAnswer matchWithAnswer(Object id, List<ExamItemAnswer> answers) {
        for (ExamItemAnswer itemAnswer : answers) {
            if (itemAnswer.getId().equals(Integer.valueOf(id.toString()))) {
                return itemAnswer;
            }
        }
        throw new IllegalStateException("Answer not found for id " + id);
    }

    public String viewResults() {
        return SUCCESS;
    }
}
