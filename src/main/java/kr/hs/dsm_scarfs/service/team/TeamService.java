package kr.hs.dsm_scarfs.service.team;

import kr.hs.dsm_scarfs.domain.payload.request.TeamInfoRequest;
import kr.hs.dsm_scarfs.domain.payload.response.TeamResponse;

public interface TeamService {
    TeamResponse getTeam(String token, Integer homeworkId);
    void addTeam(String token, TeamInfoRequest teamInfo);
    void deleteTeam(String token, Integer teamId);
    void addMember(String token, Integer teamId, Integer uuid);
    void deleteMember(String token, Integer teamId, Integer uuid);
}
