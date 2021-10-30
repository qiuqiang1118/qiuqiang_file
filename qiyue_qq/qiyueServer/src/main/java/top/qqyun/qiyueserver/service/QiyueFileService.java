package top.qqyun.qiyueserver.service;

import top.qqyun.qiyueserver.pojo.QiyueFile;

/**
 * @author 邱强
 * @time 2021/10/29
 */
public interface QiyueFileService {
    public int insert(QiyueFile qiyueFile);
    public QiyueFile findQiyueFileByid(String uuid);
}