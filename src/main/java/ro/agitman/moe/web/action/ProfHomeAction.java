package ro.agitman.moe.web.action;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.mentawai.core.BaseAction;
import ro.agitman.moe.dao.*;
import ro.agitman.moe.model.*;
import ro.agitman.moe.service.EmailService;
import ro.agitman.moe.service.ExamGroupService;
import ro.agitman.moe.service.ExamService;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by edi on 7/11/2016.
 */
public class ProfHomeAction extends BaseAction {

    private final Map<Integer, String> diffs;// = new ArrayList<String>(Arrays.asList("USOR", "MEDIU", "GREU"));
    private final Map<Integer, String> examItemType;// = new ArrayList<String>(Arrays.asList("Selectie unica", "Selectie multipla", "Text liber"));

    private final ExamService examService;
    private final ExamGroupService examGroupService;
    private final ExamGroupUserDao examGroupUserDao;
    private final ExamItemDao examItemDao;
    private final ExamItemAnswerDao answerDao;
    private final UserDao userDao;
    private final ExamInstanceDao instanceDao;
    private final StudExamAnswerDao studAnswerDao;

    private final EmailService emailService;
    private Gson gson = new Gson();

    private static final String EXAM_ID_SK = "examId";
    private static final String EXAM_ITEM_ID_SK = "examItemId";
    private static final String GROUP_ID_SK = "groupId";
    private static final String EXAM_INST_ID_SK = "examInstanceId";

    //    static initialization
    {
        examItemType = new HashMap<>();
        examItemType.put(1, "Selectie unica");
        examItemType.put(2, "Selectie multipla");
        examItemType.put(3, "Text liber");

        diffs = new HashMap<>();
        diffs.put(1, "Usor");
        diffs.put(2, "Mediu");
        diffs.put(3, "Dificil");
    }

    public ProfHomeAction(StudExamAnswerDao studAnswerDao, ExamInstanceDao examInstanceDao, UserDao userDao, ExamService examService, ExamGroupService examGroupService, ExamGroupUserDao examGroupUserDao, ExamItemDao examItemDao, ExamItemAnswerDao answerDao, EmailService emailService) {
        this.userDao = userDao;
        this.examService = examService;
        this.examGroupService = examGroupService;
        this.examItemDao = examItemDao;
        this.answerDao = answerDao;
        this.examGroupUserDao = examGroupUserDao;
        this.instanceDao = examInstanceDao;
        this.studAnswerDao = studAnswerDao;

        this.emailService = emailService;
    }

    //******************************************************
//---------------- ITEM ACTIONS ------------------------
    public String addItemsRedir() {
        Integer examId = input.getInt("id");

        if (examId < 1000) {
            return ERROR;
        }

        session().setAttribute(EXAM_ID_SK, examId);

        return SUCCESS;
    }

    public String addItems() {

        if (isPost()) {
//            save and redir
            ExamItem examItem = (ExamItem) input.getValue("examItem");
            Integer examId = (Integer) session().getAttribute(EXAM_ID_SK);
            examItem.setExamid(examId);

            if (examItem.getId() == null) {
                examItem = examItemDao.insert(examItem);
            } else {
                examItem = examItemDao.save(examItem);
            }

            recomputeExamPoints();

            session().setAttribute(EXAM_ITEM_ID_SK, examItem.getId());

            return CREATED;
        } else {
            if (isGet()) {
                Integer examId = (Integer) session().getAttribute(EXAM_ID_SK);
                Integer examItemId = (Integer) session().getAttribute(EXAM_ITEM_ID_SK);
                Exam exam = examService.findById(examId);
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
        session().setAttribute(EXAM_ITEM_ID_SK, input.getInt("id"));
        if (isAjaxRequest()) {
            return AJAX;
        }
        return SUCCESS;
    }

    public String deleteItem() {
        Integer itemId = input.getInt("id");
        examItemDao.delete(examItemDao.findById(itemId));
        session().removeAttribute(EXAM_ITEM_ID_SK);
        recomputeExamPoints();
        return SUCCESS;
    }

    public String removeEditItem() {
        session().removeAttribute(EXAM_ITEM_ID_SK);
        return SUCCESS;
    }

    private void recomputeExamPoints() {
        Integer examId = (Integer) session().getAttribute(EXAM_ID_SK);
        examService.recomputeTotalPoints(examId);
    }

    //***********************************************************
//---------------- EXAM ITEM ACTIONS ------------------------

    public String getAllAnswers() {
        Integer examItemId = (Integer) session().getAttribute(EXAM_ITEM_ID_SK);
        List<ExamItemAnswer> examAnswers = answerDao.findForItem(examItemId);

        output().setValue("answers", examAnswers);

        return SUCCESS;
    }

    public String addItemsAnswer() {

        String answerString = input().getString("answer");
        ExamItemAnswer answer = gson.fromJson(answerString, ExamItemAnswer.class);
        Integer examItemId = (Integer) session().getAttribute(EXAM_ITEM_ID_SK);

        answer.setItemid(examItemId);

        answerDao.insert(answer);

        output().setValue("answer", answer);

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

    public String saveExam() {
        Exam exam = (Exam) input.getValue("exam");

        if (exam == null) {
            return ERROR;
        }

        User user = getSessionObj();

        examService.saveInsert(user.getId(), exam);

        return SUCCESS;
    }

    public String updateExam() {

        String name = input().getString("name");
        String value = input().getString("value");
        Integer pk = input().getInt("pk");

        examService.updateNameDiff(pk, value, name);

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

        examGroupService.saveInsert(user.getId(), group);

        return SUCCESS;
    }

    public String editGroup() {
        Integer groupId = input.getInt("id");

        if (groupId < 1000) {
            return ERROR;
        }

        ExamGroup examGroup = examGroupService.findById(groupId);
        if (examGroup == null) {
            return ERROR;
        }
        output.setValue("group", examGroup);

        return SUCCESS;
    }

    public String addStudsRedir() {
        Integer groupId = input.getInt("id");
        if (groupId < 1000) {
            return ERROR;
        }
        session().setAttribute(GROUP_ID_SK, groupId);
        return SUCCESS;
    }

    public String addStuds() {
        if (isPost()) {

            Integer groupId = (Integer) session().getAttribute(GROUP_ID_SK);
            Object user = input().getValue("selectedStudents");

            if (user instanceof String[]) {
                String[] usersId = (String[]) user;
                createGroupUser(usersId, groupId);
            } else {
                Integer uid = input().getInt("selectedStudents");
                createGroupUser(uid, groupId);
            }

            updateGroupStudents(groupId);

            return CREATED;
        } else {
            if (isGet()) {
                //populate page
                Integer groupId = (Integer) session().getAttribute(GROUP_ID_SK);

                List<User> users = userDao.findAllStudents();
                List<User> used = examGroupUserDao.findByGroupId(groupId);
                users.removeAll(used);

                Map<Integer, String> freeUserMap = new HashMap<>();
//                Map<Integer, String> usedUserMap = new HashMap<>();
                for (User user : users) {
                    freeUserMap.put(user.getId(), user.getName());
                }
//                for (User user : used) {
//                    usedUserMap.put(user.getId(), user.getName());
//                }

                output().setValue("freeStudents", freeUserMap);
                output().setValue("usedStudents", used);

                return SUCCESS;
            }
        }
        return ERROR;
    }

    public String deleteStuds() {
        Integer studId = input().getInt("id");
        Integer groupId = (Integer) session().getAttribute(GROUP_ID_SK);

        examGroupUserDao.deleteByKeys(studId, groupId);
        updateGroupStudents(groupId);
        return SUCCESS;
    }

    private void updateGroupStudents(Integer groupId) {
        examGroupService.updateStudentsNo(groupId);
    }

    private void createGroupUser(String[] userIds, Integer groupId) {
        for (String userId : userIds) {
            createGroupUser(Integer.valueOf(userId), groupId);
        }
    }

    private void createGroupUser(Integer userId, Integer groupId) {
        ExamGroupUser examGroupUser = new ExamGroupUser();
        examGroupUser.setStudentid(userId);
        examGroupUser.setGroupid(groupId);
        examGroupUserDao.insert(examGroupUser);
    }

    //**************************************************************
//---------------- EXAM INSTANCES ACTIONS ----------------------
    public String addExamInstRedir() {
        return SUCCESS;
    }

    public String addExamInst() {

        if (isPost()) {
            ExamInstance instance = (ExamInstance) input.getValue("exi");
            User user = getSessionObj();
            instance.setOwner(user.getId());

            if (instance.getId() == null) {
                instance.setStatus(1);
                instanceDao.insert(instance);
            } else {
                instanceDao.save(instance);
            }

            emailService.sendExamCreated(user);

            examService.lockExam(instance.getExamid());
            examGroupService.lockGroup(instance.getEgroupid());

//            TODO implement logic to prevent editing of locked exams
//            TODO implement logic to prevent editing of locked groups

//            TODO implement logic to clone an exam


            session().setAttribute(EXAM_INST_ID_SK, instance.getId());
            return CREATED;
        } else {
            if (isGet()) {
                Integer instanceId = (Integer) session().getAttribute(EXAM_INST_ID_SK);
                if (instanceId != null) {
                    output.setValue("exi", instanceDao.findById(instanceId));
                }

                User user = getSessionObj();
                List<Exam> exams = examService.findForOwner(user.getId());
                List<ExamGroup> groups = examGroupService.findByOwner(user.getId());

                Map<Integer, String> examsMap = new HashMap<>();
                Map<Integer, String> groupsMap = new HashMap<>();


                exams.stream().forEach(exam -> examsMap.put(exam.getId(), exam.getName()));
                groups.stream().forEach(group -> groupsMap.put(group.getId(), group.getName()));

                output.setValue("exams", examsMap);
                output.setValue("groups", groupsMap);

            }
        }

        return SUCCESS;
    }

    public String changeInstanceStatus() {
        Integer instanceId = input().getInt("id");
        String action = input().getString("action");

        ExamInstance instance = instanceDao.findById(instanceId);
        if ("doStart".equals(action) && instance.getStatus().equals(1)) {
            instance.setStatus(2);
        }
        if ("doStop".equals(action) && instance.getStatus().equals(2)) {
            instance.setStatus(1);
        }
        if ("doFinish".equals(action) && instance.getStatus().equals(2)) {
            instance.setStatus(3);
        }

        instanceDao.save(instance);
        return SUCCESS;
    }

    public String reviewExam() {

        Integer exiId = input().getInt("id");
        if (exiId < 0) {
            exiId = (Integer) session().getAttribute(EXAM_INST_ID_SK);
        } else {
            session().setAttribute(EXAM_INST_ID_SK, exiId);
        }

        ExamInstance instance = instanceDao.findById(exiId);

        Integer groupId = instance.getEgroupid();
        Integer examId = instance.getExamid();
        Exam exam = examService.findById(examId);

        List<ExamItem> items = examItemDao.findByExamId(examId);
        List<User> students = examGroupUserDao.findByGroupId(groupId);

        output().setValue("items", items);
        output().setValue("students", students);
        output().setValue("alreadySolved", instance.getAutoSolved());

        session().setAttribute("exam", exam);

        return SUCCESS;
    }

    public String viewExamItemResult() {
        Integer studId = input().getInt("studId");
        Integer itemId = input().getInt("itemId");
        Integer exiId = (Integer) session().getAttribute(EXAM_INST_ID_SK);

        ExamItem item = examItemDao.findById(itemId);
        StudentExamAnswer answer = studAnswerDao.findByExiStudItem(exiId, studId, itemId);

        session().setAttribute(EXAM_ITEM_ID_SK, itemId);

        output().setValue("item", item);
        if (answer != null) {
            String val = answer.getValue();
            val = val.replace("#$", "<br/>");
            answer.setValue(val);
            output().setValue("answer", answer);
        } else {
            output().setValue("answer", "nimic");
        }
        return SUCCESS;
    }

    public String markAnswer() {

        Integer studAnswerId = input().getInt("id");
        String action = input().getString("r");
        Integer perc = input().getInt("p");

        StudentExamAnswer answer = studAnswerDao.findById(studAnswerId);
        ExamItem item = examItemDao.findById(answer.getExamItemId());

        if ("ok".equals(action)) {
            answer.setCorrect(true);
            answer.setPoints(item.getPoints() * perc / 100);
        } else{
            answer.setCorrect(false);
            answer.setPoints(0);
        }

        answer.setReviewed(true);
        studAnswerDao.save(answer);

        return SUCCESS;
    }

    public String solveExamInstance() {
        Integer exiId = (Integer) session().getAttribute(EXAM_INST_ID_SK);
        ExamInstance instance = instanceDao.findById(exiId);

        List<User> students = examGroupUserDao.findByGroupId(instance.getEgroupid());
        List<ExamItem> items = examItemDao.findByExamId(instance.getExamid());

        for (User user : students) {
            for (ExamItem item : items) {
                StudentExamAnswer answer = studAnswerDao.findByExiStudItem(exiId, user.getId(), item.getId());
                if (answer != null && answer.getSolvable()) {
                    List<ExamItemAnswer> itemAnswers = answerDao.findForItem(item.getId());
                    solveItem(answer, itemAnswers, item);
                    answer.setReviewed(true);
                    studAnswerDao.save(answer);
                }
            }
        }

        instance.setAutoSolved(true);
        instanceDao.save(instance);

        output().setValue("id", exiId);

        return SUCCESS;
    }

    private void solveItem(StudentExamAnswer studAnswer, List<ExamItemAnswer> itemAnswers, ExamItem item) {
        switch (item.getType()) {
            case 1:
                solveUniqueSel(studAnswer, itemAnswers);
                break;
            case 2:
                solveMultiSel(studAnswer, itemAnswers);
                break;
            default:
                throw new IllegalStateException("Item type unclear");
        }
    }

    private void solveMultiSel(StudentExamAnswer studAnswer, List<ExamItemAnswer> itemAnswers) {
        String[] answers = studAnswer.getRawAnswer().split("-");
        boolean correct = true;
        Iterator<ExamItemAnswer> eiaIt = itemAnswers.iterator();
        while (eiaIt.hasNext() && correct) {
            ExamItemAnswer itemAnswer = eiaIt.next();
            if (itemAnswer.getCorrect()) {
                boolean found = false;
                for (String answer : answers) {
                    if (!StringUtils.isEmpty(answer) && itemAnswer.getId().equals(Integer.valueOf(answer))) {
                        found = true;
                        break;
                    }
                }
                correct = found;
            } else {
                boolean found = false;
                for (String answer : answers) {
                    if (!StringUtils.isEmpty(answer) && itemAnswer.getId().equals(Integer.valueOf(answer))) {
                        found = true;
                        break;
                    }
                }
                correct = !found;
            }
        }
        studAnswer.setCorrect(correct);
    }

    private void solveUniqueSel(StudentExamAnswer studAnswer, List<ExamItemAnswer> itemAnswers) {
        String answer = studAnswer.getRawAnswer();
        studAnswer.setCorrect(false);
        for (ExamItemAnswer itemAnswer : itemAnswers) {
            if (itemAnswer.getCorrect() && itemAnswer.getId().equals(Integer.valueOf(answer))) {
                studAnswer.setCorrect(true);
                break;
            }
        }
    }

}
