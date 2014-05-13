package java8.lambdamethodreference;

/**
 * Thread��lambda���ʽ���ü���������
 * @version 1.0
 * @author xiehai
 * @date 2014��3��19�� ����5:34:35 
 */
public class ThreadLambda {
	public static void main(String[] args) {
		ThreadLambda.threadStart1();
		ThreadLambda.threadStart2();
		ThreadLambda.threadStart3();
	}
	
	/**
	 * ��ͨʵ��
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
	 * lambda���ʽ
	 */
	public static void threadStart2(){
		new Thread(() -> {
			//�˴���������ʵ��Runnable�ӿڵĺ���ʽ�ӿڷ���run
			System.out.println("hello world, i'm threadStart2!");
		}).start();
	}
	
	/**
	 * ��������
	 */
	public static void threadStart3(){
		new Thread(ThreadLambda::run).start();
	}
	
	public static void run(){
		System.out.println("hello world, i'm threadStart3!");
	}
}
