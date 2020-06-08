package kr.hs.dsm_scarfs.controller;

import kr.hs.dsm_scarfs.domain.payload.response.SearchResponse;
import kr.hs.dsm_scarfs.service.search.SearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchServiceImpl searchService;

    @GetMapping
    public List<SearchResponse> searchMembers(@RequestHeader("Authorization") @NotNull String token,
                                              @RequestParam @NotNull String query) {
        return searchService.search(token, query);
    }
}
