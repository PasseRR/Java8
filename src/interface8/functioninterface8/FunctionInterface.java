package interface8.functioninterface8;

/**
 * 函数式接口:函数式接口是只包含一个方法的接口。<BR>
 * @version 
 * @author xiehai
 * @date 2014年3月19日 下午5:09:00 
 */
@FunctionalInterface
public interface FunctionInterface {
	abstract void sayHello();
	//因为注解@FunctionalInterface为函数式接口,若再添加方法会报错
	//函数式接口只能包含一个方法
//	void say();
}
