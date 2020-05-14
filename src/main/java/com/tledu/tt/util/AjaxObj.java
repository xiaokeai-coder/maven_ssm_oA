package com.tledu.tt.util;

public class AjaxObj {
	/**
	 * 1 表示成功
	 * 
	 * 0 表示 失败
	 */
	private int result;
	/**
	 * 处理完的提示信息
	 */
	private String msg;
	/**
	 * 附加对象
	 */
	private Object obj;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public AjaxObj(int result, String msg) {
		super();
		this.result = result;
		this.msg = msg;
	}

	public AjaxObj(int result, String msg, Object obj) {
		super();
		this.result = result;
		this.msg = msg;
		this.obj = obj;
	}

	public AjaxObj(int result) {
		super();
		this.result = result;
	}

	public AjaxObj() {
		super();
	}

}
