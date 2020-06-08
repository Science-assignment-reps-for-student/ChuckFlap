package kr.hs.dsm_scarfs.domain.entitys;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "teams")
public class Team {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer leaderId;

    @Column
    private Integer homeworkId;

    @Column
    private String teamName;

    @Builder
    public Team(Integer leaderId, Integer homeworkId, String teamName) {
        this.leaderId = leaderId;
        this.homeworkId = homeworkId;
        this.teamName = teamName;
    }
}
