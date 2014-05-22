package base.java.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

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
		new ProducerConsumer().testProducerConsumer();
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
