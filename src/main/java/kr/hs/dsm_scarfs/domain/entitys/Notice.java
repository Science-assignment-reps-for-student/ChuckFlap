package kr.hs.dsm_scarfs.domain.entitys;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String noticeTitle;

    @Column(columnDefinition = "TEXT")
    private String notice;

    @Builder
    public Notice(Integer id, String noticeTitle, String notice) {
        this.id = id;
        this.noticeTitle = noticeTitle;
        this.notice = notice;
    }
}
