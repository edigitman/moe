package ro.agitman.moe.web.action;

import org.mentabean.BeanSession;
import org.mentawai.core.BaseAction;

/**
 * Created by edi on 7/7/2016.
 */
public class InitDBAction extends BaseAction {

    private final BeanSession session;

    public InitDBAction(BeanSession beanSession) {
        this.session = beanSession;
        try {
//            session.createTables();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String execute(){

//        session.createTables();

        return SUCCESS;
    }

}
