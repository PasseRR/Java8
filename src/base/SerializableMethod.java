package base;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

/**
 * 类实现序列化的方法
 * @version 
 * @author xiehai
 * @date 2014年4月2日 下午2:31:10 
 */
public class SerializableMethod{

}

/**
 * 序列化
 * @version 
 * @author xiehai
 * @date 2014年4月2日 下午2:52:50 
 */
@SuppressWarnings("serial")
class JavaBean1 implements Serializable{
	
}

/**
 * 串行化(外部序列化)
 * @version 
 * @author xiehai
 * @date 2014年4月2日 下午2:52:40 
 */
class JavaBean2 implements Externalizable{

	/* (non-Javadoc)
	 * @see java.io.Externalizable#writeExternal(java.io.ObjectOutput)
	 */
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(this);
	}

	/* (non-Javadoc)
	 * @see java.io.Externalizable#readExternal(java.io.ObjectInput)
	 */
	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		in.readObject();
	}
}
