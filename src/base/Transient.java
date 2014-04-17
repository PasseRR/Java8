package base;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * transient的作用及Serializable、Externalizable接口<BR>
 * transient型变量的值不包括在序列化的表示中，然而非transient型的变量是被包括进去的
 * @version 1.0
 * @author xiehai
 * @date 2014年4月16日 上午10:39:09 
 */
public class Transient {
	public static void main(String[] args) {
		Student student = new Student("jack", 11, "2班");
		try {
			//序列化对象
			String path = System.getProperty("user.dir") + "/src/base/config/object.txt";
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
			oos.writeObject(student);
			oos.close();
			
			//反序列化对象
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
			ois.readObject();
			ois.close();
			
			Student2 student2 = new Student2("rose", 12, "class 3");
			oos = new ObjectOutputStream(new FileOutputStream(path));
			oos.writeObject(student2);
			oos.close();
			
			ois = new ObjectInputStream(new FileInputStream(path));
			System.out.println((Student2) ois.readObject());
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 由于Externalizable对象在缺省情况下不保存它们的任何域，<BR>
	 * 所以transient关键字只能和Serializable对象一起使用 。
	 * @version 1.0
	 * @author xiehai
	 * @date 2014年4月16日 下午12:42:36 
	 */
	private static class Student implements Externalizable{
		private String name;
		private int age;
		private String clazz;
		public Student(){
			
		}
		public Student(String name, int age, String clazz){
			this.name = name;
			this.age = age;
			this.clazz = clazz;
		}
		/* (non-Javadoc)
		 * @see java.io.Externalizable#writeExternal(java.io.ObjectOutput)
		 */
		@Override
		public void writeExternal(ObjectOutput out) throws IOException {
			out.writeObject(this.name);
			out.writeObject(this.age);
			out.writeObject(this.clazz);
		}

		/* (non-Javadoc)
		 * @see java.io.Externalizable#readExternal(java.io.ObjectInput)
		 */
		@Override
		public void readExternal(ObjectInput in) throws IOException,
				ClassNotFoundException {
			this.name = (String) in.readObject();
			this.age = (int) in.readObject();
			this.clazz = (String) in.readObject();
			System.out.println(this);
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "name = " + name + ", age = " + age + ", clazz = " + clazz; 
		}
	}
	/**
	 * transient只能结合Serializable使用
	 * @version 
	 * @author xiehai
	 * @date 2014年4月16日 下午12:40:56 
	 */
	@SuppressWarnings("serial")
	private static class Student2 implements Serializable{
		private String name;
		private int age;
		private transient String clazz;
		public Student2(String name, int age, String clazz){
			this.name = name;
			this.age = age;
			this.clazz = clazz;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "name = " + name + ", age = " + age + ", clazz = " + clazz; 
		}
	}
}
