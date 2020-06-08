package kr.hs.dsm_scarfs.controller;

import kr.hs.dsm_scarfs.service.team.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private TeamServiceImpl teamService;

    @PostMapping("/{uuid}")
    public void addMember(@RequestHeader(value = "Authorization") @NotNull String token,
                          @PathVariable Integer uuid, @RequestParam("teamId") Integer teamId) {
        teamService.addMember(token, teamId, uuid);
    }

    @DeleteMapping("/{uuid}")
    public void deleteMember(@RequestHeader(value = "Authorization") @NotNull String token,
                             @PathVariable Integer uuid, @RequestParam("teamId") Integer teamId) {
        teamService.deleteMember(token, teamId, uuid);
    }
}
