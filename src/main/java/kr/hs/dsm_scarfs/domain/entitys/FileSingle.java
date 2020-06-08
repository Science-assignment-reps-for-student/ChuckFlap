package kr.hs.dsm_scarfs.domain.entitys;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "single_files")
public class FileSingle {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer userId;

    @Column
    private Integer homeworkId;

    @Column
    private String fileName;

    @Column
    private String source;

    @Column
    private Date createdAt;

    @Column
    private boolean late;

    @Builder
    public FileSingle(Integer userId, Integer homeworkId, String fileName, String source, Date createdAt, boolean late) {
        this.userId = userId;
        this.homeworkId = homeworkId;
        this.fileName = fileName;
        this.source = source;
        this.createdAt = createdAt;
        this.late = late;
    }
}
