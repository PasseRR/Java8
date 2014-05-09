package java8.net;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * URLConnection
 * @author xiehai
 * @date 2014年5月7日 下午2:40:53 
 */
public class URLConnectionDemo {
	public static void main(String[] args) {
		try {
			URL url = new URL("http://www.baidu.com");
			URLConnection connection = url.openConnection();
			connection.connect();
			int i = 1;
			String key;
			String value;
			//URL头信息域
			while (null != (key = connection.getHeaderFieldKey(i))) {
				value = connection.getHeaderField(i);
				System.out.println("key = " + key + ", value = " + value);
				i ++;
			}
			InputStream in = connection.getInputStream();
			BufferedReader out = new BufferedReader(new InputStreamReader(in));
			FileWriter fileWriter = new FileWriter(
					System.getProperty("user.dir") + "//src//base//config//baidu.html");
			PrintWriter printWriter = new PrintWriter(fileWriter);
			String line = null;
			//将指定URL文件的内容写到本地
			while(null != (line = out.readLine())){
				printWriter.write(line);
			}
			printWriter.close();
			fileWriter.close();
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
