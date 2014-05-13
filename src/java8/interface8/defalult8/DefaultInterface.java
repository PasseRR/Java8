package java8.interface8.defalult8;

/**
 * Java8�ӿ�������
 * @version 1.0
 * @author xiehai
 * @date 2014��3��19�� ����3:32:38 
 */
public interface DefaultInterface {
	/**����1*/
	int NUM_ZERO = 0;
	/**
	 * �ӿڷ���
	 * @param name
	 */
	void sayHello(String name);
	/**
	 * Java8������<BR>
	 * default����:�ӿڿ�����ʵ�ַ��������Ҳ���Ҫʵ����ȥʵ���䷽����<BR>
	 * ֻ���ڷ�����ǰ��Ӹ�default�ؼ��ּ���
	 */
	default void sayHello(){
		System.out.println("hello world!");
	}
	
	/**
	 * Java8������<BR>
	 * �ӿ�static����
	 * @return
	 */
	static double getPi(){
		return java.lang.Math.PI;
	}
}
