package util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Java8 Collection接口新增的default方法(包括父接口Iterable)
 * @version 1.0
 * @author xiehai
 * @date 2014年3月20日 下午1:37:50 
 */
public class Collection {
	public static void main(String[] args) {
//		forEach();
		removeIf();
	}
	
	/**
	 * 集合forEach
	 * @see java.lang.Iterable
	 */
	public static void forEach(){
		String []arrays = {"hello", "world"};
		//遍历数组
		Arrays.asList(arrays).forEach(str -> {
			System.out.println(str);
		});
		
		//顺序集合遍历
		//顺序集合forEach来自Iterable接口
		List<String> list = new LinkedList<String>();
		list.add(arrays[0]);
		list.add(arrays[1]);
		list.forEach(item -> {
			System.out.println(item);
		});
		
		//Java以后泛型简化,推断类型不用重复写泛型结果
		Map<String, Object> map = new HashMap<>();
		map.put("123", "hello");
		map.put("321", "world");
		//映射forEach来自Map接口
		map.forEach((key, value) -> {
			System.out.println(key + " " + value);
		});
	}
	
	/**
	 * 删除元素
	 * @see java.util.Collection
	 */
	public static void removeIf(){
		List<String> list = new LinkedList<String>(Arrays.asList("hello", "world", "!"));
//		list.removeIf((item) -> {
//			return item.length() > 1;
//		});
		//若返回语句只有一条,则可以省略成如下：
		list.removeIf((item) -> item.length() > 1);//若元素长度大于1则删除该元素
		list.forEach((item) -> System.out.println(item));
		//another example
		List<Integer> anotherList = new LinkedList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
		anotherList.removeIf((item) -> item < 3);//删除小于3的元素
		anotherList.forEach((item) -> System.out.println(item));
	}
}
