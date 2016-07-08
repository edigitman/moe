package ro.agitman.moe.web.filter;

import org.mentawai.core.Action;
import org.mentawai.core.Filter;
import org.mentawai.core.InvocationChain;
import org.mentawai.core.Output;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by d-uu31cq on 08.07.2016.
 */
public class PerformanceMonitoringFilter implements Filter{

    private final Logger LOGGER = LoggerFactory.getLogger(PerformanceMonitoringFilter.class);

    @Override
    public String filter(InvocationChain chain) throws Exception {
//        Action action = chain.getAction();

        long now = System.currentTimeMillis();

        String result = chain.invoke();

        long totalTime = System.currentTimeMillis() - now;

        LOGGER.info("Action completed in " + totalTime + " ms");

        return result;
    }

    @Override
    public void destroy() {

    }
}
