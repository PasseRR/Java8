package base.java.Thread;

/**
 * CAS 操作包含三个操作数 —— 内存位置（V）、预期原值（A）和新值(B)。<BR>
 * 如果内存位置的值与预期原值相匹配，那么处理器会自动将该位置值更新为新值。<BR>
 * @author xiehai
 * @date 2014年5月20日 下午2:36:13 
 */
public class CompareAndSwap {
	private int value;
	
	public synchronized int getValue(){
		return value;
	}
	
	public synchronized int compareAndSwap(int except, int newValue){
		if(value == except){
			value = newValue;
		}
		
		return value;
	}
	
	public static void main(String[] args) {
		Counter counter = new Counter();
		for(int i = 0; i < 10; ++i){
			CounterThread counterThread = new CounterThread(counter);
			counterThread.start();
		}
	}
}

/**
 * 计数器
 * @author xiehai
 * @date 2014年5月20日 下午2:54:08 
 */
class Counter{
	private CompareAndSwap compareAndSwap;
	
	public Counter(){
		compareAndSwap = new CompareAndSwap();
	}
	
	/**
	 * 当前值
	 * @return
	 */
	public int getValue(){
		return compareAndSwap.getValue();
	}
	
	/**
	 * 递增值
	 * @return
	 */
	public int increment(){
		int value = compareAndSwap.getValue();
		while(compareAndSwap.compareAndSwap(value, value + 1) == value){//如果与预期值不一样
			value = compareAndSwap.getValue();
		}
		
		return value + 1;
	}
}

class CounterThread extends Thread{
	private Counter counter;
	public CounterThread(Counter counter){
		this.counter = counter;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " " + counter.getValue() + " " + counter.increment());
	}
}
