package com.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {
	private Socket client = null;
	
	public SocketClient(String ip, int port){
		try {
			client = new Socket(ip, port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("=============create socket client success ===========");
	}
	
	/**
	 * send msg and get response from server.
	 * @param msg
	 * @return
	 */
	public String sendMsg(String msg){
		String response = null;
		try{
			//read response from server.
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream());
			
			out.write(msg);
			out.flush();
			
			//send data
			/*StringBuffer stb = new StringBuffer();
			String line = null;
			while((line = in.readLine()) != null && ! line.equals("END")){
				stb.append(line);
				out.write("\n");
				out.flush();
			}
			
			//read data.
			StringBuffer tem = new StringBuffer();
			line = null;
			while((line = in.readLine()) != null){
				tem.append(line);
				out.write("\n");
				out.flush();
			}
			response = tem.toString();*/
			response = in.readLine();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("server response: " + response);
		return response;
	}
	
	public void close(){
		try{
			client.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		int port = 8080;
		SocketClient socketClient = new SocketClient("127.0.0.1", port);
		socketClient.sendMsg("Hello World"  + "\n");
		socketClient.close();
		

		SocketClient socketClient2 = new SocketClient("127.0.0.1", port);
		socketClient2.sendMsg("C++\nEND");
		socketClient2.close();
	}

}
