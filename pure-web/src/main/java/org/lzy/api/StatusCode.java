package org.lzy.api;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 状态码
 * 
 * @author Ching.Yang
 *
 */
public enum StatusCode {

	/** 系统级：100000 － 100099 **/
	
	SC_INFORMATION(100000, "消息"),
	SC_WARNING(100001, "警告"),
	SC_ERROR(100002, "错误"),
	SC_UNAUTHORIZED(100003, "未授权错误"),
	SC_UNKNOWN_DEFINE_ERROR(100004, "未定义错误"),
	SC_IO_ERROR(100005, "IO错误"),
	SC_DUPLICATE_KEY_ERROR(100006, "重复键值错误"),
	SC_ILLEGAL_STATE_ERROR(100007, "非法状态错误"),
	SC_DATA_DICT_ERROR(100008, "数据字典错误"),
	SC_TEMPLATE_PROCESSING_ERROR(100009, "模板处理错误"),
	SC_SYSTEM_CONFIG_ERROR(100010, "系统配置错误")
	;
	
	private int code;
	private String message;
	
	private StatusCode(int code, String message) {
		this.code = code;
		this.message = message;
	}
	

	/**
	 * 编码
	 * 
	 * @return int
	 */
	public int getCode() {
		return code;
	}

	/**
	 * 描述
	 * 
	 * @return String
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * 按编码索引枚举
	 * 
	 * @param code
	 * @return ErrorCode
	 */
	public static StatusCode forCode(int code) {
		return errorCodeCache.get(code);
	}
	
	static Map<Integer, StatusCode> errorCodeCache = new HashMap<Integer, StatusCode>();
	
	static {
		for (StatusCode value : StatusCode.values()) {
			if (errorCodeCache.put(value.getCode(), value) != null) {
				throw new VerifyError(MessageFormat.format("Duplicate entry ''{0}'' for field {1}", value, value.getCode()));
			}
		}
	}
}
