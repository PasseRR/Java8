package base.java.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * {@code volatile}的使用
 * @author xiehai
 * @date 2014年5月19日 下午3:03:00 
 */
public class VolatileDemo {
	private class Runnable1 implements Runnable {
		private boolean shutDown = false;
		int i = 0;
		public void run() {
			while(!shutDown){
				System.out.println(Thread.currentThread().getName() + i ++);
			}
		}
		
		public void shutdown(){
			shutDown = true;
		}
	}
	
	private class Runnable2 implements Runnable{
		private volatile boolean shutDown = false;
		int i = 0;
		public void run() {
			while(!shutDown){
				System.out.println(Thread.currentThread().getName() + i ++);
			}
		}
		
		public void shutdown(){
			shutDown = true;
		}
	}
	public static void main(String[] args) throws InterruptedException {
		ExecutorService eService = Executors.newFixedThreadPool(5);
		ExecutorService eService2 = Executors.newFixedThreadPool(5);
		VolatileDemo volatileDemo = new VolatileDemo();
		Runnable1 runnable1 = volatileDemo.new Runnable1();
		eService.execute(runnable1);
		Thread.sleep(10);
		runnable1.shutdown();
		eService.shutdown();
		Runnable2 runnable2 = volatileDemo.new Runnable2();
		eService2.execute(runnable2);
		Thread.sleep(10);
		runnable2.shutdown();
		eService2.shutdown();
	}
}
