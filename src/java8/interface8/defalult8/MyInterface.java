package java8.interface8.defalult8;

/**
 * Java8�ӿ�ʵ��
 * @version 1.0
 * @author xiehai
 * @date 2014��3��19�� ����4:16:27 
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
	 * Java8�ӿںͳ����������<BR>
	 * <B>��ͬ��</B><BR>
	 * 1.���ǳ�������;<BR>
	 * 2.��������ʵ�ַ�������ǰ�ӿڲ��У�;<BR>
	 * 3.�����Բ���Ҫʵ������߼̳���ȥʵ�����з���,
	 * (��ǰ���У����ڽӿ���Ĭ�Ϸ�������Ҫʵ����ʵ��).<BR>
	 * <B>��ͬ��</B><BR>
	 * 1.�����಻���Զ��ؼ̳У��ӿڿ��ԣ������Ƕ������ͼ̳л��Ƕ�����Ϊ�̳У�;<BR>
	 * 2.������ͽӿ�����ӳ����������ͬ����ʵ�������ʾ����"is-a"��ϵ��
	 * �ӿڱ�ʾ����"like-a"��ϵ;<BR>
	 * 3.�ӿ��ж���ı���Ĭ����public static final �ͣ�
	 * �ұ�������ֵ������ʵ�����в������¶��壬Ҳ���ܸı���ֵ��<BR>
	 * �������еı���Ĭ���� friendly �ͣ���ֵ���������������¶��壬Ҳ�������¸�ֵ�� 
	 */
	public void abstractClassAndInterface(){
		
	}
}
