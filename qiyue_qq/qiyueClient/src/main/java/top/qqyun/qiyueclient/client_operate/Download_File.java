package top.qqyun.qiyueclient.client_operate;

import top.qqyun.qiyueclient.utils.ConnectionSetUtil;

import java.io.*;
import java.net.HttpURLConnection;

/**
 * @author 邱强
 * @time 2021/10/29
 */
public class Download_File {


    public  static  String downFile(String uuid) throws IOException {
        final String dirPath = "C:\\Users\\qiangqiang\\Desktop";
        String address = "http://127.0.0.1:8080/qiyuesuo/downloadFile";
        HttpURLConnection connection = null;
        try {
            connection = ConnectionSetUtil.creatUtil(address, "GET");
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.setRequestProperty("uuid",uuid);
        connection.connect();
        if(connection.getResponseCode()==200){
            InputStream is= connection.getInputStream();
            String fileType= connection.getHeaderField("filetype");
            is.available();
            //写入服务器地址路径
            OutputStream out= null;
            try {
                out = new FileOutputStream(dirPath+uuid+fileType);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            byte[] bt=new byte[2048];
            int b = 0;
            while (true){
                try {
                    if (!((b=is.read(bt))>0)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
//              分段写入数据
                try {
                    out.write(bt,0,b);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return "success";
        }
        else{
            return connection.getResponseCode()+"";
        }
    }
}