package java8.net;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * URL(Uniform Resource Locator:统一资源定位器)<BR>
 * URL格式:<协议名://资源名><BR>
 * 协议名:指明获取资源所用的传输协议<BR>
 * @author xiehai
 * @date 2014年5月6日 上午11:35:57 
 */
public class UrlDemo {
	public static void main(String[] args) throws MalformedURLException{
		//http://home.netscape.com : 80/home/white_paper.html#intro_1
		//协议    主机域名(IP地址)    端口号 目录     文件名                           HTML参考点
		
		//字符串URL地址
		URL url = new URL("https://www.baidu.com");
		System.out.println(url);
		//缺省端口(80)      协议          主机                                    /目录/文件
		URL url2 = new URL("http", "www.baidu.com", "/index.html");
		System.out.println(url2);
		//非缺省端口			协议		主机			端口		/目录/文件
		URL url3 = new URL("http", "localhost", 8080, "/index.jsp");
		System.out.println(url3);
		//相对URL 协议主机端口不变,替换目录文件
		URL url4 = new URL(url3, "/admin/index.html");
		System.out.println(url4);
		
		System.out.println("protocol = " + url.getProtocol());
		System.out.println("host = " + url.getHost());
		int port = url.getPort();//若端口缺省为-1
		System.out.println("port = " + (-1 == port ? 80 : port));
		System.out.println(url.toExternalForm());//url的字符串形式
	}
}
