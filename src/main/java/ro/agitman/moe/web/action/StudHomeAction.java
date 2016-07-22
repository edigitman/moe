package ro.agitman.moe.web.action;

import org.mentawai.core.BaseAction;
import ro.agitman.moe.dao.ExamDao;
import ro.agitman.moe.dao.ExamInstanceDao;
import ro.agitman.moe.dao.ExamItemDao;
import ro.agitman.moe.model.Exam;
import ro.agitman.moe.model.ExamInstance;
import ro.agitman.moe.model.ExamItem;

import java.util.List;

/**
 * Created by d-uu31cq on 22.07.2016.
 */
public class StudHomeAction extends BaseAction {

    private final ExamDao examDao;
    private final ExamInstanceDao instanceDao;
    private final ExamItemDao examItemDao;

    public StudHomeAction(ExamItemDao examItemDao, ExamDao examDao, ExamInstanceDao instanceDao) {
        this.examDao = examDao;
        this.instanceDao = instanceDao;
        this.examItemDao = examItemDao;
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

        // todo create student exam instance
        // put first item on output
        return SUCCESS;
    }

    public String saveAnswer() {

        // todo save the answer from student given an item

        return SUCCESS;
    }

    public String nextItem() {

        // todo save the answer from student given an item

        return SUCCESS;
    }




}
