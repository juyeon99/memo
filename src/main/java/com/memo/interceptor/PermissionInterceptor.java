package com.memo.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component		// Spring Bean => @Autowired 가능
public class PermissionInterceptor implements HandlerInterceptor{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());	// sysout 쓰지 말고 logger 찍기
	
	// preHandle() : Controller가 실행 전에 먼저 수행
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws IOException {
		
		// session이 있는지 확인		=> 있으면 로그인 된 상태
		HttpSession session = request.getSession();
		String userLoginId = (String) session.getAttribute("userLoginId");
		
		// URL path 확인
		String uri = request.getRequestURI();
		logger.info("#################### preHandler:{}", uri);
		
		// 로그인X && /post	=> /user/sign_in_view로 redirect
		if(userLoginId == null && uri.startsWith("/post")) {
			response.sendRedirect("/user/sign_in_view");
			return false;
		}
		
		// 로그인O && /user	=> 글 목록 페이지로 redirect
		if(userLoginId != null && uri.startsWith("/user")) {
			response.sendRedirect("/post/post_list_view");
			return false;
		}
		
		return true;	// 전과 같이 요청된 path로 Controller 수행
	}
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler, 
			ModelAndView modelAndView) {
		// URI 확인
		String uri = request.getRequestURI();
		logger.info("#################### postHandler:{}", uri);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, 
			Exception ex) {
		// URI 확인
		String uri = request.getRequestURI();
		logger.info("#################### afterCompletion:{}", uri);
	}
	
}
