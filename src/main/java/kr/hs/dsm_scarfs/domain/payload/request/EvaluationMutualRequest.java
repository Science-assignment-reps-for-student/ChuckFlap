package kr.hs.dsm_scarfs.domain.payload.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EvaluationMutualRequest {

    private Integer homeworkId;
    private Integer targetUuid;
    private Integer cooperation;
    private Integer communication;

    @Builder
    public EvaluationMutualRequest(Integer homeworkId, Integer targetUuid, Integer cooperation, Integer communication) {
        this.homeworkId = homeworkId;
        this.targetUuid = targetUuid;
        this.cooperation = cooperation;
        this.communication = communication;
    }
}
