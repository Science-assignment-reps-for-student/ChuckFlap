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
@Entity(name = "multi_files")
public class FileMulti {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer teamId;

    @Column
    private Integer homeworkId;

    @Column
    private String fileName;

    @Column
    private String source;

    @Column
    private Date createdAt;

    @Builder
    public FileMulti(Integer teamId, Integer homeworkId, String fileName, String source, Date createdAt, boolean late) {
        this.teamId = teamId;
        this.homeworkId = homeworkId;
        this.fileName = fileName;
        this.source = source;
        this.createdAt = createdAt;
    }
}
