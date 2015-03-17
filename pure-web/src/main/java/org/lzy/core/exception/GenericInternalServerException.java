package org.lzy.core.exception;

import org.apache.commons.lang.ArrayUtils;
import org.lzy.api.StatusCode;
import org.lzy.core.common.GenericBeanFactoryUtils;
import org.springframework.context.support.MessageSourceAccessor;


/**
 * 通用内部服务器异常
 * 
 * @author yang.mq
 * 
 */
public abstract class GenericInternalServerException extends GenericRuntimeException {

	private static final long serialVersionUID = -1838593541261373696L;

	private StatusCode statusCode;

	public GenericInternalServerException(StatusCode statusCode) {
		super(translate(statusCode.getMessage(), ArrayUtils.EMPTY_OBJECT_ARRAY));
		this.statusCode = statusCode;
	}

	public GenericInternalServerException(StatusCode statusCode, Throwable cause) {
		super(translate(statusCode.getMessage(), ArrayUtils.EMPTY_OBJECT_ARRAY), cause);
		this.statusCode = statusCode;
	}

	public GenericInternalServerException(StatusCode statusCode, String message) {
		super(translate(message, ArrayUtils.EMPTY_OBJECT_ARRAY));
		this.statusCode = statusCode;
	}

	public GenericInternalServerException(StatusCode statusCode, String message, Throwable cause) {
		super(translate(message, ArrayUtils.EMPTY_OBJECT_ARRAY), cause);
		this.statusCode = statusCode;
	}

	public GenericInternalServerException(StatusCode statusCode, String message, Object[] args) {
		super(translate(message, args));
		this.statusCode = statusCode;
	}

	public GenericInternalServerException(StatusCode statusCode, String message, Object[] args, Throwable cause) {
		super(translate(message, args), cause);
		this.statusCode = statusCode;
	}

	/**
	 * 状态代码
	 * 
	 * @return StatusCode
	 */
	public StatusCode getStatusCode() {
		return statusCode;
	}

	/**
	 * 
	 * @param message
	 * @param args
	 * @return String
	 */
	private static String translate(String message, Object[] args) {
		MessageSourceAccessor msa = GenericBeanFactoryUtils.getMessageSourceAccessor();
		if(msa!=null){
			String str=msa.getMessage(message, args);
			return  str;
		}else{
			String str=String.format(message, args);
			return str;
		}
//		return msa != null ? msa.getMessage(message, args) : String.format(message, args);
	}
}
