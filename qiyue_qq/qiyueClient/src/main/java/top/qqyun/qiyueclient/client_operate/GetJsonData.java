package top.qqyun.qiyueclient.client_operate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import top.qqyun.qiyueclient.utils.ConnectionSetUtil;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;

/**
 * @author 邱强
 * @time 2021/10/29
 */
public class GetJsonData {

    public static com.alibaba.fastjson.JSONObject getJson(String uuid) throws Exception {
//        设置需要执行的操作地址
        String address = "http://127.0.0.1:8080/qiyuesuo/GetJsonData?uuid="+uuid;
        HttpURLConnection connection = ConnectionSetUtil.creatUtil(address, "GET");
//        打开输入流
        InputStream is= connection.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        byte [] bt=new byte[2048];
        int b=0;
        while((b=is.read(bt))!=-1){
            byteArrayOutputStream.write(bt, 0, b);
        }
        String str=byteArrayOutputStream.toString();
//        关闭输入输出流
        byteArrayOutputStream.close();
        is.close();
//        将字符串转换为json格式
        JSONObject jsonObject= JSON.parseObject(str);
        return jsonObject;

    }
}