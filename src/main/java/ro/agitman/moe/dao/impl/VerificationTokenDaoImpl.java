package ro.agitman.moe.dao.impl;

import org.mentabean.BeanSession;
import ro.agitman.moe.dao.VerificationTokenDao;
import ro.agitman.moe.model.ExamItem;
import ro.agitman.moe.model.VerificationToken;

/**
 * Created by d-uu31cq on 10.08.2016.
 */
public class VerificationTokenDaoImpl extends GenericDaoImpl<VerificationToken> implements VerificationTokenDao {

    /**
     * @param beanSession .
     */
    public VerificationTokenDaoImpl(BeanSession beanSession) {
        super(VerificationToken.class, beanSession);
    }

    @Override
    public VerificationToken findById(Integer id) {
        VerificationToken verificationToken = new VerificationToken(id);
        boolean loaded = beanSession.load(verificationToken);
        if (!loaded) {
            throw new IllegalStateException("Cannot load verificationToken by id " + id);
        }
        return verificationToken;
    }
}
