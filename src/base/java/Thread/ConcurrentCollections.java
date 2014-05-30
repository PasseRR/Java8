package base.java.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 并发集合
 * @author xiehai
 * @date 2014年5月23日 下午4:03:54 
 */
public class ConcurrentCollections {
	public static void main(String[] args) {
//		new MapDemo().testMapGetAndPut();
		new CopyOnWriteDemo().testCopyOnWrite();
	}
}

/**
 * 用于测试Hashtable,synchronizedMap,ConcurrentHashMap的读写性能<BR>
 * Hashtable和synchronizedMap一次只能一个线程访问hash表,当一个线程访问时,<BR>
 * 便锁了整个hash表,而ConcurrentHashMap将hash表分为数个Segment,每次只会锁将要<BR>
 * 访问的那条记录,而且ConcurrentHashMap在get的时候不会锁资源
 * @author xiehai
 * @date 2014年5月23日 下午5:31:29 
 */
class MapDemo{
	/**线程数量*/
	public static final int THREAD_COUNT = 1000;
	/**Map的大小*/
	public static final int MAP_SZIE = 1000;
	
	/**
	 * 模拟1000个并发，每个测试1000次操作，循环测试10轮
	 */
	public void testMapGetAndPut(){
		Map<Long, Integer> hashtable = new Hashtable<>();
		Map<Long, Integer> synchronizedMap = Collections.synchronizedMap(new HashMap<>());
		Map<Long, Integer> concurrentHashMap = new ConcurrentHashMap<>();
		
		long hashtableTime = 0;
		long synchronicedMapTime = 0;
		long concurrentHashMapTime = 0;
		for(int i = 0; i < 10; ++i){
			hashtableTime += testMapPut(hashtable);
			synchronicedMapTime += testMapPut(synchronizedMap);
			concurrentHashMapTime += testMapPut(concurrentHashMap);
		}
		System.out.println("hashtable put operatoration costs:" + hashtableTime + " ms");
		System.out.println("synchronizedMap put operatoration costs:" + synchronicedMapTime + " ms");
		System.out.println("concurrentHashMap put operatoration costs:" + concurrentHashMapTime + " ms");
		
		hashtableTime = synchronicedMapTime = concurrentHashMapTime = 0;
		
		for (int i = 0; i < 10; ++i){
			hashtableTime += testMapGet(hashtable);
			synchronicedMapTime += testMapGet(synchronizedMap);
			concurrentHashMapTime += testMapGet(concurrentHashMap);
		}
		System.out.println("hashtable get operatoration costs:" + hashtableTime + " ms");
		System.out.println("synchronizedMap get operatoration costs:" + synchronicedMapTime + " ms");
		System.out.println("concurrentHashMap get operatoration costs:" + concurrentHashMapTime + " ms");
	}
	
	/**
	 * 模拟THREAD_COUNT个线程并发获取map中的元素
	 * @param map
	 * @return 所需时间
	 */
	private long testMapGet(Map<Long, Integer> map){
		long start = System.currentTimeMillis();
		for(int i = 0; i < THREAD_COUNT; ++i){
			new MapGetThread(map).start();;
		}
		long end = System.currentTimeMillis();
		
		return end - start;
	}
	
	/**
	 * 模拟THREAD_COUNT个线程并发向map中放入元素
	 * @param map
	 * @return 所需时间
	 */
	private long testMapPut(Map<Long, Integer> map){
		long start = System.currentTimeMillis();
		for(int i = 0; i < THREAD_COUNT; ++i){
			new MapPutThread(map).start();;
		}
		long end = System.currentTimeMillis();
		
		return end - start;
	}
	
	/**
	 * 获取map中的元素
	 * @author xiehai
	 * @date 2014年5月26日 下午1:16:04 
	 */
	private class MapGetThread extends Thread{
		private Map<Long, Integer> map;
		public MapGetThread(Map<Long, Integer> map) {
			this.map = map;
		}
		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			for(int i = 0; i < MAP_SZIE; ++i){
				map.get(this.getId());
			}
		}
	}
	
	/**
	 * 向map中放入元素
	 * @author xiehai
	 * @date 2014年5月26日 下午1:16:21 
	 */
	private class MapPutThread extends Thread{
		private Map<Long, Integer> map;
		public MapPutThread(Map<Long, Integer> map) {
			this.map = map;
		}
		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			for(int i = 0; i < MAP_SZIE; ++i){
				map.put(getId(), i);
			}
		}
	}
}

/**
 * copy-on-write模式的集合<BR>
 * 读不需要锁,写需要锁(增加、删除),如果一个集合读多于写,可以考虑使用该集合
 * @author xiehai
 * @date 2014年5月26日 下午3:20:06 
 */
class CopyOnWriteDemo{
	private class CopyOnWriteArrayListDemo{
		private List<Integer> arrayList = new ArrayList<>();
		private List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
		
		/**
		 * 初始化list
		 */
		private void init(){
			for(int i = 1; i < 10; ++i){
				arrayList.add(i);
				copyOnWriteArrayList.add(i);
			}
		}
		
		private void clear(){
			arrayList.clear();
			copyOnWriteArrayList.clear();
		}
		
		/**
		 * 对于ArrayList，使用直接方式，一边遍历，一边删除，会报错。<BR>
		 * 解决办法一：使用迭代器，一边遍历，一边删除，不会报错。<BR>
		 * 解决办法二：使用CopyOnWriteArrayList，直接方式，一边遍历，一会删除，不会报错。
		 */
		private void condition1(){
			this.init();
			try {//直接遍历删除会抛出ConcurrentModificationException异常
				//会删除第一个满足条件的元素,然后抛出异常
				for(Integer integer : arrayList){
					if(integer.intValue()%2 == 0){
						arrayList.remove(integer);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			print(arrayList, "ArrayList直接遍历,一边遍历一边删除");
			
			//迭代器删除
			Iterator<Integer> iterator = arrayList.iterator();
			while(iterator.hasNext()){
				Integer integer = iterator.next();
				if(integer.intValue()%2 == 0){
					iterator.remove();
				}
			}
			print(arrayList, "ArrayList使用迭代器遍历,一边遍历一边删除");
			
			for(Integer integer : copyOnWriteArrayList){
				if(integer.intValue()%2 == 0){
					copyOnWriteArrayList.remove(integer);
				}
			}
			print(copyOnWriteArrayList, "CopyOnWriteArrayList直接遍历删除");
			this.clear();
		}
		
		/**
		 * 对于ArrayList，使用迭代器，一边遍历，一边add，会报错。<BR>
		 * 解决办法一：改用CopyOnWriteArrayList，直接方式，一边遍历，一边add，不会报错。<BR>
		 * 解决办法二：改用CopyOnWriteArrayList，一边遍历，一边add，不会报错。
		 */
		private void condition2(){
			this.init();
			try {//集合会添加第一个满足该条件的元素,然后抛出异常
				Iterator<Integer> iterator = arrayList.iterator();
				while (iterator.hasNext()) {
					Integer integer = iterator.next();
					if(integer.intValue()%2 == 0){
						arrayList.add(10);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			print(arrayList, "使用迭代器,向list中增加元素");
			
			//使用CopyOnWriteArrayList
			for(Integer integer : copyOnWriteArrayList){
				if(integer%2 == 0){
					copyOnWriteArrayList.add(Integer.sum(integer, 10));
				}
			}
			print(copyOnWriteArrayList, "使用CopyOnWriteArrayList直接遍历添加元素");
			
			//使用CopyOnWriteArrayList迭代器遍历增加元素
			Iterator<Integer> iterator = copyOnWriteArrayList.iterator();
			while(iterator.hasNext()){
				Integer integer = iterator.next();
				if(integer%2 == 1){
					copyOnWriteArrayList.add(Integer.sum(integer, 10));
				}
			}
			print(copyOnWriteArrayList, "使用CopyOnWriteArrayList迭代器遍历添加元素");
			this.clear();
		}
		
		/**
		 * 对于CopyOnWriteArrayList，迭代器，不能remove。<BR>
		 * 解决办法一：使用迭代器，一边遍历，一边add/remove<BR>
		 * 解决办法二：使用直接方式，一边遍历，一边add/remove
		 */
		private void condition3(){
			this.init();
			Iterator<Integer> iterator = copyOnWriteArrayList.iterator();
			try {//不会删除元素,直接抛出异常
				while(iterator.hasNext()){
					Integer integer = iterator.next();
					if(integer % 2 == 0){
						//使用方法一不会报错
//						copyOnWriteArrayList.remove(integer);
						iterator.remove();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			print(copyOnWriteArrayList, "CopyOnWriteArrayList使用迭代器遍历删除");
			
			for(Integer integer : copyOnWriteArrayList){
				if(integer%2 == 0){
					copyOnWriteArrayList.remove(integer);
				}
			}
			print(copyOnWriteArrayList, "CopyOnWriteArrayList使用直接遍历删除");
			this.clear();
		}
		
		private void print(List<Integer> list, String condition){
			System.out.println(condition);
			System.out.println(Arrays.toString(list.toArray()));
		}
		
		public void testCopyOnWriteArrayList(){
			condition1();
			condition2();
			condition3();
		}
	}
	
	public void testCopyOnWrite(){
		new CopyOnWriteArrayListDemo().testCopyOnWriteArrayList();
	}
}