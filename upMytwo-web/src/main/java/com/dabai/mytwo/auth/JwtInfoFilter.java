package com.dabai.mytwo.auth;

import com.dabai.mytwo.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;


/**  
* @author dabai: 

* 类说明  拦截需要验证jwt信息的第二个filter 解析验证toen
*/
public class JwtInfoFilter implements Filter {

	public static ObjectMapper MAPPER=new ObjectMapper();
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("jwtnfo filter init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HashMap<String,Object>map=new HashMap<String,Object>();
		HttpServletRequest request1=(HttpServletRequest) request;
		HttpServletResponse response1=(HttpServletResponse) response;
		String token = request1.getHeader("Authorization");
		response1.setContentType("text/json;charset=UTF-8");
		if(!JwtUtil.verifyToken(token)){
			response1.setContentType("text/json;charset=UTF-8");
			map.put("code", 3);
			map.put("msg", "未登录或者,验证信息过期,请重新登陆");
			String string = MAPPER.writeValueAsString(map);
			PrintWriter writer = response1.getWriter();
			writer.write(string);
			writer.flush();
			writer.close();
			
		}else{
		  String userid = (String) JwtUtil.parseToken(token).get("userid");
		  String username = (String) JwtUtil.parseToken(token).get("username");
		  Integer roleid=(Integer) JwtUtil.parseToken(token).get("roleid");
		  String image=(String) JwtUtil.parseToken(token).get("image");
		  String signature=(String) JwtUtil.parseToken(token).get("signature");
		  map.put("roleid",roleid);
		  map.put("userid", userid);
		  map.put("username", username);
		  map.put("image", image);
		  map.put("signature", signature);
		  request1.setAttribute("data", map);
		  chain.doFilter(request1, response1);
		}
		
		
	}

	public void output(String jsonStr, HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8;");
		PrintWriter out = response.getWriter();
//		out.println();
		out.write(jsonStr);
		out.flush();
		out.close();
		
	}
	@Override
	public void destroy() {
	}

 
}
