package ro.agitman.moe.service.impl;

import org.mentabean.BeanSession;
import ro.agitman.moe.dao.ExamItemAnswerDao;
import ro.agitman.moe.dao.ExamItemDao;
import ro.agitman.moe.dao.StudExamAnswerDao;
import ro.agitman.moe.dao.StudExamInstanceDao;
import ro.agitman.moe.model.*;
import ro.agitman.moe.service.StudExamAnswerService;
import ro.agitman.moe.web.dto.StudentItemAnswerDTO;

import java.util.List;

/**
 * Created by edi on 8/5/16.
 */
public class StudExamAnswerServiceImpl implements StudExamAnswerService {

    private final BeanSession session;
    private final StudExamAnswerDao studAnswerDao;
    private final ExamItemAnswerDao answerDao;
    private final StudExamInstanceDao studEXIDao;
    private final ExamItemDao itemDao;

    public StudExamAnswerServiceImpl(BeanSession session,
                                     StudExamAnswerDao answerDao,
                                     StudExamInstanceDao instanceDao,
                                     ExamItemDao examItemDao,
                                     ExamItemAnswerDao examItemAnswerDao) {
        this.session = session;
        this.studAnswerDao = answerDao;
        this.studEXIDao = instanceDao;
        this.itemDao = examItemDao;
        this.answerDao = examItemAnswerDao;
    }


    @Override
    public List<StudentExamAnswer> findByStudent(Integer id) {
        return studAnswerDao.findByStudent(id);
    }

    @Override
    public StudentExamAnswer saveInsert(StudentExamAnswer examAnswer){

        if (examAnswer.getId() == null) {
            studAnswerDao.insert(examAnswer);
        } else {
            studAnswerDao.save(examAnswer);
        }

        return examAnswer;
    }


    public List<StudentItemAnswerDTO> loadStudentItemAsnwers(Integer exiId){

        return studAnswerDao.findWithItemByStudentInstance(exiId);
    }


    public StudentExamAnswer createAnswer(Object answerValue, Boolean skip,Integer ownerId, Integer studExiId, Integer itemId, Boolean isLast){

        StudentExamAnswer answer = new StudentExamAnswer();
        answer.setOwnerId(ownerId);
        answer.setExamItemId(itemId);
        answer.setStudentExamInstanceId(studExiId);

        if(!skip){
            parseAnswer(answerValue, answer, itemId);
        }

        this.saveInsert(answer);

        if (isLast) {
//          mark exam as completed by student
            StudentExamInstance instance = studEXIDao.findById(studExiId);
            instance.setStatus(2);
            studEXIDao.save(instance);
        }

        return answer;
    }


    private void parseAnswer(Object studAnswerObj, StudentExamAnswer answer, Integer itemId){

        ExamItem item = itemDao.findById(itemId);
        List<ExamItemAnswer> answers = answerDao.findForItem(itemId);

        StringBuilder actualAnswer = new StringBuilder();

        // in case of free text selected
        if (item.getType().equals(3)) {
            actualAnswer.append(studAnswerObj);
            answer.setSolvable(false);
        } else {
            answer.setSolvable(true);
            // in case of multiple options, checkbox
            if (studAnswerObj instanceof String[]) {
                String[] studAnswerArr = (String[]) studAnswerObj;
                answer.setRawAnswer(buildAnswerArray(studAnswerArr));

                for (String s : studAnswerArr) {
                    ExamItemAnswer foundAnswer = matchWithAnswer(s, answers);
                    actualAnswer.append(foundAnswer.getValue()).append(", ");
                }
            } else {
                // in case only one answer selected, radiobox
                answer.setRawAnswer(String.valueOf(studAnswerObj));

                ExamItemAnswer foundAnswer = matchWithAnswer(studAnswerObj, answers);
                actualAnswer.append(foundAnswer.getValue());
            }
        }
        answer.setValue(actualAnswer.toString());
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

}
