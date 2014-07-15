package base.java.lang;

import java.lang.ref.ReferenceQueue;



/**
 * Java引用类型:<BR>
 * 1.强引用(StrongReference)<BR>
 * 2.软引用(SoftReference)<BR>
 * 3.弱引用(WeakReference)<BR>
 * 4.虚引用(PhantomReference)
 * @author xiehai
 * @date 2014年6月27日 下午12:51:20 
 */
public class Reference {
	public static void main(String[] args) {
//		new StrongReference().strongReference();
//		new SoftReference().testSoftReference();
//		new WeakReference().testWeakReference();
		new WeakReference().testWeakReferenceQueue();
//		new PhantomReference().testPhantomReference();
	}
}
/**
 * 强引用<BR>
 * 如果一个对象具有强引用, 那垃圾回收器绝不会回收它。<BR>
 * 显示的置该对象为null, 则gc会回收该对象
 * @author xiehai
 * @date 2014年7月1日 下午3:05:42 
 */
class StrongReference{
	//强引用是使用最普遍的引用。
	//如果一个对象具有强引用，那垃圾回收器绝不会回收它。
	String object = new String("Hello World");
	void strongReference(){
		//当内存空间不足，Java虚拟机宁愿抛出OutOfMemoryError错误，
		//使程序异常终止，也不会靠随意回收具有强引用的对象来解决内存不足的问题。
		//如果不使用时，要通过如下方式来弱化引用
		object = null;
		//显式地设置object为null，或超出对象的生命周期范围，则gc认为该对象不存在引用，
		//这时就可以回收这个对象。具体什么时候收集这要取决于gc的算法
		
		//在一个方法的内部有一个强引用，这个引用保存在栈中，而真正的引用内容（Object）保存在堆中。
		//当这个方法运行完成后就会退出方法栈，则引用内容的引用不存在，这个Object会被回收。
		Object object = new Object();
		//object的其他操作
		if(null != object){
			//...
		}
	}
}

/**
 *  软引用<BR>
 *  如果一个对象只具有软引用, 则内存空间足够, 垃圾回收器就不会回收它;<BR>
 *  如果内存空间不足了, 就会回收这些对象的内存.<BR>
 *  只要垃圾回收器没有回收它,该对象就可以被程序使用.<BR>
 *  软引用可用来实现内存敏感的高速缓存.<BR>
 * @author xiehai
 * @date 2014年7月1日 下午3:08:13 
 */
class SoftReference{
	//这是强引用
	String string = new String("Hello World!");
	//这是软引用
	java.lang.ref.SoftReference<String> softReference = new java.lang.ref.SoftReference<String>(string);
	/**
	 *(1)如果一个网页在浏览结束时就进行内容的回收，则按后退查看前面浏览过的页面时，需要重新构建<BR>
	 *(2)如果将浏览过的网页存储到内存中会造成内存的大量浪费，甚至会造成内存溢出<BR>
	 */
	void softReference(){
		@SuppressWarnings("unused")
		String browse = null;//浏览器缓存内容
		if(null != softReference.get()){
			//网页缓存还在
			browse = softReference.get();
		}else {
			//网页缓存由于内存不够已被回收
			softReference = new java.lang.ref.SoftReference<String>(string);
			browse = softReference.get();
		}
	}
	
	void testSoftReference(){
		System.out.println(softReference.get());
		System.gc();
		int i = 0;
		while (true) {
			try {
				new Object();
				i ++;
			} catch (Exception e) {
				System.out.println("Out of memory at " + i);
				break;
			}
		}
		System.out.println(softReference.get() == null);
	}
	//软引用可以和一个引用队列（ReferenceQueue）联合使用，
	//如果软引用所引用的对象被垃圾回收器回收，
	//Java虚拟机就会把这个软引用加入到与之关联的引用队列中。
}

/**
 * 弱引用与软引用的区别在于：只具有弱引用的对象拥有更短暂的生命周期。<BR>
 * 在垃圾回收器线程扫描它所管辖的内存区域的过程中，一旦发现了只具有弱引用的对象，<BR>
 * 不管当前内存空间足够与否，都会回收它的内存。<BR>
 * 不过，由于垃圾回收器是一个优先级很低的线程，因此不一定会很快发现那些只具有弱引用的对象。<BR>
 * @author xiehai
 * @date 2014年7月14日下午4:03:37
 */
class WeakReference{
	void testWeakReference(){
		java.lang.ref.Reference<String> weakReference = new java.lang.ref.WeakReference<String>(new String("Hello World!"));
		System.out.println(weakReference.get());
		System.gc();
		System.out.println(weakReference.get());
	}
	
	/**
	 * 当一个 WeakReference 开始返回 null 时， 它所指向的对象已经准备被回收，<BR>
	 * 这时可以做一些合适的清理工作. 将一个ReferenceQueue传给一个 Reference的构造函数，<BR> 
	 * 当对象被回收时， 虚拟机会自动将这个weak ref插入到ReferenceQueue中，<BR>
	 * WeakHashMap 就是利用 ReferenceQueue 来清除 key 已经没有强引用的 <BR>
	 */
	void testWeakReferenceQueue(){
		ReferenceQueue<String> queue = new ReferenceQueue<String>();
		String string1 = new String("Hello");
		String string2 = new String("World");
		java.lang.ref.Reference<String> weakrReference1 = new java.lang.ref.WeakReference<String>(string1, queue);
		java.lang.ref.Reference<String> weakReference2 = new java.lang.ref.WeakReference<String>(string2, queue);
		System.out.println("before gc:" + queue.poll());
		string1 = null;
		string2 = null;
		System.gc();
		System.out.println((weakrReference1.isEnqueued() ? weakrReference1.isEnqueued() : weakrReference1.get()) + " " + (weakReference2.isEnqueued() ? weakReference2.isEnqueued() : weakReference2.get()));
		System.out.println("after gc:" + queue.poll());
	}
}

/**
 * Phantom Reference(虚引用)与 WeakReference和 SoftReference有很大的不同,<BR>  
 * 因为它的get() 方法永远返回 null, 这也正是它名字的由来 
 * @author xiehai
 * @date 2014年7月14日下午5:11:37
 */
class PhantomReference{
	void testPhantomReference(){
		ReferenceQueue<String> queue = new ReferenceQueue<>();
		java.lang.ref.PhantomReference<String> phantomReference = new java.lang.ref.PhantomReference<String>(new String("Hello World!"), queue);
		System.out.println(phantomReference.get());
		System.out.println(queue.poll());
	}
}

