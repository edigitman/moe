package ro.agitman.moe.web.action;

import org.mentawai.core.BaseAction;
import ro.agitman.moe.dao.ExamDao;
import ro.agitman.moe.dao.ExamItemDao;
import ro.agitman.moe.model.Exam;
import ro.agitman.moe.model.ExamItem;
import ro.agitman.moe.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by edi on 7/11/2016.
 */
public class ProfHomeAction extends BaseAction {

    private final List<String> diffs = new ArrayList<String>(Arrays.asList("USOR", "MEDIU", "GREU"));

    private final ExamDao examDao;
    private final ExamItemDao examItemDao;

    public ProfHomeAction(ExamDao examDao, ExamItemDao examItemDao) {
        this.examDao = examDao;
        this.examItemDao = examItemDao;
    }

    public String newExam() {
        output.setValue("difficulties", diffs);
        return SUCCESS;
    }

    public String addItemsRedir(){
        Integer examId = input.getInt("id");

        if (examId < 1000) {
            return ERROR;
        }

        session().setAttribute("examId", examId);

        return SUCCESS;
    }

    public String addItems() {

        if(isPost()){
//            save and redir
            ExamItem examItem = (ExamItem)input.getValue("examItem");
            Integer examId = (Integer)session().getAttribute("examId");
            examItem.setExamid(examId);

            examItemDao.insert(examItem);

            return CREATED;
        } else {
            if(isGet()){
                Integer examId = (Integer)session().getAttribute("examId");
                Exam exam = examDao.findById(examId);
                if (exam == null) {
                    return ERROR;
                }

                List<ExamItem> examItems = examItemDao.findByExamId(examId);

                output.setValue("exam", exam);
                output.setValue("examItems", examItems);

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
