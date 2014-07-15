package java8.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Java8 新增数据流
 * @see java.util.stream.Stream
 * @version 1.0
 * @author xiehai
 * @date 2014年3月21日 下午4:45:08 
 */
public class Stream {
	public static void main(String[] args) {
//		operation();
		mapOperation();
	}
	
	/**
	 * 数据量流的种类
	 */
	public static void streamType(){
		List<Integer> list = new LinkedList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
		//并行流
		//根据集合的大小,按照一定的方式拆分成多个小集合
		//多个集合,多个线程一起进行
		list.stream().parallel();
		//顺序流
		//不将集合拆分,一个线程进行
		list.stream();
	}
	
	/**
	 * stream的中间操作及末尾操作<BR>
	 * 线性集合List, Set
	 */
	public static void operation(){
		Integer []num = {3, 4, 5, 6, 7, 8, 9, 9, 8};
		List<Integer> list;
		list = Arrays.asList(num)
				.stream()
				.filter(item -> item > 5)//过滤大于5的数字 这里filter是中间操作
//				.count();//统计元素个数 返回long
//				.collect(Collectors.toCollection(LinkedList::new));//将结果放入一个LinkedList
				.collect(Collectors.toList());//将结果赋给list collection末尾操作
		list.forEach(item -> System.out.print(item + " "));
		System.out.println();
		
		Arrays.asList(num)
				.stream()
				.limit(5)//限制元素个数
				.forEach(item -> System.out.print(item + " "));
		System.out.println();
		
		list = Arrays.asList(num)
				.stream()
				.peek(item -> System.out.print(item + " "))//对每个元素执行一些操作
				.collect(Collectors.toList());
		System.out.println();
		
		Arrays.asList(num)
				.stream()
				.distinct()//去除重复元素
				.forEach(item -> System.out.print(item + " "));
		System.out.println();
		
		Arrays.asList(num)
				.stream()
				.sorted((i, j) -> j - i)//按照指定的Comparator排序 只是一个排序的视图 可在尾端将视图放入集合
//				.forEach(System.out::print);
				.forEach(item -> System.out.print(item + " "));
		System.out.println();
		
		Integer max = Arrays.asList(num)
				.stream()
//				.min((first, second) -> first - second)
				.max((i, j) -> i-j)//获得最大最小值 尾端操作
				.get();
		System.out.println(max);
		
		boolean flg = Arrays.asList(num)
				.stream()
				//以下均为尾端操作
//				.allMatch(item -> item > 5);//所有元素是否满足 
//				.noneMatch(item -> item > 10);//所有元素是否不满足
				.anyMatch(item -> item > 5);//是否有一个元素满足
		System.out.println(flg);
		
		Arrays.asList(num)
				.stream()
				.filter(item -> item > 5)//过滤大于5的数字
//				.map(item -> String.valueOf(item))//将数字转换成String
				.map(String::valueOf)
				.forEach(item -> System.out.println(item.getClass()));//当前视图中对象不是Integer而是String
	}
	
	/**
	 * 非线性集合操作Map
	 */
	public static void mapOperation(){
		Map<String, Object> map = new HashMap<>();
		map.put("NUM_ONE", 1);
		map.put("ONE", "1");
		map.put("OK", 0);
		//遍历map
		map.forEach((key, value) -> {
			System.out.println(key + "->" + value + " " + value.getClass().getName());
		});
		
		map.putIfAbsent("NUM_TWO", 2);//如果key的值为空,则将该key-value放入map
		map.putIfAbsent("OK", 3);//不会改变key=OK的value
		map.forEach((key, value) -> {
			System.out.println(key + "->" + value);
		});
	}
}
