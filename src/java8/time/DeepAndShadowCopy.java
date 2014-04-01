/**
 * 
 */
package java8.time;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 深拷贝和浅拷贝
 * @version 
 * @author xiehai
 * @date 2014年3月31日 下午2:32:32 
 */
public class DeepAndShadowCopy {
	/**
	 * 串行化深拷贝
	 * @param object
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object deepClone(Object object) throws IOException, ClassNotFoundException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(object);
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bais);
		return ois.readObject();
	}
	
	public static void main(String[] args) {
		Father father = new Father();
		father.setName("李四");
		Person person = new Person();
		person.setName("张三");
		person.setFather(father);
		System.out.println(person);
		System.out.println("deep copy=========>");
		Person person2 = (Person) person.deepClone();
		person2.name = "王五";
		person2.father.name = "赵六";
		System.out.println(person2);
		
		System.out.println("shadow copy=========>");
		Person person3 = new Person(){{
			setFather(new Father(){{
				setName("mike");
			}});
			setName("joe");
		}};
		System.out.println("person3" + person3);
		Person person4 = (Person) person3.clone();
		person4.father.name = "司马八";
		person4.name = "燕七";
		//浅拷贝会改变自定义对象的值 但不会包装类(String,Integer)的值
		System.out.println("person3" + person3);
		System.out.println("person4" + person4);
	}
}
class Person implements Cloneable{
	String name;
	Father father;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the father
	 */
	public Father getFather() {
		return father;
	}
	/**
	 * @param father the father to set
	 */
	public void setFather(Father father) {
		this.father = father;
	}
	
	/**
	 * 浅克隆
	 */
	public Object clone(){
		Person person = null;
		try {
			person = (Person) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return person;
	}
	
	public Object deepClone(){
		Person person = null;
		try {
			person = (Person) super.clone();
			person.father = (Father) father.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return person;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.name + " " + this.father.name;
	}
}

class Father implements Cloneable{
	String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.name;
	}
}
