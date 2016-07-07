package ro.agitman.moe.web.action;

import org.mentawai.core.BaseAction;

/**
 * Created by d-uu31cq on 07.07.2016.
 */
public class RenewPasswordAction extends BaseAction {

    /**
     * Request new password
     * @return .
     */
    public String request() {
        return SUCCESS;
    }

    /**
     * Confirm from email address that you actually requested the change, gather some info about you based on token
     * @return .
     */
    public String confirm() {
        return SUCCESS;
    }

    /**
     * Confirm the new passeord from web-page
     * @return .
     */
    public String validate() {
        return SUCCESS;
    }

}
