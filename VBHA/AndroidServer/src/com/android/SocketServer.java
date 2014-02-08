package com.android;

import java.io.*;
import java.net.*;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.utils.User;
import com.android.utils.UserDao;

 public class SocketServer {
    
    ServerSocket sever;
    
    public SocketServer(int port){
        try{
            sever = new ServerSocket(port);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void beginListen(){
        while(true){
            try{
                final Socket socket = sever.accept();
                System.out.println("receiver a socket");
                new Thread(new Runnable(){
                    public void run(){
                        BufferedReader in;
                        try{
                            in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
                            PrintWriter out = new PrintWriter(socket.getOutputStream());
                            //StringBuffer stb = new StringBuffer();
                            if(!socket.isClosed()){
                            	String line = null;
                            	line = in.readLine();
                            	/*String line = null;
                            	while((line = in.readLine()) != null && ! line.equals("END")){
                            		stb.append(line);
                            		out.write("ok\n");
                            		out.flush();
                            	}
                            	out.write("END\n");
                            	out.flush();*/
                            	
                            	System.out.println("client send: " + line);
                            	
                            	//handle the client request and send response.
                            	String data = handle(line);
                            	//write reponse to client.
                            	out.write(data + "\n");
                            	//out.write("status ok\n");
                            	out.flush();
                            	out.close();
                            }
                            //in.close();
                            socket.close();
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    
    /**
     * handle android client request. and response data to client.
     * @param data
     * @return
     */
    private String handle(String data){
    	String response = "";
    	try {
			JSONObject obj = new JSONObject(data);
			//get action
			String action = obj.getString("action");
			System.out.println("action: " + action);
			
			if(action.equals("login")){//request login
				String name = obj.getString("name");
				String pwd = obj.getString("pwd");
				boolean flag = new UserDao().login(name, pwd);
				response = flag + "";
			}
			else if(action.equals("register")){//request register
				String name = obj.getString("name");
				String pwd = obj.getString("pwd");
				int age = obj.getInt("age");
				String email = obj.getString("email");
				
				User user = new User(name, pwd, age, email);
				boolean flag = new UserDao().register(user);
				response = flag + "";
			}
			else if(action.equals("control")){//for control
				//get param
				String param = obj.getString("param");
				System.out.println("control: " + param);
				
				/*
				 * send param(control data from client) to another device.
				 *	your code is below.
				 */
				
				// then response data to android client here
				response = "status 200";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return response;
    }
    
    public static void main(String[] args){
    	int port = 8080;
    	SocketServer server = new SocketServer(port);
    	System.out.println("create server socket in port: " + port);
    	server.beginListen();
    }
}