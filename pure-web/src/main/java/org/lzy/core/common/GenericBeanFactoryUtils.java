package org.lzy.core.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;

/**
 * 
 * @author Ching.Yang
 * 
 */
public final class GenericBeanFactoryUtils implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	private static MessageSourceAccessor messageSourceAccessor;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		GenericBeanFactoryUtils.applicationContext = applicationContext;
		GenericBeanFactoryUtils.messageSourceAccessor = new MessageSourceAccessor(applicationContext.getBean(MessageSource.class));
	}

	public static final Object getBean(String name) throws BeansException {
		return applicationContext.getBean(name);
	}

	public static final <T> T getBean(String name, Class<T> requiredType) throws BeansException {
		return applicationContext.getBean(name, requiredType);
	}

	public static final <T> T getBean(Class<T> requiredType) throws BeansException {
		return applicationContext.getBean(requiredType);
	}

	public static final Object getBean(String name, Object... args) throws BeansException {
		return applicationContext.getBean(name, args);
	}

	public static final MessageSourceAccessor getMessageSourceAccessor() {
		return messageSourceAccessor;
	}

	public static final void publishEvent(ApplicationEvent event) {
		applicationContext.publishEvent(event);
	}
	
	
}
