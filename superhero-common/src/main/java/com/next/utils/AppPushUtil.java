package com.next.utils;

import java.util.ArrayList;
import java.util.List;

import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.style.Style0;

public class AppPushUtil {

	//定义常量, appId、appKey、masterSecret 采用本文档 "第二步 获取访问凭证 "中获得的应用配置
	private static String appId = "MuPAEm7fmi8S3OFLVwYkr5";
	private static String appKey = "xqCpWGlMWY9X8YSRPv7eJ7";
	private static String masterSecret = "DuYy91mAx7Ac2yGcpRc7o8";
	private static String url = "http://sdk.open.api.igexin.com/apiex.htm";
	
	public static void main(String[] args) {

		// 1. 声明个推
		IGtPush push = new IGtPush(url, appKey, masterSecret);
		
		// 2. 创建通知模板
		NotificationTemplate template = getNotificationTemplate(appId, appKey);
		
		// 3. 定义发送的目标app列表
		List<String> appIds = new ArrayList<String>();
		appIds.add(appId);
		
		// 4. 定义消息对象
		AppMessage message = new AppMessage();
		message.setData(template);
		message.setAppIdList(appIds);
		message.setOffline(true);
		message.setOfflineExpireTime(1000 * 600);
		
		// 5. 执行推送
		push.pushMessageToApp(message);
	}
	
	private static NotificationTemplate getNotificationTemplate(String appId, String appKey) {
		NotificationTemplate template = new NotificationTemplate();
		
		// 为模板设置appid和appkey
		template.setAppId(appId);
		template.setAppkey(appKey);
		
		Style0 style = new Style0();
		style.setText("我爱你");
		style.setTitle("我是你老公");
		
		template.setStyle(style);
		
		return template;
	}

}
