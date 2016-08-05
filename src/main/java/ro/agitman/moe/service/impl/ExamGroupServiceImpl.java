package ro.agitman.moe.service.impl;

import org.mentabean.BeanSession;
import ro.agitman.moe.dao.ExamGroupDao;
import ro.agitman.moe.model.ExamGroup;
import ro.agitman.moe.service.ExamGroupService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by d-uu31cq on 05.08.2016.
 */
public class ExamGroupServiceImpl implements ExamGroupService {

    private final BeanSession session;
    private final ExamGroupDao groupDao;

    public ExamGroupServiceImpl(BeanSession session, ExamGroupDao groupDao) {
        this.session = session;
        this.groupDao = groupDao;
    }

    public ExamGroup findById(Integer groupId){
        return groupDao.findById(groupId);
    }

    @Override
    public void updateStudentsNo(Integer groupId) {
        groupDao.updateStudentsNo(groupId);
    }

    public List<ExamGroup> findByOwner(Integer id){
        return groupDao.findByOwner(id);
    }

    //TODO move this to Exam Instance
    public void lockGroup(Integer groupId){
        try{
            session.getConnection().setAutoCommit(false);

            ExamGroup group = groupDao.findById(groupId);
            group.setLocked(true);
            groupDao.save(group);

            session.getConnection().setAutoCommit(true);
            session.getConnection().commit();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ExamGroup saveInsert(Integer userId, ExamGroup group) {

        group.setOwner(userId);
        if (group.getId() == null) {
            groupDao.insert(group);
        } else {
            groupDao.save(group);
        }

        return group;
    }
}
