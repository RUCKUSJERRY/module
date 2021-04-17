package com.login.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.login.dto.GoogleDto;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LoginController() {     
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		
		if (command.equals("naverlogin")) {

			String clientId = "_ux_53Q_FFO4HwCKyRH0";
			String clientSecret = "AbGIxaKvSz"; 
			String code = request.getParameter("code");
			String state = request.getParameter("state");
			String redirectURI = URLEncoder.encode("main.jsp","UTF-8");
					
			StringBuffer apiURL = new StringBuffer();
			apiURL.append("https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&");
			apiURL.append("client_id=" + clientId);
			apiURL.append("&client_secret=" + clientSecret);
			apiURL.append("&redirect_uri=" + redirectURI);
			apiURL.append("&code=" + code);
			apiURL.append("&state=" + state);
			String access_token = "";
			String refresh_token = ""; //나중에 이용합시다
					
			try { 
				  URL url = new URL(apiURL.toString());
			      HttpURLConnection con = (HttpURLConnection)url.openConnection();
			      con.setRequestMethod("GET");
			      int responseCode = con.getResponseCode();
			      BufferedReader br;
			      System.out.print("responseCode="+responseCode);
			      if(responseCode==200) { // 정상 호출
			        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			      } else {  // 에러 발생
			        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			      }
			      String inputLine;
			      StringBuffer res = new StringBuffer();
			      while ((inputLine = br.readLine()) != null) {
			        res.append(inputLine);
			      }
			      br.close();
			      if(responseCode==200) {
			    	//System.out.println(res.toString());
			    	JsonElement element = JsonParser.parseString(res.toString());
			    	JsonObject jsonData = element.getAsJsonObject();
			    				    	
			    	access_token = jsonData.get("access_token").getAsString();
			    	refresh_token = jsonData.get("refresh_token").getAsString();
			    	
			      }
			    } catch (Exception e) {
			      System.out.println(e);
			    }
				if(access_token != null) { // access_token을 잘 받아왔다면
					try {
						 String header = "Bearer " + access_token;
						 String apiurl = "https://openapi.naver.com/v1/nid/me";
						 URL url = new URL(apiurl);
						HttpURLConnection con = (HttpURLConnection)url.openConnection();
						con.setRequestMethod("GET");
						con.setRequestProperty("Authorization", header);
						int responseCode = con.getResponseCode();
						BufferedReader br;
						if(responseCode==200) { // 정상 호출
						 br = new BufferedReader(new InputStreamReader(con.getInputStream()));
						} else {  // 에러 발생
						br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
						}
						String inputLine;
						StringBuffer res = new StringBuffer();
						 while ((inputLine = br.readLine()) != null) {
						res.append(inputLine);
						}
						br.close();
						
						JsonElement element = JsonParser.parseString(res.toString());
						JsonObject jsonData = element.getAsJsonObject();
						JsonElement records = jsonData.get("response");	
												
						String id = records.getAsJsonObject().get("id").getAsString();
						String nickname = records.getAsJsonObject().get("nickname").getAsString();
						String email = records.getAsJsonObject().get("email").getAsString();						
						
						request.setAttribute("N_access_token", access_token);
						request.setAttribute("N_refresh_token", refresh_token);
						request.setAttribute("N_id", id);
						request.setAttribute("N_nickname", nickname);
						request.setAttribute("N_email", email);
						
						RequestDispatcher dispatch = request.getRequestDispatcher("main.jsp");
						dispatch.forward(request, response);
												
				    } catch (Exception e) {
				    	e.printStackTrace();
				    }
				}
			
			
		} else if (command.equals("googlelogin")) {

			String code = request.getParameter("code");
			String query = "code=" + code;
			query += "&client_id=" + "533483186463-pa8l2qj040k6tcb3so8co0in080qv3c9.apps.googleusercontent.com";
			query += "&client_secret=" + "ia3XbfrFzaMSgmyQQ2oMyKoX";
			query += "&redirect_uri=" + "http://localhost:8787/SNS_Login/LoginController?command=googlelogin";
			query += "&grant_type=authorization_code";
			String tokenJson = getHttpConnection("https://accounts.google.com/o/oauth2/token", query);
			
			//System.out.println(tokenJson.toString());
			
			Gson gson = new Gson();
			GoogleDto token = gson.fromJson(tokenJson, GoogleDto.class);
			
			String retUri = "https://www.googleapis.com/oauth2/v1/userinfo?access_token="+token.getAccess_token();
			
			String ret = getHttpConnection(retUri);
			
			//System.out.println(ret);
			
			JsonElement element = JsonParser.parseString(ret);
			JsonObject jsonData = element.getAsJsonObject();
			
			String id = jsonData.get("id").getAsString();
			String email = jsonData.get("email").getAsString();
			String picture = jsonData.get("picture").getAsString();
						
			request.setAttribute("G_id", id);
			request.setAttribute("G_email", email);
			request.setAttribute("G_picture", picture);
			request.setAttribute("G_access_token", token.getAccess_token());
			request.setAttribute("G_refresh_token", token.getRefresh_token());
			
			RequestDispatcher dispatch = request.getRequestDispatcher("main.jsp");
			dispatch.forward(request, response);	
			
			
		}
		
		
		
		
	}

	private String getHttpConnection(String uri) throws ServletException, IOException {
		URL url = new URL(uri);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		int responseCode = conn.getResponseCode();
		System.out.println(responseCode);
		String line;
		StringBuffer buffer = new StringBuffer();
		try (InputStream stream = conn.getInputStream()) {
			try (BufferedReader rd = new BufferedReader(new InputStreamReader(stream))) {
				while ((line = rd.readLine()) != null) {
					buffer.append(line);
					buffer.append('\r');
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	private String getHttpConnection(String uri, String param) throws ServletException, IOException {
		URL url = new URL(uri);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		try (OutputStream stream = conn.getOutputStream()) {
			try (BufferedWriter wd = new BufferedWriter(new OutputStreamWriter(stream))) {
				wd.write(param);
			}
		}
		int responseCode = conn.getResponseCode();
		System.out.println(responseCode);
		String line;
		StringBuffer buffer = new StringBuffer();
		try (InputStream stream = conn.getInputStream()) {
			try (BufferedReader rd = new BufferedReader(new InputStreamReader(stream))) {
				while ((line = rd.readLine()) != null) {
					buffer.append(line);
					buffer.append('\r');
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
