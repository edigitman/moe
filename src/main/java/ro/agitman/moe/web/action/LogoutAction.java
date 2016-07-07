package ro.agitman.moe.web.action;

import org.mentawai.core.BaseAction;
import org.mentawai.filter.AuthenticationFree;

/**
 * Created by d-uu31cq on 07.07.2016.
 */
public class LogoutAction extends BaseAction implements AuthenticationFree {

    /**
     * Implements the actual logout.
     * This method simply calls the session <i>reset()</i> method, to clean the session.
     * You may override this method if you want to do other operations when the user logs out.
     */
    protected void logout() {
        session.reset();
    }

    public String execute() {
        logout();
        return SUCCESS;
    }

    @Override
    public boolean bypassAuthentication(String s) {
        return true;
    }
}
