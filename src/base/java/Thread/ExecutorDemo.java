package base.java.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 线程池示范
 * @author xiehai
 * @date 2014年5月16日 下午2:58:45 
 */
public class ExecutorDemo {
	/**
	 * JDK提供的线程池分为3步:<BR>
	 * 1.创建线程目标对象<BR>
	 * 2.使用Executors创建线程池对象,返回ExecutorService对象<BR>
	 * 3.使用ExecutorService对象执行线程,最后关闭线程池中的线程
	 * @param args
	 */
	public static void main(String[] args) {
		//线程池中线程的数量 3
		ExecutorService es = Executors.newFixedThreadPool(3);
		Runnable runnable = new RunnableDemo();
		for(int i = 0; i < 10; ++i){
			es.execute(runnable);
		}
		es.shutdown();
//		methodOfExecutors();
	}
	
	/**
	 * Executors的API
	 */
	public static void methodOfExecutors(){
		//创建一个可重用固定线程数的线程池,以共享的无界队列的方式来运行线程
		//在需要时使用提供的ThreadFactory来创建新线程
		ExecutorService es = Executors.newFixedThreadPool(3);
		es.shutdown();
		
		//返回用于创建新线程的默认线程工厂
		@SuppressWarnings("unused")
		ThreadFactory tFactory = Executors.defaultThreadFactory();
		
		//创建一个可根据需要创建新线程的线程池,但是在以前构造的线程可用时将重用他们
		es = Executors.newCachedThreadPool();
		es.shutdown();
		
		//创建一个线程池,他可安排在规定延迟后运行命令或定期的执行
		es = Executors.newScheduledThreadPool(3);
		
		//在某个时间执行给定命令
		es.execute(()->{});
		//or
		es.execute(new Runnable() {
			@Override
			public void run() {
				
			}
		});
		
		//启动一次顺序关闭,执行以前提交的任务,但不接受新的任务
		es.shutdown();
	}
}
