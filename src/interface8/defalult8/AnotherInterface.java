package interface8.defalult8;

/**
 * 用于测试default方法二义性
 * @version 1.0
 * @author xiehai
 * @date 2014年3月19日 下午4:21:10 
 */
public interface AnotherInterface {
	
	/**
	 *若实现的两个接口都有相同的default方法,且名字,参数个数一样<BR>
	 *则编译器会报<b>二义性</b>的错误 
	 */
	default void sayHello(){
		System.out.println("HELLO WORLD!");
	}
}
