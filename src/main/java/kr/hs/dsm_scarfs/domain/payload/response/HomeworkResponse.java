package kr.hs.dsm_scarfs.domain.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;

@Getter
public class HomeworkResponse {

    private Integer id;
    private String homeworkTitle;
    private Integer homeworkType;
    private Date homework_deadline;
    private boolean submissionStatus;
    private Date created_at;
    private boolean hasTeam;

    @Builder
    public HomeworkResponse(Integer id, String homeworkTitle, Integer homeworkType, Date homework_deadline, boolean submissionStatus, Date created_at, boolean hasTeam) {
        this.id = id;
        this.homeworkTitle = homeworkTitle;
        this.homeworkType = homeworkType;
        this.homework_deadline = homework_deadline;
        this.submissionStatus = submissionStatus;
        this.created_at = created_at;
        this.hasTeam = hasTeam;
    }
}
