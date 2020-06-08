package kr.hs.dsm_scarfs.controller;

import kr.hs.dsm_scarfs.domain.payload.request.TeamInfoRequest;
import kr.hs.dsm_scarfs.domain.payload.response.TeamResponse;
import kr.hs.dsm_scarfs.service.team.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamServiceImpl teamService;

    @GetMapping
    public TeamResponse getTeam(@RequestHeader("Authorization") @NotNull String token, @RequestParam Integer homeworkId) {
        return teamService.getTeam(token, homeworkId);
    }

    @PostMapping
    public void addTeam(@RequestHeader("Authorization") @NotNull String token,
                        @RequestBody @NotNull TeamInfoRequest teamInfoRequest) {
        teamService.addTeam(token, teamInfoRequest);
    }

    @DeleteMapping("/{teamId}")
    public void deleteTeam(@RequestHeader("Authorization") @NotNull String token,
                           @PathVariable Integer teamId) {
        teamService.deleteTeam(token, teamId);
    }
}
