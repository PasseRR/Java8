package base.java.Thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子变量计数器
 * @author xiehai
 * @date 2014年5月20日 下午3:42:51 
 */
public class AtomicCounter implements CalcCounter{
	private AtomicInteger ai = new AtomicInteger();
	public int getValue() {
		return ai.get();
	}

	public int increment() {
		return ai.incrementAndGet();
	}

	public int increment(int n) {
		return ai.addAndGet(n);
	}

	public int decrement() {
		return ai.decrementAndGet();
	}

	public int decrement(int n) {
		return ai.addAndGet(-n);
	}
	
	public static void main(String[] args) {
		//非原子变量计数器
		//会出现重复的计数
		CalcCounter c1 = new NormalCounter();
		for(int i = 0; i < 30; ++i){
			new CalcCounterThread(c1).start();
		}
		
		//原子变量计数器
		//不会出现重复的计数
//		CalcCounter c2 = new AtomicCounter();
//		for(int i = 0; i < 10; ++i){
//			new CalcCounterThread(c2).start();
//		}
	}
}

/**
 * 普通计数器
 * @author xiehai
 * @date 2014年5月20日 下午4:04:40 
 */
class NormalCounter implements CalcCounter{
	private int count;

	public int getValue() {
		return count;
	}

	public int increment() {
		return ++count;
	}

	public int increment(int n) {
		return count+n;
	}

	public int decrement() {
		return --count;
	}

	public int decrement(int n) {
		return count-n;
	}
	
}

/**
 * 计数器接口
 * @author xiehai
 * @date 2014年5月20日 下午3:44:41 
 */
interface CalcCounter{
	/**
	 * 当前值
	 * @return
	 */
	int getValue();
	
	/**
	 * 递增1
	 * @return
	 */
	int increment();
	
	/**
	 * 递增n
	 * @param n
	 * @return
	 */
	int increment(int n);
	
	/**
	 * 递减1
	 * @return
	 */
	int decrement();
	
	/**
	 * 递减n
	 * @param n
	 * @return
	 */
	int decrement(int n);
}

/**
 * 测试计数器的线程
 * @author xiehai
 * @date 2014年5月20日 下午4:04:58 
 */
class CalcCounterThread extends Thread{
	private CalcCounter calcCounter;
	public CalcCounterThread(CalcCounter calcCounter){
		this.calcCounter = calcCounter;
	}

	public void run() {
		System.out.println(Thread.currentThread().getName() + " " + calcCounter.getValue() + " " + calcCounter.increment());
	}
} 
