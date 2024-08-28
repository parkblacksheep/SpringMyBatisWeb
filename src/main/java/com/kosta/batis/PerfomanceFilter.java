package com.kosta.batis;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

//모든 요청에 필터 적용
@WebFilter(urlPatterns = "/*")
public class PerfomanceFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//초기화 작업 
		
		
		//
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		long startTime=System.currentTimeMillis();
		//서블릿 또는 다음 필터 호출 
		chain.doFilter(request, response);
		
		//후처리 작업
		HttpServletRequest req =(HttpServletRequest)request;
		String method=req.getMethod();
		System.out.println(method+":["+req.getRequestURI()+"]");
		System.out.println("소요 시간 : "+(System.currentTimeMillis()-startTime)+"ms");
	}

	@Override
	public void destroy() {
		//정리 작업
	}

}
