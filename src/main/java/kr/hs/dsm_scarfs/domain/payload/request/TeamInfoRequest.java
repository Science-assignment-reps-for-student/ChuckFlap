package kr.hs.dsm_scarfs.domain.payload.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamInfoRequest {

    private String teamName;
    private Integer homeworkId;

    @Builder
    public TeamInfoRequest(String teamName, Integer homeworkId) {
        this.teamName = teamName;
        this.homeworkId = homeworkId;
    }
}
