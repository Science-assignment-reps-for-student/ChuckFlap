package kr.hs.dsm_scarfs.service.search;

import kr.hs.dsm_scarfs.domain.payload.response.SearchResponse;

import java.util.List;

public interface SearchService {
    List<SearchResponse> search(String token, String searchName);
}
