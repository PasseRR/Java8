package interface8.defalult8;

/**
 * Java8接口新特征
 * @version 1.0
 * @author xiehai
 * @date 2014年3月19日 下午3:32:38 
 */
public interface DefaultInterface {
	/**数字1*/
	int NUM_ZERO = 0;
	/**
	 * 接口方法
	 * @param name
	 */
	void sayHello(String name);
	/**
	 * Java8新特征<BR>
	 * default方法:接口可以有实现方法，而且不需要实现类去实现其方法。<BR>
	 * 只需在方法名前面加个default关键字即可
	 */
	default void sayHello(){
		System.out.println("hello world!");
	}
	
	/**
	 * Java8新特征<BR>
	 * 接口static方法
	 * @return
	 */
	static double getPi(){
		return java.lang.Math.PI;
	}
}
