package org.lzy.core.exception;

import org.lzy.api.StatusCode;


/**
 * 通用运行时异常
 * 
 * @author yang.mq
 * 
 */
public abstract class GenericRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -700675918549997764L;

	public GenericRuntimeException() {
		super();
	}

	public GenericRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public GenericRuntimeException(String message) {
		super(message);
	}

	public GenericRuntimeException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * 获取异常状态码
	 * 
	 * @return StatusCode
	 */
	public abstract StatusCode getStatusCode();
}
