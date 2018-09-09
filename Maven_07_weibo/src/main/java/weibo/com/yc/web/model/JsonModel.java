package weibo.com.yc.web.model;

import java.io.Serializable;

public class JsonModel implements Serializable {
	private static final long serialVersionUID = -6619653602262734182L;
	
	private Integer code;   
	private String msg;
	private Object obj;
	private String url;    //下一个操作的地址
	
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
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

	@Override
	public String toString() {
		return "JsonModel [code=" + code + ", msg=" + msg + ", obj=" + obj
				+ "]";
	}

}
