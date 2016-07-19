package ro.agitman.moe.dao;

import ro.agitman.moe.model.ExamGroupUser;
import ro.agitman.moe.model.User;

import java.util.List;

/**
 * Created by edi on 7/19/2016.
 */
public interface ExamGroupUserDao extends GenericDao<ExamGroupUser> {

    List<User> findByGroupId(Integer groupId);

    void deleteByKeys(Integer studId, Integer groupId);
}
