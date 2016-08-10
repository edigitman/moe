package ro.agitman.moe.dao.impl;

import org.mentabean.BeanException;
import org.mentabean.BeanSession;
import org.mentabean.util.OrderBy;
import org.mentabean.util.SQLUtils;
import ro.agitman.moe.dao.ExamItemDao;
import ro.agitman.moe.model.ExamItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by edi on 7/11/2016.
 */
public class ExamItemDaoImpl extends GenericDaoImpl<ExamItem> implements ExamItemDao {

    public ExamItemDaoImpl(BeanSession beanSession) {
        super(ExamItem.class, beanSession);
    }

    @Override
    public List<ExamItem> findByExamId(Integer examId) {
        ExamItem examItem = new ExamItem();
        examItem.setExamid(examId);

        return beanSession.loadList(examItem, new OrderBy().orderByAsc("ord"));
    }

    public Integer findNextOrderIndex(Integer examId) {
        Connection conn = beanSession.getConnection();
        PreparedStatement stmt = null;
        ResultSet rset = null;

        try {
            stmt = SQLUtils.prepare(conn, "select max(i.ord) + 1 from exam_items i where i.examid = ?", examId);
            rset = stmt.executeQuery();

            return rset.next() ? rset.getInt(1) : 1;

        } catch (SQLException e) {
            throw new BeanException(e);
        } finally {
            SQLUtils.close(rset, stmt);
        }
    }

    @Override
    public ExamItem findById(Integer id) {
        ExamItem examItem = new ExamItem(id);
        boolean loaded = beanSession.load(examItem);
        if (!loaded) {
            throw new IllegalStateException("Cannot load examItem by id " + id);
        }
        return examItem;
    }
}
