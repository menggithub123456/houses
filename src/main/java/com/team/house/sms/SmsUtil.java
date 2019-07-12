package com.team.house.sms;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**  
 * @Title: http://www.smschinese.cn/api.shtml
 * @date 2011-3-22
 * @version V1.2  
 */
public class SmsUtil {
	
	//用户名
	private static String Uid = "k1314sgs";
	
	//接口安全秘钥
	private static String Key = "d41d8cd98f00b204e980";
	
	//手机号码，多个号码如13800000000,13800000001,13800000002
	//private static String smsMob = "15071390524";
	
	//短信内容
	//private static String smsText = "验证码：8888";
	
	public static int smsload(String smsMob,String smsText) {
		//smsMob 手机号  smsText 短信内容
		HttpClientUtil client = HttpClientUtil.getInstance();
		int result = client.sendMsgGbk(Uid, Key, smsText, smsMob );
		return result;
	}
}
