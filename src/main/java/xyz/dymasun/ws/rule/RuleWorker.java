package xyz.dymasun.ws.rule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PreDestroy;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//线程及线程池所用的工具类
public abstract class RuleWorker extends Thread {
    //是否允许并发
    private boolean currency = false;
    //线程池对象
    private static final ThreadPoolExecutor JOB_EXECUTE = new ThreadPoolExecutor(10,2000,365,TimeUnit.DAYS,new LinkedBlockingQueue<Runnable>(100000), new ThreadPoolExecutor.CallerRunsPolicy());
    private RuleMap param;
    public RuleWorker(){
    }
    //实现线程功能所需要实现的方法
	public abstract void doMethod(RuleMap param);
    //以线程的方式启动，并允许并发
    public void start(RuleMap param){
		currency = true;//允许并发
		this.param = param;
		this.start();
    }
    @Override
    public void run() {
        this.doMethod(this.param);
    }

    //以线程池的方式启动，将本对象放入线程池中
    public void pool(RuleMap param){
        this.param = param;
        JOB_EXECUTE.execute(this);
    }
    public static void distory(){
        if(!JOB_EXECUTE.isShutdown())
            JOB_EXECUTE.shutdown();
    }
}
