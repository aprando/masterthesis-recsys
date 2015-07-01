package br.com.aprando.recommendersystem.base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;

public class FacebookGraphClientRest {

	public static final String GET = "GET";
	
	public static final String POST = "POST";
	
	private String accessToken;
	
	private String appSecret;
	
	private String version;
	
	HttpURLConnection conn = null;
	
	public FacebookGraphClientRest(String accessToken){
		this.accessToken = accessToken;
		this.appSecret = "";
		this.version = "2.1";
	}
	
	public FacebookGraphClientRest(String accessToken, String appSecret){
		this.accessToken = accessToken;
		this.appSecret = appSecret;
		this.version = "2.1";
	}

	public FacebookGraphClientRest(String accessToken, String appSecret, String version){
		this.accessToken = accessToken;
		this.appSecret = appSecret;
		this.version = version;
	}
	
	public void openConnection(String urlServico, String metodoHttp, Hashtable<String, String> params) throws Exception{
		try {
			if(conn != null)
				closeConnection();
			
			String urlGraphAPI = "https://graph.facebook.com/" + version + "/";
			String token = "?access_token=" + this.accessToken + "&appsecret_proof" + this.appSecret;
			URL url = new URL(urlGraphAPI + urlServico + token);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(metodoHttp);
			conn.setRequestProperty("Accept", "application/json");
			if(metodoHttp == POST && params != null && params.size() > 0){
				OutputStream os = conn.getOutputStream();
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
				writer.write(getPostParamString(params));
				writer.flush();
				writer.close();
				os.close();
			}
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	public void closeConnection(){
		if(conn != null)
			conn.disconnect();
		conn = null;
	}
	
	public String getMessage() throws Exception{
		if(conn != null){
			return getStringFromInputStream(conn.getInputStream());
		}
		throw new Exception("Nao foi possivel recuperar a mensagem. Connection nula. ");
	}
	
	public String get(String urlServico) throws Exception {
		openConnection(urlServico, GET, null);	
		String value = getMessage();
		closeConnection();
		return value;
	}

	public String post(String urlServico, Hashtable<String, String> params) throws Exception {
		openConnection(urlServico, POST, params);
		String value = getMessage();
		closeConnection();
		return value;
	}

	
	private String getPostParamString(Hashtable<String, String> params) {
	    if(params.size() == 0)
	        return "";

	    StringBuffer buf = new StringBuffer();
	    Enumeration<String> keys = params.keys();
	    while(keys.hasMoreElements()) {
	        buf.append(buf.length() == 0 ? "" : "&");
	        String key = keys.nextElement();
	        buf.append(key).append("=").append(params.get(key));
	    }
	    return buf.toString();
	}
	
	// convert InputStream to String
	private String getStringFromInputStream(InputStream is) {
 
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
 
		String line;
		try {
 
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
 
		return sb.toString();
 
	}	
}
