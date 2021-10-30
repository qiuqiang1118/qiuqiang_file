package top.qqyun.qiyueclient.utils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author 邱强
 * @time 2021/10/29
 */
public class ConnectionSetUtil {

    public static HttpURLConnection creatUtil(String request, String method) throws Exception {
        URL url=new URL(request);
        /*统一资源*/
        URLConnection urlConnection=url.openConnection();
        /*http连接类*/
        HttpURLConnection httpURLConnection= (HttpURLConnection) urlConnection;
        //输入流设置
        httpURLConnection.setDoInput(true);
        //输出流设置
        httpURLConnection.setDoOutput(true);
        if(method.equals("POST"))
        {
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
        }else {
            httpURLConnection.setRequestMethod("GET");
        }
        // 设置字符编码
        httpURLConnection.setRequestProperty("Charset", "UTF-8");
        // 设置长连接
        httpURLConnection.setRequestProperty("Connection", "Keep-Alive");

        return httpURLConnection;
    }

}