package org.lzy.core.exception;

import org.lzy.api.StatusCode;


/**
 * 内部接口异常
 * @author Administrator
 *
 */
public class InsideInterfaceException extends GenericInternalServerException {

	private static final long serialVersionUID = -4247135575635616477L;

	public InsideInterfaceException(StatusCode statusCode, String message, Object[] args, Throwable cause) {
		super(statusCode, message, args, cause);
	}

	public InsideInterfaceException(StatusCode statusCode, String message, Object[] args) {
		super(statusCode, message, args);
	}

	public InsideInterfaceException(StatusCode statusCode, String message, Throwable cause) {
		super(statusCode, message, cause);
	}

	public InsideInterfaceException(StatusCode statusCode, String message) {
		super(statusCode, message);
	}

	public InsideInterfaceException(StatusCode statusCode, Throwable cause) {
		super(statusCode, cause);
	}

	public InsideInterfaceException(StatusCode statusCode) {
		super(statusCode);
	}
}
