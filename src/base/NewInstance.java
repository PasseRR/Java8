package base;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Java创建对象实例的方法
 * @version 
 * @author xiehai
 * @date 2014年4月3日 上午10:24:50 
 */
public class NewInstance {
	public static void main(String[] args) 
			throws 	ClassNotFoundException, 
					InstantiationException, 
					IllegalAccessException, 
					CloneNotSupportedException, 
					NoSuchMethodException, 
					SecurityException, 
					IllegalArgumentException, 
					InvocationTargetException {
		//1.常用的new
		Instance instance1 = new Instance();
		instance1.sayHello("instance1");
		
		//2.使用clone
		Instance instance2 = (Instance) instance1.clone();
		instance2.sayHello("instance2");
		
		//3.使用newInstance方法来创建对象
		Class<?> clazz = Class.forName("base.Instance");
		Instance instance3 = (Instance) clazz.newInstance();
		instance3.sayHello("instance3");
		
		//4.另一种newInstance
		Instance instance4 = (Instance) Instance.class.newInstance();
		instance4.sayHello("instance4");
		
		//5.使用ClassLoader来创建对象
		Instance instance5 = (Instance) NewInstance.class
										.getClassLoader()
										.loadClass("base.Instance")
										.newInstance();
		instance5.sayHello("instance5");
		
		//6.使用反射
		Constructor<?> constructor = clazz.getDeclaredConstructor(new Class[]{});
		Instance instance6 = (Instance) constructor.newInstance(new Object[]{});
		instance6.sayHello("instance6");
		//带参数的构造方法
		//int.class和Integer.class不一样
		Constructor<?> constructor2 = clazz.getDeclaredConstructor(new Class[]{String.class, int.class});
//		constructor2.setAccessible(true);//设置构造方法的可见性
		Instance instance7 = (Instance) constructor2.newInstance(new Object[]{"123", 123});
		instance7.getArguments();
		//Class.newInstance() 只能够调用无参的构造函数，即默认的构造函数； 
		//Constructor.newInstance() 可以根据传入的参数，调用任意构造构造函数。 

		//Class.newInstance() 抛出所有由被调用构造函数抛出的异常。 

		//Class.newInstance() 要求被调用的构造函数是可见的，也即必须是public类型的; 
		//Constructor.newInstance() 在特定的情况下，可以调用私有的构造函数。 
	}
}

class Instance implements Cloneable{
	String string;
	int i;
	public Instance() {
		
	}
	
	public Instance(String str, int i){
		this.string = str;
		this.i = i;
	}
	
	public void getArguments(){
		System.out.println("String = " + this.string + ", i = " + this.i);
	}
	
	public void sayHello(String instance){
		System.out.println(instance + " say, hello world!");
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
