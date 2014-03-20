package interface8.defalult8;

/**
 * Java8接口实现
 * @version 1.0
 * @author xiehai
 * @date 2014年3月19日 下午4:16:27 
 */
public class MyInterface implements DefaultInterface/*, AnotherInterface*/{

	@Override
	public void sayHello(String name) {
		System.out.println("hello, " + name + "!");
	}
	
	public static void main(String[] args) {
		MyInterface mi = new MyInterface();
		mi.sayHello("Jack");
		mi.sayHello();
		System.out.println(DefaultInterface.getPi());
	}
	/**
	 * Java8接口和抽象类的区别：<BR>
	 * <B>相同点</B><BR>
	 * 1.都是抽象类型;<BR>
	 * 2.都可以有实现方法（以前接口不行）;<BR>
	 * 3.都可以不需要实现类或者继承者去实现所有方法,
	 * (以前不行，现在接口中默认方法不需要实现者实现).<BR>
	 * <B>不同点</B><BR>
	 * 1.抽象类不可以多重继承，接口可以（无论是多重类型继承还是多重行为继承）;<BR>
	 * 2.抽象类和接口所反映出的设计理念不同。其实抽象类表示的是"is-a"关系，
	 * 接口表示的是"like-a"关系;<BR>
	 * 3.接口中定义的变量默认是public static final 型，
	 * 且必须给其初值，所以实现类中不能重新定义，也不能改变其值；<BR>
	 * 抽象类中的变量默认是 friendly 型，其值可以在子类中重新定义，也可以重新赋值。 
	 */
	public void abstractClassAndInterface(){
		
	}
}
