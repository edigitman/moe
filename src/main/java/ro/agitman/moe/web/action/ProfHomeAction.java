package ro.agitman.moe.web.action;

import org.mentawai.core.BaseAction;
import ro.agitman.moe.dao.ExamDao;
import ro.agitman.moe.dao.ExamItemAnswerDao;
import ro.agitman.moe.dao.ExamItemDao;
import ro.agitman.moe.model.Exam;
import ro.agitman.moe.model.ExamItem;
import ro.agitman.moe.model.ExamItemAnswer;
import ro.agitman.moe.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by edi on 7/11/2016.
 */
public class ProfHomeAction extends BaseAction {

    private final List<String> diffs = new ArrayList<String>(Arrays.asList("USOR", "MEDIU", "GREU"));
    private final List<String> examItemType = new ArrayList<String>(Arrays.asList("Selectie unica", "Selectie multipla", "Text liber"));

    private final ExamDao examDao;
    private final ExamItemDao examItemDao;
    private final ExamItemAnswerDao answerDao;

    public ProfHomeAction(ExamDao examDao, ExamItemDao examItemDao, ExamItemAnswerDao answerDao) {
        this.examDao = examDao;
        this.examItemDao = examItemDao;
        this.answerDao = answerDao;
    }

    public String newExam() {
        output.setValue("difficulties", diffs);
        return SUCCESS;
    }

    public String addItemsRedir() {
        Integer examId = input.getInt("id");

        if (examId < 1000) {
            return ERROR;
        }

        session().setAttribute("examId", examId);

        return SUCCESS;
    }

    public String addItemsAnswer() {

        ExamItemAnswer answer = (ExamItemAnswer) input.getValue("answer");
        Integer itemId = (Integer) input.getValue("item.id");

        answer.setItemid(itemId);

        answerDao.save(answer);

        return SUCCESS;
    }

    public String addItems() {

        if (isPost()) {
//            save and redir
            ExamItem examItem = (ExamItem) input.getValue("examItem");
            Integer examId = (Integer) session().getAttribute("examId");
            examItem.setExamid(examId);

            examItem = examItemDao.insert(examItem);
            Exam exam = examDao.findById(examId);
            if (exam.getPoints() == null) {
                exam.setPoints(examItem.getPoints());
            } else {
                exam.setPoints(examItem.getPoints() + exam.getPoints());
            }
            examDao.save(exam);

            session().setAttribute("examItemId", examItem.getId());

            return CREATED;
        } else {
            if (isGet()) {
                Integer examId = (Integer) session().getAttribute("examId");
                Integer examItemId = (Integer) session().getAttribute("examItemId");
                Exam exam = examDao.findById(examId);
                if (exam == null) {
                    return ERROR;
                }

                List<ExamItem> examItems = examItemDao.findByExamId(examId);

                if (examItemId != null) {
                    for (ExamItem ei : examItems) {
                        if (ei.getId().equals(examItemId)) {
                            output.setValue("item", ei);
                            output.setValue("answers", answerDao.findForItem(examItemId));
                            break;
                        }
                    }
                } else {
                    output.setValue("item", new ExamItem());
                }

                output.setValue("exam", exam);
                output.setValue("examItems", examItems);
                output.setValue("itemTypes", examItemType);
            }
        }
        return SUCCESS;
    }

    public String editExam() {
        Integer examId = input.getInt("id");

        if (examId < 1000) {
            return ERROR;
        }

        Exam exam = examDao.findById(examId);
        if (exam == null) {
            return ERROR;
        }

        output.setValue("exam", exam);
        output.setValue("difficulties", diffs);
        return SUCCESS;
    }

    public String saveExam() {
        Exam exam = (Exam) input.getValue("exam");

        if (exam == null) {
            return ERROR;
        }

        User user = getSessionObj();
        exam.setOwner(user.getId());
        if (exam.getId() == null) {
            examDao.insert(exam);
        } else {
            examDao.save(exam);
        }

        return SUCCESS;

    }
}
