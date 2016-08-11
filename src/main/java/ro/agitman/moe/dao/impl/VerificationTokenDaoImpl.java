package ro.agitman.moe.dao.impl;

import org.mentabean.BeanSession;
import ro.agitman.moe.dao.VerificationTokenDao;
import ro.agitman.moe.model.VerificationToken;

import java.util.List;

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

    public VerificationToken findByToken(String tokenValue) {
        VerificationToken token = new VerificationToken();
        token.setToken(tokenValue);
        token.setVerified(0);

        return beanSession.loadUnique(token);
    }

    public VerificationToken findByUser(Integer id) {
        VerificationToken token = new VerificationToken();
        token.setUserid(id);
        token.setVerified(0);

        List<VerificationToken> tks = beanSession.loadList(token);
        return tks.isEmpty() ? null : tks.get(0);
    }
}
