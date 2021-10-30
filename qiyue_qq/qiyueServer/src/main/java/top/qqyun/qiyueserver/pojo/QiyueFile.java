package top.qqyun.qiyueserver.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author 邱强
 * @time 2021/10/29
 */
@Data
@Entity
@Table(name = "qy_file")
public class QiyueFile {

    @Id
    @GeneratedValue
    private Long id;
    private String uuid;
    private String fileName;
    private double fileSize;
    private String fileType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    private String fileSavePath;
}