package base;

import java.util.Arrays;
import java.util.List;

/**
 * Java Native Interface的基本调用<BR>
 * 步骤:<BR>
 * 1.创建Java类,并创建native方法<BR>
 * 2.用Javah命令生成*.h文件<BR>
 * 3.创建dll项目(用Cpp IDE)并导入生成的*.h文件<BR>
 * 4.实现native方法并编译<BR>
 * 5.将生成的dll文件放在项目路径或java.library.path下<BR>
 * @version 1.0
 * @author xiehai
 * @date 2014年4月16日 下午1:20:08 
 */
public class Jni {
	static{
		System.loadLibrary("jni");
	}
	/**
	 * 测试返回值为void
	 */
	public native void helloWorld();
	
	/**
	 * 测试返回值为boolean
	 * @param number 判断number是否是素数
	 * @return
	 */
	public native boolean isPrime(int number);
	
	/**
	 * 测试返回值为int
	 * @param pre 被加数
	 * @param last 加数
	 * @return
	 */
	public native int add(int pre, int last);
	
	/**
	 * 测试返回值为float
	 * @param pre 被除数
	 * @param last 除数
	 * @return
	 */
	public native float divide(int pre, int last);
	
	/**
	 * 测试返回值为double
	 * @param sub 底数
	 * @param sup 指数
	 * @return
	 */
	public native double pow(double sub, int sup);
	
	/**
	 * 测试返回String
	 * @param str 转换为大写字符串
	 * @return
	 */
	public native String toUpcase(String str);
	
	/**
	 * 以空格分割字符串
	 * @param str 子字符串
	 * @return
	 */
	public native String[] split(String str);
	
	/**
	 * 返回Java封装类
	 * @return
	 */
	public native List<String> getList();
	
	/**
	 * 返回自定义类
	 * @return
	 */
	public native Person getPerson();
	
	
	public static void main(String[] args) {
		Jni jni = new Jni();
		System.out.println(jni.isPrime(23));
		System.out.println(jni.add(2, 4));
		System.out.println(jni.divide(4, 3));
		System.out.println(jni.pow(1.5, 2));
		System.out.println(jni.toUpcase("Hello World!"));
//		jni.helloWorld();
		System.out.println(Arrays.toString(jni.split("Hello World !")));
	}
	
	class Person{
		
	}
}
