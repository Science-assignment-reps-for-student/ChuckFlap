package kr.hs.dsm_scarfs.domain.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class TeamResponse {

    private Integer teamId;
    private Integer leaderId;
    private Integer leaderNumber;
    private String leaderName;
    private String teamName;
    private List<MemberResponse> members;

    @Builder
    public TeamResponse(Integer teamId, Integer leaderId, Integer leaderNumber, String leaderName, String teamName, List<MemberResponse> members) {
        this.teamId = teamId;
        this.leaderId = leaderId;
        this.leaderNumber = leaderNumber;
        this.leaderName = leaderName;
        this.teamName = teamName;
        this.members = members;
    }
}
