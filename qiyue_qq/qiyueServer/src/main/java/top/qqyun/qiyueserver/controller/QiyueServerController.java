package top.qqyun.qiyueserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.qqyun.qiyueserver.pojo.QiyueFile;
import top.qqyun.qiyueserver.service.QiyueFileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.sql.Date;
import java.util.UUID;

/**
 * @author 邱强
 * @time 2021/10/29
 */

@Controller
@RequestMapping("/qiyuesuo")
public class QiyueServerController {

    @Autowired
    QiyueFileService qiyueFileService;

    private final Logger logger =  LoggerFactory.getLogger(getClass());

    @GetMapping("/downloadFile")
    public void download(HttpServletRequest request,HttpServletResponse response) throws IOException {
        QiyueFile qiyueFile = qiyueFileService.findQiyueFileByid(request.getHeader("uuid"));
        if(qiyueFile==null){
//            按照题目要求设置异常响应状态码
            response.setStatus(401);
            logger.info("fail");
            return;
        }
        //创建文件,设置文件地址
        File file=new File(qiyueFile.getFileSavePath());
//        获取文件输入流
        InputStream is=new FileInputStream(file);
//        获取文件输出流
        OutputStream out=new DataOutputStream(response.getOutputStream());
        response.setHeader("fileType",qiyueFile.getFileType());
        response.setHeader("uuid",qiyueFile.getUuid());
        byte[] bt=new byte[2048];
        int b;
        while ((b=is.read(bt))>0){
            //写入数据
            out.write(bt,0,b);
        }
//        写入完成关闭输入输出流
        logger.info("success");
        is.close();
        out.close();

    }

    @GetMapping("/GetJsonData")
    public QiyueFile getJson(String uuid){
        return qiyueFileService.findQiyueFileByid(uuid);
    }

    @PostMapping("/uploadFile")
    public String upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UUID uuid = UUID.randomUUID();
        String uid = uuid.toString();
        InputStream is = null;
        try {
            is = request.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final String dirPath = "C:\\Users\\qiangqiang\\Desktop\\";
        File file=new File(dirPath+request.getHeader("meTime"));
//        有文件就是用,无文件就创建一个新的文件
        if(!file.exists()){
            file.mkdir();
            logger.info("mkdir");
        }
        File newFile = new File( dirPath+request.getHeader("meTime")+uuid+request.getHeader("fileType"));
        FileOutputStream fileOutputStream=new FileOutputStream(newFile);
        byte[] bt = new byte[2048];
        int b=-1;
        while ((b=is.read(bt))!=-1){
            fileOutputStream.write(bt,0,b);
        }

        //获取对象各类数据
        Long size=Long.valueOf(request.getHeader("fileSize"));
        String type=request.getHeader("fileType");
        String url=dirPath+request.getHeader("time")+"\\"+uuid+(request.getHeader("filetype"));
        String fileName= URLDecoder.decode(request.getHeader("fileName"),"UTF-8");
        Date createTime=new Date(System.currentTimeMillis());


//        分装各类数据
        QiyueFile qiyueFile = new QiyueFile();
        qiyueFile.setFileSize(size);
        qiyueFile.setFileType(type);
        qiyueFile.setFileName(fileName);
        qiyueFile.setCreateTime(createTime);
        qiyueFile.setUuid(uid);

//        执行sql操作
        int i = qiyueFileService.insert(qiyueFile);
        logger.info("success");
        System.out.println("影响行数 " + i);
        is.close();
        fileOutputStream.close();

        return uid;
    }



}