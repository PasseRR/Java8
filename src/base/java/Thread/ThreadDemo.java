package base.java.thread;

/**
 * 继承Thread类实现线程
 * @author xiehai
 * @date 2014年5月16日 下午2:46:36 
 */
public class ThreadDemo extends Thread{
	public ThreadDemo(String name) {
		super(name);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		System.out.println(getName() + " is running!");
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 10; ++i){
			new ThreadDemo("Thread " + i).start();
		}
	}
}
