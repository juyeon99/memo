package com.memo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.memo.common.FileManagerService;
import com.memo.interceptor.PermissionInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Autowired
	private PermissionInterceptor interceptor;
	
	// Web의 이미지 주소와 실제 파일경로를 mapping해주는 설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		.addResourceHandler("/images/**")	// 이미지의 실제 웹 주소	ex) http://localhost/images/userid_189643326/image.jpg
		.addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH);	// 실제 파일이 있는 곳
//		.addResourceLocations("file:" + FileManagerService.FILE_UPLOAD_PATH);		//(on MAC)
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor)
		.addPathPatterns("/**")		// 모든 path를 interceptor로 확인
		.excludePathPatterns("/error", "/static/**", "/user/sign_out");	// interceptor 확인 제외
	}
}
