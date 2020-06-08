package kr.hs.dsm_scarfs.domain.entitys;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "homeworks")
public class Homework {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Date homework_1_deadline;
    @Column
    private Date homework_2_deadline;
    @Column
    private Date homework_3_deadline;
    @Column
    private Date homework_4_deadline;

    @Column
    private String homeworkTitle;

    @Column
    private String homeworkDescription;

    @Column
    private Integer homeworkType;

    @Column
    private Date createdAt;

    public Homework(Date homework_1_deadline, Date homework_2_deadline,
                    Date homework_3_deadline, Date homework_4_deadline,
                    String homeworkTitle, String homeworkDescription, Integer homeworkType, Date createdAt) {
        this.homework_1_deadline = homework_1_deadline;
        this.homework_2_deadline = homework_2_deadline;
        this.homework_3_deadline = homework_3_deadline;
        this.homework_4_deadline = homework_4_deadline;
        this.homeworkTitle = homeworkTitle;
        this.homeworkDescription = homeworkDescription;
        this.homeworkType = homeworkType;
        this.createdAt = createdAt;
    }
}
