package top.qqyun.qiyueclient.client_operate;

import top.qqyun.qiyueclient.utils.ConnectionSetUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.time.LocalDate;

/**
 * @author 邱强
 * @time 2021/10/29
 */
public class Upload_File {

    public static String upload(String path){

        String address = "http://127.0.0.1:8080/qiyuesuo/uploadFile";
        HttpURLConnection connection = null;
        try {
//            设置上传链接,请求方式为post
            connection = ConnectionSetUtil.creatUtil(address, "POST");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileType=path.substring(path.indexOf("."));
        File file=new File(path);
        if (!file.exists()){
            return "指定文件路劲不存在";
        }
        connection.setRequestProperty("fileSize", String.valueOf(file.length()));
        connection.setRequestProperty("fileType",fileType);
        try {
            connection.setRequestProperty("fileName", URLEncoder.encode(file.getName(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        LocalDate today = LocalDate.now(); //获取当前日期 年月日
        //设置连接参数时间
        connection.setRequestProperty("meTime",today.toString());
        OutputStream out= null;
        try {
            out = new DataOutputStream(connection.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileInputStream fileInputStream= null;
        try {
//            设置文件输入流
            fileInputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] bt=new byte[2048];
        int b=-1;
//        读取数据
        while (true){
            try {
                if (!((b=fileInputStream.read(bt))!=-1)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
//                写入数据
                out.write(bt,0,b);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
//            关闭输入输出流
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream is= null;
        try {
            is = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int ch = 0;
        StringBuffer sb=new StringBuffer();
        while (true){
            try {
//                读入数据
                if (!((ch=is.read())!=-1)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb.append((char)ch);
        }
        System.out.println(sb.toString().trim());
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
}