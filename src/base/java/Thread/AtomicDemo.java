package base.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * java.util.concurrent.atomic包例子
 * @author xiehai
 * @date 2014年6月9日 下午1:49:06 
 */
public class AtomicDemo {
	public static void main(String[] args) {
//		new BaseClassDemo().testAtomicBoolean();
		new BaseClassDemo().testAtomicInteger();
	}
}
/**
 * 基本类:AtomicBoolean, AtomicInteger, AtomicLong
 * @author xiehai
 * @date 2014年6月11日 下午4:02:23 
 */
class BaseClassDemo{
	private final AtomicBoolean FLG = new AtomicBoolean(false);
	private final AtomicInteger INT = new AtomicInteger(0);
	/**
	 * AtomicBoolean主要方法<BR>
	 * AtomicBoolean#compareAndSet(boolean, boolean)  
	 * 第一个参数为原始值，第二个参数为要修改的新值，若修改成功则返回true，否则返回false<BR> 
     * AtomicBoolean#getAndSet(boolean)   
     * 尝试设置新的boolean值，直到成功为止，返回设置前的数据 
	 */
	public void testAtomicBoolean(){
		ExecutorService es = Executors.newFixedThreadPool(3);
		for(int i = 0; i < 30; ++i){
			es.execute(new Runnable() {
				@Override
				public void run() {
					if(FLG.compareAndSet(false, true)){
						
					}else if(FLG.compareAndSet(true, false)){
						
					}
					System.out.println(Thread.currentThread().getName() + "AtomicBoolean -> " + FLG.get());
				}
			});
		}
		es.shutdown();
	}
	
	/**
	 * AtomicInteger和AtomicLong使用差不多,区别是长度<BR>
	 * AtomicInteger常见的方法列表 <BR>
     * AtomicInteger#get()             直接返回值 <BR>
     * AtomicInteger#getAndAdd(int)    增加指定的数据，返回变化前的数据 <BR>
     * AtomicInteger#getAndDecrement() 减少1，返回减少前的数据 <BR>
     * AtomicInteger#getAndIncrement() 增加1，返回增加前的数据 <BR>
     * AtomicInteger#getAndSet(int)    设置指定的数据，返回设置前的数据 <BR>
     * AtomicInteger#addAndGet(int)    增加指定的数据后返回增加后的数据 <BR>
     * AtomicInteger#decrementAndGet() 减少1，返回减少后的值 <BR>
     * AtomicInteger#incrementAndGet() 增加1，返回增加后的值 <BR>
     * AtomicInteger#lazySet(int)      仅仅当get时才会set <BR>
     * AtomicInteger#compareAndSet(int, int) 尝试新增后对比，若增加成功则返回true否则返回false 
	 */
	public void testAtomicInteger(){
		ExecutorService es = Executors.newFixedThreadPool(30);
		for(int i = 0; i < 300; ++i){
			es.execute(new Runnable() {
				@Override
				public void run() {
					int now = INT.incrementAndGet();
					System.out.println(Thread.currentThread().getName() + " -> " + now);
				}
			});
		}
		es.shutdown();
	}
}

/**
 * 引用类:AtomicReference, AtomicReference的ABA实例, 
 * AtomicStampedRerence, AtomicMarkableReference
 * @author xiehai
 * @date 2014年6月11日 下午5:05:18 
 */
class ReferenceClassDemo{
	
}