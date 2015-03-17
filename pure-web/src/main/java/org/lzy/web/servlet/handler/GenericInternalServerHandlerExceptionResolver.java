package org.lzy.web.servlet.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.lzy.core.exception.GenericInternalServerException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;


/**
 * 
 * @author ching
 *
 */
public class GenericInternalServerHandlerExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		for (Throwable throwable = ex; throwable != null; throwable = ExceptionUtils.getCause(throwable)) {
			if (GenericInternalServerException.class.isAssignableFrom(throwable.getClass())) {
				return new ModelAndView("exception", SimpleMappingExceptionResolver.DEFAULT_EXCEPTION_ATTRIBUTE,
						throwable);
			}
		}

		return null;
	}
}
