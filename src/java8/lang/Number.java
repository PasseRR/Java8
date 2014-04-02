package java8.lang;

/**
 * Java8 数字相关的改变
 * @version 1.0
 * @author xiehai
 * @date 2014年4月1日 下午5:24:57 
 */
public class Number {
	public static void main(String[] args) {
		//可以使用下划线'_'来格式化数字,使得显示明确
		int num1 = 123_456_789;
		System.out.println(num1);
		//二进制数字,0b/0B,每4位一组
		int num2 = 0b1010_1010;
		System.out.println(num2);
		//八进制数字,以0开始
		int num3 = 01441_1111;
		System.out.println(num3);
		//十六进制数字,0X/0x
		int num4 = 0x1111_1111;
		System.out.println(num4);
		
		//基本类型的位数
		//byte -128~127
		System.out.println("byte->\t" + Integer.toBinaryString(Byte.MAX_VALUE));
		//char 无符号 0~2^16
		System.out.println("char->\t" + Integer.toBinaryString((int)Character.MAX_VALUE));
		//short -2^15~2^15-1
		System.out.println("short->\t" + Integer.toBinaryString(Short.MAX_VALUE));
		//int -2^31~2^31-1
		System.out.println("int->\t" + Integer.toBinaryString(Integer.MAX_VALUE));
		//long -2^63~2^63-1
		System.out.println("long->\t" + Long.toBinaryString(Long.MAX_VALUE));
		//double
		System.out.println("double->" + Double.toHexString(Double.MAX_VALUE));
		//float
		System.out.println("float->\t" + Float.toHexString(Float.MAX_VALUE));
	}
}
