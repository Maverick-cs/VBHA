package com.example.vociebasedcotrol.utils;

import org.json.JSONObject;

public class NetUtils {
	//default ip and port of server.
	private String ip = "222.205.13.58";//your server ip
	private int port = 8080;			//your server port
	
	public boolean login(String name, String pwd){
		SocketClient client = new SocketClient(ip, port);
		
		//package data to JSONObject
		JSONObject obj = new JSONObject();
		try{
			obj.put("action", "login");
			obj.put("name", name);
			obj.put("pwd", pwd);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println(obj.toString());
		//send msg and get response from server.
		String data = client.sendMsg(obj.toString() + "\n");
		client.close();
		boolean flag = data.equals("true") ? true : false;
		if(flag){
			System.out.println("login success");
		}else{
			System.out.println("login failed");
		}
		return flag;
	}
	
	public boolean register(String name, String pwd, int age, String email){
		SocketClient client = new SocketClient(ip, port);
		
		//package data to JSONObject
		JSONObject obj = new JSONObject();
		try{
			obj.put("action", "register");
			obj.put("name", name);
			obj.put("pwd", pwd);
			obj.put("age", age);
			obj.put("email", email);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//send msg and get response from server.
		String data = client.sendMsg(obj.toString()+"\n");
		client.close();
		boolean flag = data.equals("true") ? true : false;
		if(flag){
			System.out.println("register success");
		}else{
			System.out.println("register failed, name has existed...");
		}
		return flag;
	}
	
	//send control actions for server.
	public String sendControl(String control){
		SocketClient client = new SocketClient(ip, port);
		
		//package data to JSONObject
		JSONObject obj = new JSONObject();
		try{
			obj.put("action", "control");
			obj.put("param", control);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//send msg and get response from server.
		//response data can be json, if you want json just modify the response in server.
		//in this, I just set a string for convince
		String data = client.sendMsg(obj.toString()+"\n");
		client.close();
		System.out.println(data);
		return data;		
	}

}
