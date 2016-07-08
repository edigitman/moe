package ro.agitman.moe.web.action;

import org.mentawai.core.BaseAction;

/**
 * Created by d-uu31cq on 08.07.2016.
 */
public class AdminHomeAction extends BaseAction {


    public String editUser() throws Exception{
        Integer userId = input.getInt("id");



        return SUCCESS;
    }

}
