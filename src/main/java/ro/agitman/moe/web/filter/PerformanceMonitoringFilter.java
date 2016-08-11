package ro.agitman.moe.web.filter;

import org.mentawai.core.Filter;
import org.mentawai.core.Input;
import org.mentawai.core.InvocationChain;
import org.mentawai.core.Output;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

/**
 * Created by d-uu31cq on 08.07.2016.
 */
public class PerformanceMonitoringFilter implements Filter {

    private final Logger LOGGER = LoggerFactory.getLogger(PerformanceMonitoringFilter.class);

    @Override
    public String filter(InvocationChain chain) throws Exception {
        long now = System.currentTimeMillis();
        LOGGER.info("Action " + chain.getActionName() + "." + chain.getInnerAction() + " started... ");

        Input input = chain.getAction().getInput();
        Iterator<String> inKeys = input.keys();
        while (inKeys.hasNext()) {
            String key = inKeys.next();
            LOGGER.info("INPUT [" + key + "] = [" + input.getValue(key) + "]");
        }

        String result = chain.invoke();
        LOGGER.info("Action " + chain.getActionName() + "." + chain.getInnerAction() + " returned: " + result);

        Output output = chain.getAction().getOutput();
        Iterator<String> outKeys = output.keys();
        while (outKeys.hasNext()) {
            String key = outKeys.next();
            if("_debug".equals(key))
                continue;
            LOGGER.info("OUTPUT [" + key + "] = [" + output.getValue(key) + "]");
        }
        long totalTime = System.currentTimeMillis() - now;

        LOGGER.info("Action " + chain.getActionName() + "." + chain.getInnerAction() + " completed in " + totalTime + " ms");

        return result;
    }

    @Override
    public void destroy() {

    }
}
