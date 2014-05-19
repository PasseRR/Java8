package base.java.Thread;

/**
 * 实现Runnable接口实现线程
 * @author xiehai
 * @date 2014年5月16日 下午2:51:09 
 */
public class RunnableDemo implements Runnable{

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " is running!");
	}

	public static void main(String[] args) {
		Runnable runnable = new RunnableDemo();
		for(int i = 0; i < 10; ++i){
			new Thread(runnable, "Thread " + i).start();
		}
	}
}
