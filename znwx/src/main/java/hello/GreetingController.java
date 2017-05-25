package hello;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@RestController
public class GreetingController {
	

 private static String token = "weixin"; 

 /*************************/
 /*连接验证*/
 /*************************/
@RequestMapping(value="/znwx",method = RequestMethod.GET)
	public String come(
			@RequestParam(value="signature") String signature,
			@RequestParam(value="timestamp") String timestamp,
			@RequestParam(value="nonce") String nonce,
			@RequestParam(value="echostr") String echostr) {

    // 与接口配置信息中的Token要一致  
        String[] arr = new String[] { token, timestamp, nonce };  
        // 将token、timestamp、nonce三个参数进行字典序排序  
        Arrays.sort(arr);  
        StringBuilder content = new StringBuilder();  
        for (int i = 0; i < arr.length; i++) {  
            content.append(arr[i]); 
        } 
        MessageDigest md = null;  
        String tmpStr = null;  
        try {  
            md = MessageDigest.getInstance("SHA-1");  
            // 将三个参数字符串拼接成一个字符串进行sha1加密  
            byte[] digest = md.digest(content.toString().getBytes());  
            tmpStr = byteToStr(digest);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
        content = null;  

        System.out.println(signature.toUpperCase());
        System.out.println(tmpStr);
        
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信  
        if(tmpStr.equals(signature.toUpperCase()))
        {
            return echostr;
        } else{
            return "hah";
        }
    }  

   

    private static String byteToStr(byte[] byteArray) {  
        String strDigest = "";  
        for (int i = 0; i < byteArray.length; i++) {  
            strDigest += byteToHexStr(byteArray[i]);  
        }  
        return strDigest;  
    }  

   

    private static String byteToHexStr(byte mByte) {  
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
        char[] tempArr = new char[2];  
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];  
        tempArr[1] = Digit[mByte & 0X0F];  
        String s = new String(tempArr);  
        return s;  
        } 


    @RequestMapping(value="/znwx",method = RequestMethod.POST)
    public String getWeiXinMessage(@RequestBody String request) throws Exception{
        String respMessage = null;
        
        System.out.println("request is:"+request);
        if (request!=null){
              return ReturnMessage.Return(request);
                   
        }
        return respMessage;
    }
}
