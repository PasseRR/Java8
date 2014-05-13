package java8.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Java8 Collection�ӿ�������default����(�������ӿ�Iterable)
 * @version 1.0
 * @author xiehai
 * @date 2014��3��20�� ����1:37:50 
 */
public class Collection {
	public static void main(String[] args) {
//		forEach();
		removeIf();
	}
	
	/**
	 * ����forEach
	 * @see java.lang.Iterable
	 */
	public static void forEach(){
		String []arrays = {"hello", "world"};
		//��������
		Arrays.asList(arrays).forEach(str -> {
			System.out.println(str);
		});
		
		//˳�򼯺ϱ���
		//˳�򼯺�forEach����Iterable�ӿ�
		List<String> list = new LinkedList<String>();
		list.add(arrays[0]);
		list.add(arrays[1]);
		list.forEach(item -> {
			System.out.println(item);
		});
		
		//Java�Ժ��ͼ�,�ƶ����Ͳ����ظ�д���ͽ��
		Map<String, Object> map = new HashMap<>();
		map.put("123", "hello");
		map.put("321", "world");
		//ӳ��forEach����Map�ӿ�
		map.forEach((key, value) -> {
			System.out.println(key + " " + value);
		});
	}
	
	/**
	 * ɾ��Ԫ��
	 * @see java.util.Collection
	 */
	public static void removeIf(){
		List<String> list = new LinkedList<String>(Arrays.asList("hello", "world", "!"));
//		list.removeIf((item) -> {
//			return item.length() > 1;
//		});
		//���������ֻ��һ��,�����ʡ�Գ����£�
		list.removeIf((item) -> item.length() > 1);//��Ԫ�س��ȴ���1��ɾ����Ԫ��
		list.forEach((item) -> System.out.println(item));
		//another example
		List<Integer> anotherList = new LinkedList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
		anotherList.removeIf((item) -> item < 3);//ɾ��С��3��Ԫ��
		anotherList.forEach((item) -> System.out.println(item));
	}
}
