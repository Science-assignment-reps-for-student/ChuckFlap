package kr.hs.dsm_scarfs.domain.entitys;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "mutual_evaluations")
public class EvaluationMutual {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer userId;

    @Column
    private Integer targetId;

    @Column
    private Integer homeworkId;

    @Column
    private Integer cooperation;

    @Column
    private Integer communication;

    @Builder
    public EvaluationMutual(Integer userId, Integer targetId, Integer homeworkId, Integer cooperation, Integer communication) {
        this.userId = userId;
        this.targetId = targetId;
        this.homeworkId = homeworkId;
        this.cooperation = cooperation;
        this.communication = communication;
    }
}
