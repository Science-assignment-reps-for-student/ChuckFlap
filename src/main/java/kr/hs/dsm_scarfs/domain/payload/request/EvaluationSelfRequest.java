package kr.hs.dsm_scarfs.domain.payload.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EvaluationSelfRequest {

    private Integer homeworkId;
    private Integer scientificAccuracy;
    private Integer communication;
    private Integer attitude;

    @Builder
    public EvaluationSelfRequest(Integer homeworkId, Integer scientificAccuracy, Integer communication, Integer attitude) {
        this.homeworkId = homeworkId;
        this.scientificAccuracy = scientificAccuracy;
        this.communication = communication;
        this.attitude = attitude;
    }
}
