package java8.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Java通信 客户端
 * @version 
 * @author xiehai
 * @date 2014年4月30日 下午4:22:53 
 */
public class Client {
	private Socket socket = null;
	private BufferedReader bufferedReader = null;
	private PrintWriter printWriter = null;
	public Client(){
		
	}
	
	public void send() throws IOException{
		try {
			socket = new Socket("localhost", 1234);
			bufferedReader = new BufferedReader(new InputStreamReader(System.in));
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
}
