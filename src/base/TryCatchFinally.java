package base;

/**
 * try里有一个return语句，那么紧跟在这个try后的finally里的code会不会被执行，<BR>
 * 什么时候被执行，在return前还是后? 
 * @version 
 * @author xiehai
 * @date 2014年4月2日 下午3:14:41 
 */
public class TryCatchFinally {
	private static final int ONE = 1;
	public static int getNumber1(){
		try {
			return ONE;
		} catch (Exception e) {
			
		} finally{
			System.out.println("i'm finally");
		}
		return 0;
	}
	
	public static int getNumber2(){
		int i = 0;
		try {
			return i;
		} catch (Exception e) {
			
		} finally{
			i ++;
			i = 100;
		}
		return i;
	}
	
	public static void getNumber3(){
		try {
			System.exit(0);
		} catch (Exception e) {

		} finally{
			System.out.println("i'm finally!");
		}
	}
	
	public static void main(String[] args) {
		//从结果可知,finally里的代码会执行
		//且在return前执行
		System.out.println("get the number1:" + getNumber1());
		System.out.println();
		
		//通过1可知 finally会在return前执行,
		//但是return的结果不会在finally块中改变
		System.out.println("get the number2:" + getNumber2());
		
		//如果在try块中退出JVM,那么finally块中的语句不会执行
		getNumber3();
	}
}
