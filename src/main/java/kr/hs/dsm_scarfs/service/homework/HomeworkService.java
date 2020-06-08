package kr.hs.dsm_scarfs.service.homework;

import kr.hs.dsm_scarfs.domain.payload.response.HomeworkResponse;

import java.util.List;

public interface HomeworkService {
    List<HomeworkResponse> getHomeworks(String token) throws NoSuchMethodException;
}
