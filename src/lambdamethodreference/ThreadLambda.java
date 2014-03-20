package lambdamethodreference;

/**
 * Thread的lambda表达式运用及方法引用
 * @version 1.0
 * @author xiehai
 * @date 2014年3月19日 下午5:34:35 
 */
public class ThreadLambda {
	public static void main(String[] args) {
		ThreadLambda.threadStart1();
		ThreadLambda.threadStart2();
		ThreadLambda.threadStart3();
	}
	
	/**
	 * 普通实现
	 */
	public static void threadStart1(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("hello world, i'm threadStart1!");
			}
		}).start();
	}
	
	/**
	 * lambda表达式
	 */
	public static void threadStart2(){
		new Thread(() -> {
			//此处的作用是实现Runnable接口的函数式接口方法run
			System.out.println("hello world, i'm threadStart2!");
		}).start();
	}
	
	/**
	 * 方法引用
	 */
	public static void threadStart3(){
		new Thread(ThreadLambda::reference).start();
	}
	
	public static void reference(){
		System.out.println("hello world, i'm threadStart3!");
	}
}
