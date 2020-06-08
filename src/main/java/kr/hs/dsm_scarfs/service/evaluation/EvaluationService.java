package kr.hs.dsm_scarfs.service.evaluation;

import kr.hs.dsm_scarfs.domain.payload.request.EvaluationMutualRequest;
import kr.hs.dsm_scarfs.domain.payload.request.EvaluationSelfRequest;

public interface EvaluationService {
    void evaluationSelf(String token, EvaluationSelfRequest evaluationSelfRequest);
    void evaluationMutual(String token, EvaluationMutualRequest evaluationMutualRequest);
}
