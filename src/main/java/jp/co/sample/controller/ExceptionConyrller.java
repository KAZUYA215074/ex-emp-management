package jp.co.sample.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExceptionConyrller implements HandlerExceptionResolver{

	private static final Logger logger = LoggerFactory.getLogger(ExceptionConyrller.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e) {
		logger.error("システムエラーが発生しました！", e);
		return new ModelAndView("redirect:/common/maintenance");
	}
}
