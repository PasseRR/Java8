package java8.interface8.defalult8;

/**
 * ���ڲ���default����������
 * @version 1.0
 * @author xiehai
 * @date 2014��3��19�� ����4:21:10 
 */
public interface AnotherInterface {
	
	/**
	 *��ʵ�ֵ������ӿڶ�����ͬ��default����,������,��������һ��<BR>
	 *��������ᱨ<b>������</b>�Ĵ��� 
	 */
	default void sayHello(){
		System.out.println("HELLO WORLD!");
	}
}
