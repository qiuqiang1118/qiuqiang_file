package top.qqyun.qiyueserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.qqyun.qiyueserver.pojo.QiyueFile;

/**
 * @author 邱强
 * @time 2021/10/29
 */
@Repository
public interface QiyueFileRepository extends JpaRepository<QiyueFile,Long> {
    public QiyueFile getQiyueFileByUuid(String uuid);
}