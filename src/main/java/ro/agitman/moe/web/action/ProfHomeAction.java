package ro.agitman.moe.web.action;

import org.mentawai.core.BaseAction;
import ro.agitman.moe.dao.*;
import ro.agitman.moe.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by edi on 7/11/2016.
 */
public class ProfHomeAction extends BaseAction {

    private final Map<Integer, String> diffs;// = new ArrayList<String>(Arrays.asList("USOR", "MEDIU", "GREU"));
    private final Map<Integer, String> examItemType;// = new ArrayList<String>(Arrays.asList("Selectie unica", "Selectie multipla", "Text liber"));

    private final ExamDao examDao;
    private final ExamGroupDao examGroupDao;
    private final ExamItemDao examItemDao;
    private final ExamItemAnswerDao answerDao;
    private final UserDao userDao;

//    static initialization
    {
        examItemType = new HashMap<>();
        examItemType.put(1, "Selectie unica");
        examItemType.put(2, "Selectie multipla");
        examItemType.put(3, "Text liber");

        diffs = new HashMap<>();
        diffs.put(1, "Usor");
        diffs.put(2, "Mediu");
        diffs.put(3, "Greu");
    }

    public ProfHomeAction(UserDao userDao, ExamDao examDao, ExamGroupDao examGroupDao, ExamItemDao examItemDao, ExamItemAnswerDao answerDao) {
        this.userDao = userDao;
        this.examDao = examDao;
        this.examGroupDao = examGroupDao;
        this.examItemDao = examItemDao;
        this.answerDao = answerDao;
    }

//******************************************************
//---------------- ITEM ACTIONS ------------------------
    public String addItemsRedir() {
        Integer examId = input.getInt("id");

        if (examId < 1000) {
            return ERROR;
        }

        session().setAttribute("examId", examId);

        return SUCCESS;
    }

    public String addItems() {

        if (isPost()) {
//            save and redir
            ExamItem examItem = (ExamItem) input.getValue("examItem");
            Integer examId = (Integer) session().getAttribute("examId");
            examItem.setExamid(examId);

            if (examItem.getId() == null) {
                examItem = examItemDao.insert(examItem);
            } else {
                examItem = examItemDao.save(examItem);
            }

            recomputeExamPoints();

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

    public String editItem() {
        session().setAttribute("examItemId", input.getInt("id"));
        if (isAjaxRequest()) {
            return AJAX;
        }
        return SUCCESS;
    }

    public String deleteItem() {
        Integer itemId = input.getInt("id");
        examItemDao.delete(examItemDao.findById(itemId));
        session().removeAttribute("examItemId");
        recomputeExamPoints();
        return SUCCESS;
    }

    public String removeEditItem() {
        session().removeAttribute("examItemId");
        return SUCCESS;
    }

    private void recomputeExamPoints() {
        Integer examId = (Integer) session().getAttribute("examId");
        Exam exam = examDao.findById(examId);
        List<ExamItem> items = examItemDao.findByExamId(examId);

        Long total = 0L;
        for (ExamItem ei : items) {
            total += ei.getPoints();
        }

        exam.setPoints(total);
        examDao.save(exam);
    }

//***********************************************************
//---------------- EXAM ITEM ACTIONS ------------------------
    public String addItemsAnswer() {

        ExamItemAnswer answer = (ExamItemAnswer) input.getValue("answer");
        Integer itemId = input.getInt("item.id");

        answer.setItemid(itemId);

        answerDao.insert(answer);

        return SUCCESS;
    }

    public String deleteAnswer() {
        Integer answerId = input.getInt("id");
        answerDao.delete(answerDao.findById(answerId));
        return SUCCESS;
    }

//******************************************************
//---------------- EXAM ACTIONS ------------------------
    public String newExam() {
        output.setValue("difficulties", diffs);
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

//******************************************************
//---------------- GROUPS ACTIONS ----------------------
    public String newGroup() {
        return SUCCESS;
    }

    public String saveGroup() {
        ExamGroup group = (ExamGroup) input.getValue("group");

        if (group == null) {
            return ERROR;
        }

        User user = getSessionObj();
        group.setOwner(user.getId());
        if (group.getId() == null) {
            group.setStudents(0);
            examGroupDao.insert(group);
        } else {
            examGroupDao.save(group);
        }
        output().setObject("activeTab", "liGroups");
        return SUCCESS;
    }

    public String editGroup() {
        Integer groupId = input.getInt("id");

        if (groupId < 1000) {
            return ERROR;
        }

        ExamGroup examGroup = examGroupDao.findById(groupId);
        if (examGroup == null) {
            return ERROR;
        }
        output.setValue("group", examGroup);

        return SUCCESS;
    }

    public String addStudsRedir() {
        Integer examId = input.getInt("id");
        if (examId < 1000) {
            return ERROR;
        }
        session().setAttribute("groupId", examId);
        return SUCCESS;
    }

    public String addStuds() {
        if (isPost()) {
            // do save

//            get group id from input
//            get user id from input
            // create an examgroupuser and save

            return CREATED;
        } else {
            if (isGet()) {
                //populate page

                List<User> users = userDao.findAllStudents();
                Map<Integer, String> userMap = new HashMap<>();
                for(User user : users){
                    userMap.put(user.getId(), user.getName());
                }

                output().setValue("freeStudents", userMap);
//                 list of user

//                a map of students that are not in this group
//                a map of students that are in this group
                return SUCCESS;
            }
        }

        return ERROR;
    }
}
