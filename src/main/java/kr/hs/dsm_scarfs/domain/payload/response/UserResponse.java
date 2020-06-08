package kr.hs.dsm_scarfs.domain.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponse {

    private Integer userId;
    private String userEmail;
    private String userName;
    private Integer userNumber;
    private Integer userType;
    private Integer homeworkLeft;
    private Integer homeworkDone;
    private Integer homeworkOverTime;

    @Builder
    public UserResponse(Integer userId, String userEmail, String userName, Integer userNumber, Integer userType, Integer homeworkLeft, Integer homeworkDone, Integer homeworkOverTime) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userNumber = userNumber;
        this.userType = userType;
        this.homeworkLeft = homeworkLeft;
        this.homeworkDone = homeworkDone;
        this.homeworkOverTime = homeworkOverTime;
    }
}
