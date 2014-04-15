package base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


/**
 * 集合和数组排序<BR>
 * 统计名字出现的次数,按出现的次数降序排序<BR>
 * 若次数相同,按名字字典升序排序
 * @version 
 * @author xiehai
 * @date 2014年4月10日 下午2:13:05 
 */
public class CollectionAndArraysSort {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Integer []arrays = new Integer[]{1, 2, 3, 4, 5, 6, 7, 0, -1, -3};
		List<Integer> list = Arrays.asList(arrays);
		
		//数组排序
		System.out.println("-------------Arrays Sort---------------");
		//数组排序方法 默认是升序
		Arrays.sort(arrays);
		System.out.println("ASC:\t" + Arrays.toString(arrays));
		//或者使用Comparator降序
		Arrays.sort(arrays, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		System.out.println("DESC:\t" + Arrays.toString(arrays));
		//可以使用Java8的lambda表达式 升序
		Arrays.sort(arrays, (i1, i2) -> i1 - i2);
		System.out.println("ASC:\t" + Arrays.toString(arrays));
		
		//List排序
		System.out.println("---------------List Sort----------------");
		//集合List排序 默认升序
		Collections.sort(list);
		System.out.println("ASC:\t" + list);
		//或者使用Comparable降序
		Collections.sort(list, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		System.out.println("DESC:\t" + list);
		//或者使用lambda表达式
		Collections.sort(list, (i, j) -> i - j);
		System.out.println("ASC:\t" + list);
		
		//Set排序
		System.out.println("---------------Set Sort----------------");
		//集合Set排序 升序 考虑使用TreeSet
		Set<Integer> treeSet = new TreeSet<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		//将list中的元素加入set
		treeSet.addAll(list);
		System.out.println("ASC:\t" + treeSet);
		//使用Lambda表达式 降序
		Set<Integer> treesSet2 = new TreeSet<>((o1, o2) -> o2 - o1);
		treesSet2.addAll(list);
		System.out.println("DESC:\t" + treesSet2);
		
		//Map按Key排序
		System.out.println("---------------Map Sort By Key----------------");
		Map<String, Integer> map = new TreeMap<>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);//按名字字典升序排序
			}
		});
		for(int i = 0; i < data.length; ++i){
			if(map.containsKey(data[i])){
				int count = map.get(data[i]);
				map.put(data[i], ++count);
			}else {
				map.put(data[i], 1);
			}
		}
		System.out.println(map);
		
		//Map按Value排序
		System.out.println("---------------Sort Map By Value----------------");
		Map<String, Integer> map2 = new LinkedHashMap<String, Integer>();
		for(int i = 0; i < data.length; ++i){
			if(map2.containsKey(data[i])){
				int count = map2.get(data[i]);
				map2.put(data[i], ++count);
			}else {
				map2.put(data[i], 1);
			}
		}
		List<Map.Entry<String, Integer>> persons = new ArrayList<Map.Entry<String,Integer>>(map2.entrySet());
		Collections.sort(persons, new Comparator<Map.Entry<String, Integer>>(){
			@Override
			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2) {
				int i1 = o1.getValue();
				int i2 = o2.getValue();
				if(i1 == i2){
					return o1.getKey().compareTo(o2.getKey());
				}else {
					return i2 - i1;
				}
			}
			
		});
		//或者lambda表达式
		Collections.sort(persons, (Entry<String, Integer> o1, Entry<String, Integer> o2) -> {
			int i1 = o1.getValue();
			int i2 = o2.getValue();
			if(i1 == i2){
				return o1.getKey().compareTo(o2.getKey());
			}else {
				return i2 - i1;
			}
		});
		System.out.println(persons);
		//将List转换为Map
		Iterator<?> iterator = persons.iterator();
		Map.Entry<String, Integer> tmp = null; 
		map2.clear();
		while (iterator.hasNext()) {
			tmp = (Map.Entry<String, Integer>) iterator.next();
			map2.put(tmp.getKey(), tmp.getValue());
		}
		System.out.println(map2);
	}
	
	static String []data = new String[]{
			"John",
			"John",
			"John",
			"Lily",
			"Lucy",
			"James",
			"James",
			"James",
			"James",
			"James",
			"Wade",
			"Kobe",
			"Durante",
			"Durante",
			"Westbrook",
			"Nash",
			"Nash",
			"Nash",
			"Nash",
			"Hanmeimei",
			"Lilei",
			"Lilei",
			"Jack"
	};
}
