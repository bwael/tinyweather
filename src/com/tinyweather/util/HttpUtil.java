package com.tinyweather.util;

import java.net.HttpURLConnection;

public class HttpUtil {
	public interface HttpCallbackListener{
		void onFinish(String response);
		
		void onError(Exception e);
	}
	
	public static void sendHttpRequest(final String address,
			final HttpCallbackListener listener){
		new Thread(new Runnable(){
			@Override
			public void run(){
				HttpURLConnection connection = null;
				try{
					//TO DO
				}
				catch(Exception e){
					//TO DO
				}
				finally{
					//TO DO
				}
			}
		}).start();
	}
}
