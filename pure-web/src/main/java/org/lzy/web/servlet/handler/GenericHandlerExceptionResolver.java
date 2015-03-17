package org.lzy.web.servlet.handler;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * 
 * @author Ching.Yang
 *
 */
public class GenericHandlerExceptionResolver extends SimpleMappingExceptionResolver {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenericHandlerExceptionResolver.class);
	
	private List<HandlerExceptionResolver> exceptionResolvers;

	public List<HandlerExceptionResolver> getExceptionResolvers() {
		return Collections.unmodifiableList(exceptionResolvers);
	}

	public void setExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		this.exceptionResolvers = exceptionResolvers;
	}

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		LOGGER.error("Catch unhandle exception", ex);
		
		if (exceptionResolvers != null) {
			for (HandlerExceptionResolver exceptionResolver : exceptionResolvers) {
				ModelAndView mav = exceptionResolver.resolveException(request, response, handler, ex);
				if (mav != null) {
					return mav;
				}
			}
		}
		
		return super.doResolveException(request, response, handler, ex);
	}
}
