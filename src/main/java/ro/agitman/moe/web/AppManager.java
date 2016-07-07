package ro.agitman.moe.web;

import org.mentawai.core.ApplicationManager;
import ro.agitman.moe.web.action.HelloAction;

/**
 * Created by edi on 7/6/2016.
 */
public class AppManager extends ApplicationManager {

    @Override
    public void loadActions() {

        action("/Hello", HelloAction.class, "hi")
                .on(SUCCESS, fwd("/jsp/hello.jsp"));

    }
}
