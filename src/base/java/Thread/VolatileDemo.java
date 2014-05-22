package base.java.thread;

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
		public void run() {
			long start = System.currentTimeMillis();
			while(!shutDown){
				Thread.yield();
			}
			long end = System.currentTimeMillis();
			System.out.println(end - start);
		}
		
		public void shutdown(){
			shutDown = true;
		}
	}
	
	private class Runnable2 implements Runnable{
		private volatile boolean shutDown = false;
		public void run() {
			long start = System.currentTimeMillis();
			while(!shutDown){
				Thread.yield();
			}
			long end = System.currentTimeMillis();
			System.out.println(end - start);
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
		runnable1.shutdown();
		eService.shutdown();
		Runnable2 runnable2 = volatileDemo.new Runnable2();
		eService2.execute(runnable2);
		runnable2.shutdown();
		eService2.shutdown();
//		VolatileDemo volatileDemo = new VolatileDemo();
//		Runnable2 runnable1 = volatileDemo.new Runnable2();
//		new Thread(runnable1).start();
//		runnable1.shutdown();
	}
}
