package kr.hs.dsm_scarfs.domain.entitys;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "members")
public class Member {

    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer teamId;

    @Column
    private Integer userId;

    @Builder
    public Member(Integer teamId, Integer userId) {
        this.teamId = teamId;
        this.userId = userId;
    }
}
