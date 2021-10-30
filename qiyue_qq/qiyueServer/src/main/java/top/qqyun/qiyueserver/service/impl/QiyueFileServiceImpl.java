package top.qqyun.qiyueserver.service.impl;

import top.qqyun.qiyueserver.dao.QiyueFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.qqyun.qiyueserver.pojo.QiyueFile;
import top.qqyun.qiyueserver.service.QiyueFileService;

/**
 * @author 邱强
 * @time 2021/10/29
 */
@Service
public class QiyueFileServiceImpl implements QiyueFileService {

    @Autowired
    QiyueFileRepository qiyueFileRepository;

    @Override
    public int insert(QiyueFile qiyueFile) {
        qiyueFileRepository.save(qiyueFile);
        return 0;
    }

    @Override
    public QiyueFile findQiyueFileByid(String uuid) {
        QiyueFile qiyueFileByUuid = qiyueFileRepository.getQiyueFileByUuid(uuid);
        return qiyueFileByUuid;
    }
}