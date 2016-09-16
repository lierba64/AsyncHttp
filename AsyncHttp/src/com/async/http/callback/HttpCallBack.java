package com.async.http.callback;

import com.async.http.request2.BaseHttpRequest;

public interface HttpCallBack<ResponseBody> {
	/**
	 * ��ȡ������������
	 * @param total
	 */
	public void start();
	public void current(long current,long total);
	public void finish();
	public void success(ResponseBody result);
	public void fail(Exception e,ResponseBody request);
	
}