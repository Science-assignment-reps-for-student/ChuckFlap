package kr.hs.dsm_scarfs.domain.payload.response;

import lombok.Builder;
import lombok.Getter;


@Getter
public class SearchResponse {

    private Integer userId;
    private Integer userNumber;
    private String userName;

    @Builder
    public SearchResponse(Integer userId, Integer userNumber, String userName) {
        this.userId = userId;
        this.userNumber = userNumber;
        this.userName = userName;
    }
}
