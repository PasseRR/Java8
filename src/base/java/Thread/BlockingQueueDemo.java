package base.java.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * BlockingQueue阻塞队列<BR>
 * a.ArrayBlockingQueue    一个由数组支持的有界队列<BR>
 * b.LinkedBlockingQueue   一个链接节点支持的可选有界队列<BR>
 * c.PriorityBlockingQueue 一个由优先级堆支持的无界优先级队列<BR>
 * d.DelayQueue	一个由优先级堆支持的、基于时间的调度队列<BR>
 * e.SynchronousQueue 一个利用BlockingQueue接口的简单聚集机制<BR>
 * P.S. 可以通过构造方法设置capacity来使得阻塞队列是有界的,否则,则为无界队列
 * @author xiehai
 * @date 2014年5月21日 下午4:10:06 
 */
public class BlockingQueueDemo {
	public static void main(String[] args) {
//		new ProducerConsumer().testProducerConsumer();
		testCache();
	}
	
	public static void testCache(){
		Cache<String, String> cache = new Cache<>();
		cache.put("userName", "Jack", 3, TimeUnit.SECONDS);
		cache.put("user", "Jacky", 1, TimeUnit.SECONDS);
		try {
			cache.printAllCacheNames();
			Thread.sleep(2000);
			cache.printAllCacheNames();
			Thread.sleep(2000);
			cache.printAllCacheNames();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

/**
 * 生产者与消费者问题<BR>
 * {@code LinkedBlockingQueue}
 * @author xiehai
 * @date 2014年5月21日 下午5:14:19 
 */
class ProducerConsumer{
	/**
	 * 生产者
	 * @author xiehai
	 * @date 2014年5月21日 下午5:14:42 
	 */
	private class Producer extends Thread{
		private String name;
		private BlockingQueue<String> blockingQueue;
		
		public Producer(String name, BlockingQueue<String> blockingQueue){
			this.name = name;
			this.blockingQueue = blockingQueue;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			try {
				for(int i = 0; i < 5; ++i){
					//put方法在队列满的时候会阻塞直到有队列成员被消费
					blockingQueue.put(produce());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * 生产者生产产品
		 * @return
		 */
		private String produce(){
			StringBuilder product = new StringBuilder();
			product.append(this.name);
			product.append(" ");
			product.append((char) ('A' + (int) (Math.random()*26)));
			
			System.out.println(this.name + " produces product:" + product.toString());
			
			return product.toString();
		}
	}
	
	/**
	 * 消费者
	 * @author xiehai
	 * @date 2014年5月21日 下午5:21:34 
	 */
	private class Consumer extends Thread{
		private String name;
		private BlockingQueue<String> blockingQueue;
		
		public Consumer(String name, BlockingQueue<String> blockingQueue){
			this.name = name;
			this.blockingQueue = blockingQueue;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			try {
				for(int i = 0; i < 5; ++i){
					//take方法在队列空的时候会阻塞，直到有队列成员被放进来
					consume(blockingQueue.take());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * 消费者消费产品
		 * @param product
		 */
		private void consume(String product){
			System.out.println(this.name + " consumes product:" + product);
		}
	}
	
	public void testProducerConsumer(){
		//线程多（>20），Queue长度长（>30），使用LinkedBlockingQueue
		//线程少 (<20) ，Queue长度短 (<30) , 使用SynchronousQueue
		//ArrayBlockingQueue不考虑使用
		BlockingQueue<String> queue = new LinkedBlockingQueue<>(5);
		Producer producer1 = new Producer("Jack", queue);
		Producer producer2 = new Producer("Jones", queue);
		Consumer consumer1 = new Consumer("Jim", queue);
		Consumer consumer2 = new Consumer("Lucy", queue);
		producer1.start();
		producer2.start();
		consumer1.start();
		consumer2.start();
	}
}

/**
 * 缓存模拟<BR>
 * DelayQueue,用于放置实现了Delayed接口的对象,其中的对象只能在其到期时才能从队列中取走<BR>
 * 这种队列是有序的,即队头对象的延迟到期时间最长。注意：不能将null元素放置到这种队列中。
 * @author xiehai
 * @date 2014年5月22日 下午4:29:33 
 */
class Cache<K, V>{
	//缓存的map
	private ConcurrentHashMap<K, V> map = new ConcurrentHashMap<>();
	//验证缓存在map中的key是否过期的key队列
	private DelayQueue<DelayItem<K>> queue = new DelayQueue<>();
	
	public Cache(){
		Thread daemon = new Thread(new Runnable() {
			@Override
			public void run() {
				removeOverdueCache();
			}
		});
		daemon.setDaemon(true);
		daemon.setName("Cache Service Thread");
		daemon.start();
	}
	
	/**
	 * 加入新的缓存item
	 * @param key		缓存item的key
	 * @param value 	缓存item的value
	 * @param time 		缓存过期时间
	 * @param timeUnit 	缓存过期时间的类型
	 */
	public void put(K key, V value, long time, TimeUnit timeUnit){
		V oldValue = map.put(key, value);
		if(null != oldValue){//缓存的key已经存在,移除队列中的key
			queue.remove(key);
		}
		long nanotime = TimeUnit.NANOSECONDS.convert(time, timeUnit);
		
		queue.put(new DelayItem<K>(key, nanotime));
	}
	
	/**
	 * 移除过期的缓存
	 */
	private void removeOverdueCache(){
		System.out.println(Thread.currentThread().getName() + "-Remove Cache Service Start!");
		while(true){
			try {
				DelayItem<K> item = queue.take();
				if(null != item){
					K key = item.getItem();
					System.out.println("Remove cache, cache name = " + key + ", value = " + map.get(key));
					map.remove(key);
					queue.remove(key);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 返回对应key的缓存
	 * @param key
	 * @return
	 */
	public V getCache(K key){
		return map.get(key);
	}
	
	public void printAllCacheNames(){
		queue.forEach((item) -> System.out.println(item));
	}
}

class DelayItem<T> implements Delayed{
	/**用于产生缓存item的序列号*/
	private static AtomicLong seqNoFactory = new AtomicLong(0);
	/**缓存内容*/
	private T item;
	/**序列号*/
	private long seqNo;
	/**缓存失效时间*/
	private long time;
	public static final long START_TIME = System.nanoTime();
	
	public DelayItem(T item, long time) {
		this.item = item;
		this.time = now() + time;
		this.seqNo = seqNoFactory.incrementAndGet();
	}
	
	/**
	 * 用于进行队列内部的排序
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Delayed other) {
		 if (other == this) // compare zero ONLY if same object
	            return 0;
	        if (other instanceof DelayItem) {
	            @SuppressWarnings("rawtypes")
				DelayItem x = (DelayItem) other;
	            long diff = time - x.time;
	            if (diff < 0){
	                return -1;
	            }else if (diff > 0){
	                return 1;
	            }else if (seqNo < x.seqNo){
	                return -1;
	            }else{
	                return 1;
	            }
	        }
	        long d = (getDelay(TimeUnit.NANOSECONDS) - other.getDelay(TimeUnit.NANOSECONDS));
	        return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
	}

	/**
	 * 取DelayQueue里面的元素时判断是否到了延时时间，否则不予获取，是则获取。
	 * @see java.util.concurrent.Delayed#getDelay(java.util.concurrent.TimeUnit)
	 */
	@Override
	public long getDelay(TimeUnit unit) {
		long d = unit.convert(time - now(), TimeUnit.NANOSECONDS);
		return d;
	}
	
	public T getItem(){
		return this.item;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cache Name = " + this.item + 
				", Overdue Time = " + this.time/1000/1000/1000 + 
				"s, Seq No = " + this.seqNo;
	}
	
	private long now(){
		return System.nanoTime() - START_TIME;
	}
}