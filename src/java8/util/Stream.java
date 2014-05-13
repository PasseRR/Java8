package java8.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Java8 ����������
 * @see java.util.stream.Stream
 * @version 1.0
 * @author xiehai
 * @date 2014��3��21�� ����4:45:08 
 */
public class Stream {
	public static void main(String[] args) {
//		operation();
		mapOperation();
	}
	
	/**
	 * ��������������
	 */
	public static void streamType(){
		List<Integer> list = new LinkedList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
		//������
		//���ݼ��ϵĴ�С,����һ���ķ�ʽ��ֳɶ��С����
		//�������,����߳�һ�����
		list.stream().parallel();
		//˳����
		//�������ϲ��,һ���߳̽���
		list.stream();
	}
	
	/**
	 * stream���м������ĩβ����<BR>
	 * ���Լ���List, Set
	 */
	public static void operation(){
		Integer []num = {3, 4, 5, 6, 7, 8, 9, 9, 8};
		List<Integer> list;
		list = Arrays.asList(num)
				.stream()
				.filter(item -> item > 5)//���˴���5������ ����filter���м����
//				.count();//ͳ��Ԫ�ظ��� ����long
//				.collect(Collectors.toCollection(LinkedList::new));//���������һ��LinkedList
				.collect(Collectors.toList());//���������list collectionĩβ����
		list.forEach(item -> System.out.print(item + " "));
		System.out.println();
		
		Arrays.asList(num)
				.stream()
				.limit(5)//����Ԫ�ظ���
				.forEach(item -> System.out.print(item + " "));
		System.out.println();
		
		list = Arrays.asList(num)
				.stream()
				.peek(item -> System.out.print(item + " "))//��ÿ��Ԫ��ִ��һЩ����
				.collect(Collectors.toList());
		System.out.println();
		
		Arrays.asList(num)
				.stream()
				.distinct()//ȥ���ظ�Ԫ��
				.forEach(item -> System.out.print(item + " "));
		System.out.println();
		
		Arrays.asList(num)
				.stream()
				.sorted((i, j) -> j - i)//����ָ����Comparator���� ֻ��һ���������ͼ ����β�˽���ͼ���뼯��
//				.forEach(System.out::print);
				.forEach(item -> System.out.print(item + " "));
		System.out.println();
		
		Integer max = Arrays.asList(num)
				.stream()
//				.min((first, second) -> first - second)
				.max((i, j) -> i-j)//��������Сֵ β�˲���
				.get();
		System.out.println(max);
		
		boolean flg = Arrays.asList(num)
				.stream()
				//���¾�Ϊβ�˲���
//				.allMatch(item -> item > 5);//����Ԫ���Ƿ����� 
//				.noneMatch(item -> item > 10);//����Ԫ���Ƿ�����
				.anyMatch(item -> item > 5);//�Ƿ���һ��Ԫ������
		System.out.println(flg);
		
		Arrays.asList(num)
				.stream()
				.filter(item -> item > 5)//���˴���5������
//				.map(item -> String.valueOf(item))//������ת����String
				.map(String::valueOf)
				.forEach(item -> System.out.println(item.getClass()));//��ǰ��ͼ�ж�����Integer����String
	}
	
	/**
	 * �����Լ��ϲ���Map
	 */
	public static void mapOperation(){
		Map<String, Object> map = new HashMap<>();
		map.put("NUM_ONE", 1);
		map.put("ONE", "1");
		map.put("OK", 0);
		//����map
		map.forEach((key, value) -> {
			System.out.println(key + "->" + value + " " + value.getClass().getName());
		});
		
		map.putIfAbsent("NUM_TWO", 2);//���key��ֵΪ��,�򽫸�key-value����map
		map.putIfAbsent("OK", 3);//����ı�key=OK��value
		map.forEach((key, value) -> {
			System.out.println(key + "->" + value);
		});
	}
}
