package kr.hs.dsm_scarfs.domain.entitys;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "self_evaluations")
public class EvaluationSelf {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer userId;

    @Column
    private Integer homeworkId;

    @Column
    private Integer scientificAccuracy;

    @Column
    private Integer communication;

    @Column
    private Integer attitude;

    @Builder
    public EvaluationSelf(Integer userId, Integer homeworkId, Integer scientificAccuracy, Integer communication, Integer attitude) {
        this.userId = userId;
        this.homeworkId = homeworkId;
        this.scientificAccuracy = scientificAccuracy;
        this.communication = communication;
        this.attitude = attitude;
    }
}
