package xyz.dymasun.ws.rule;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import xyz.dymasun.ws.utils.PropertiesUtils;

import javax.annotation.PreDestroy;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
@Component("rulePool")
public class RulePool {

    private static ThreadPoolExecutor JOB_EXECUTE;
    public RulePool(){
        if (JOB_EXECUTE == null)
            JOB_EXECUTE = new ThreadPoolExecutor(10,2000,365,TimeUnit.DAYS,new LinkedBlockingQueue<Runnable>(100000), new ThreadPoolExecutor.CallerRunsPolicy());
    }
    protected static void add(Runnable command){
        JOB_EXECUTE.execute(command);
    }
    @PreDestroy
    public void destory(){
        System.out.println(JOB_EXECUTE == null);
        System.out.println("shutdown");
        if (!JOB_EXECUTE.isShutdown())
            JOB_EXECUTE.shutdown();
    }
}
