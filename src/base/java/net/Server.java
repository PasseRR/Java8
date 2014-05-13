package base.java.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Java通信服务器端<BR>
 * 1. 在服务器，用一个端口来实例化一个 ServerSocket对象。<BR>
 * 此时，服务器就可以这个端口时刻监听从客户端发来的连接请求。<BR>
 * 2.调用ServerSocket的accept方法，开始监听连接从端口上发来的连接请求。<BR>
 * 3.利用accept方法返回的客户端的Socket对象，进行读写IO的操作<BR>
 * 4.通讯完成后，关闭打开的流和Socket对象。<BR>
 * @author xiehai
 * @date 2014年5月5日 下午4:54:11 
 */
public class Server {
	private ServerSocket server = null;
	private BufferedReader bufferedReader = null;
	public Server(){
		try {
			receive();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void receive() throws IOException{
		server = new ServerSocket(1234);
		System.out.println("Server" + server + " is start!");
		while (true){
			Socket socket = server.accept();
			System.out.println("Accept client" + socket + "!");
			try {
				bufferedReader = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				String receive = bufferedReader.readLine();
				while (!"END".equals(receive) && null != receive) {
					System.out.println(receive);
					receive = bufferedReader.readLine();
				}
			}catch(Exception e){
				e.printStackTrace();
				throw e;
			}finally{
				if(null != socket){
					socket.close();
				}
				if(null != bufferedReader){
					bufferedReader.close();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new Server();
	}
}
