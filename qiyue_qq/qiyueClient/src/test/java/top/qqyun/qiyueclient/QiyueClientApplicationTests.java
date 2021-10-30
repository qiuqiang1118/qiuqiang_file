package top.qqyun.qiyueclient;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.qqyun.qiyueclient.client_operate.Download_File;
import top.qqyun.qiyueclient.client_operate.GetJsonData;
import top.qqyun.qiyueclient.client_operate.Upload_File;

import java.io.IOException;

@SpringBootTest
class QiyueClientApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void download() throws IOException {
//        uuid地址为下载到本地的图片
        System.out.println("Download_File= " + Download_File.downFile("1e275688-f60a-4f56-9c1d-c2019641a2b7"));
    }
    @Test
    void upload(){
        Upload_File.upload("C:\\Users\\qiangqiang\\Desktop\\07d2a391-65bb-46ed-be51-d16213a9bba3.jpg");
    }

    @Test
    void  getJson() throws Exception {
        GetJsonData.getJson("07d2a391-65bb-46ed-be51-d16213a9bba3");
    }

}
