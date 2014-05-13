package base.java.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Java通信 客户端<BR>
 * 1.用服务器的IP地址和端口号实例化Socket对象。<BR>
 * 2.调用connect方法，连接到服务器上。<BR>
 * 3.将发送到服务器的IO流填充到IO对象里，比如BufferedReader/PrintWriter。<BR>
 * 4.利用Socket提供的getInputStream和getOutputStream方法，通过IO流对象，向服务器发送数据流。<BR>
 * 5.通讯完成后，关闭打开的IO对象和Socket。<BR>
 * @author xiehai
 * @date 2014年4月30日 下午4:22:53 
 */
public class Client {
	private Socket socket = null;
	private BufferedReader bufferedReader = null;
	private PrintWriter printWriter = null;
	public Client(){
		try {
			send();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void send() throws IOException{
		try {
			socket = new Socket("localhost", 1234);
			System.out.println(socket + " is start!");
			bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			printWriter = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(socket.getOutputStream())), true);
			String in = bufferedReader.readLine();
			while (!"END".equals(in)) {
				printWriter.println(in);
				in = bufferedReader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			close();
		}
	}
	
	private void close() throws IOException{
		if(null != socket){
			socket.close();
		}
		if(null != bufferedReader){
			bufferedReader.close();
		}
		if(null != printWriter){
			printWriter.close();
		}
	}
	
	public static void main(String[] args) {
		new Client();
	}
}
