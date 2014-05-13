package base.java.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * InetAddress类了解
 * @version 1.0
 * @author xiehai
 * @date 2014年5月4日 下午2:48:33 
 */
public class InetAddressDemo {
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws UnknownHostException {
		InetAddress inetAddress = InetAddress.getLocalHost();
		System.out.println(inetAddress.getHostName());//主机名
		System.out.println(inetAddress.getHostAddress());//主机地址
		
		InetAddress inetAddress2 = inetAddress.getByName("www.baidu.com");
		System.out.println(inetAddress2.getHostName());//主机名
		System.out.println(inetAddress2.getHostAddress());//主机地址
		
		InetAddress []inetAddresses = InetAddress.getAllByName("www.baidu.com");
		for(InetAddress inetAddres : inetAddresses){
			System.out.println(inetAddres);
		}
	}
}
