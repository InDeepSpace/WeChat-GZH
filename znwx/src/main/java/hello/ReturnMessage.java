package hello;

import java.util.Map;

public class ReturnMessage {
	
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";
	
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
	
	public static String Return(String request) throws Exception{
		
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			
			String fromUserName = requestMap.get("FromUserName");
			String toUserName = requestMap.get("ToUserName");
			String msgType = requestMap.get("MsgType");
			String content = requestMap.get("Content");
			
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime("123");
			
		//请求文本 返回文本
		if(msgType.equals(REQ_MESSAGE_TYPE_TEXT)){
				textMessage.setMsgType(RESP_MESSAGE_TYPE_TEXT);
			if("1".equals(content)){
			textMessage.setContent("欢迎关注");
			}else if("2".equals(content)){
				textMessage.setContent("请持续关注更多内容");
			}else {
				textMessage.setContent("你好!"+content);
			}
		}
		
		//请求图片 返回图文
		if(msgType.equals(REQ_MESSAGE_TYPE_IMAGE)){
			textMessage.setMsgType(RESP_MESSAGE_TYPE_TEXT);
			textMessage.setContent("别发图片");	
	}
		
		return MessageUtil.textMessageToXml(textMessage);
	}
}
