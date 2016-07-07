package ro.agitman.moe.web.action;

import org.mentawai.core.BaseAction;

/**
 * Created by edi on 7/6/2016.
 */
public class HelloAction extends BaseAction {

    public String hi() {
        String msg = input.getString("msg");
        if (isEmpty(msg)) {
            msg = "Why you did not type anything?";
        }
        output.setValue("msg", msg);
        return SUCCESS;
    }

}
