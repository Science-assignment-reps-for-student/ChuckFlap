package kr.hs.dsm_scarfs.controller;

import kr.hs.dsm_scarfs.domain.payload.request.EvaluationMutualRequest;
import kr.hs.dsm_scarfs.domain.payload.request.EvaluationSelfRequest;
import kr.hs.dsm_scarfs.service.evaluation.EvaluationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EvaluationController {

    @Autowired
    private EvaluationServiceImpl evaluationService;

    @PostMapping("evaluation-self")
    public void evaluationSelf(@RequestHeader("Authorization") String token,
                               @RequestBody EvaluationSelfRequest evaluationSelfRequest) {
        evaluationService.evaluationSelf(token, evaluationSelfRequest);
    }

    @PostMapping("evaluation-mutual")
    public void evaluationMutual(@RequestHeader("Authorization") String token,
                               @RequestBody EvaluationMutualRequest evaluationMutualRequest) {
        evaluationService.evaluationMutual(token, evaluationMutualRequest);
    }

}
