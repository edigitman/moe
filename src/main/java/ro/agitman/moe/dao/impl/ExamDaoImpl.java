package ro.agitman.moe.dao.impl;

import org.mentabean.BeanException;
import org.mentabean.BeanSession;
import org.mentabean.util.SQLUtils;
import ro.agitman.moe.dao.ExamDao;
import ro.agitman.moe.model.Exam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.mentabean.util.SQLUtils.orderByAsc;

/**
 * Created by d-uu31cq on 07.07.2016.
 */
public class ExamDaoImpl extends GenericDaoImpl<Exam> implements ExamDao {


    public ExamDaoImpl(BeanSession beanSession) {
        super(Exam.class, beanSession);
    }

    @Override
    public Exam findById(Integer id) {
        Exam exam = new Exam(id);
        boolean loaded = beanSession.load(exam);
        if (!loaded) {
            throw new IllegalStateException("Cannot load exam by id " + id);
        }
        return exam;
    }


    @Override
    public List<Exam> findForOwner(Integer id) {
        Exam exam = new Exam();
        exam.setOwner(id);

        return beanSession.loadList(exam, orderByAsc("id"));
    }

    public Exam findForExamInstanceId(Integer exiId) {
        Connection conn = beanSession.getConnection();
        PreparedStatement stmt = null;
        ResultSet rset = null;

        try {
            StringBuilder query = new StringBuilder(256);
            query.append("select ");
            query.append(beanSession.buildSelect(Exam.class, "e"));
            query.append(" from exams e join exam_instances ei on e.id = ei.examid ");
            query.append(" where ei.id = ? ");

            stmt = SQLUtils.prepare(conn, query.toString(), exiId);
            rset = stmt.executeQuery();

            Exam exam = new Exam();
            beanSession.populateBean(rset, exam, "e");
            return exam;
        } catch (SQLException e) {
            throw new BeanException(e);
        } finally {
            SQLUtils.close(rset, stmt);
        }
    }
}
