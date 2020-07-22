package com.dabai.mytwo.auth;


import com.dabai.mytwo.util.JwtUtil;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**  
* @author dabai: 

* 类说明  jwt认证
*/
public class JwtAuthFilter extends AccessControlFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest arg0, ServletResponse arg1, Object arg2) throws Exception {
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest arg0, ServletResponse arg1) throws Exception {
		HttpServletRequest request=(HttpServletRequest) arg0;
		String jwt=request.getHeader("Authorization");
		if(JwtUtil.verifyToken(jwt)){
			 UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(jwt, jwt);
			 try{
			getSubject(arg0,arg1).login(usernamePasswordToken);
			
			return true;
			 }catch(Exception e){
				 return false;
			 }
		}
		redirectToLogin(arg0,arg1);
		return false;
	}

	 /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(200);
            return false;
        }
        return super.preHandle(request, response);
    }
	

}
