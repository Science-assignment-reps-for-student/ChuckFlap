package kr.hs.dsm_scarfs.service.search;

import kr.hs.dsm_scarfs.domain.entitys.User;
import kr.hs.dsm_scarfs.domain.payload.response.SearchResponse;
import kr.hs.dsm_scarfs.domain.repository.UserRepository;
import kr.hs.dsm_scarfs.exception.UserNotFoundException;
import kr.hs.dsm_scarfs.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<SearchResponse> search(String token, String searchName) {
        List<SearchResponse> searchMembers = new ArrayList<>();

        userRepository.findById(JwtUtil.parseToken(token)).orElseThrow(UserNotFoundException::new);

        for(User u : userRepository.findByUserNameIsContainingAndUserTypeOrderByUserNumber(searchName, 0)) {
            searchMembers.add(
                    SearchResponse.builder()
                    .userId(u.getId())
                    .userNumber(u.getUserNumber())
                    .userName(u.getUserName())
                    .build()
            );
        }

        return searchMembers;
    }
}
