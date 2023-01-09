package cp.demo.service;

import cp.demo.domain.entity.UserEntity;
import cp.demo.domain.repository.UserRepository;
import cp.exception.JoinErrorResult;
import cp.exception.JoinException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JoinException joinException;

    /**
     * 회원가입 서비스
     *
     * @param userId
     * @param password
     * @return
     */
    public UserEntity join(String userId, String password){
        final Optional<UserEntity> result = userRepository.findById(userId);
        if (result.isPresent()) {
            throw new JoinException(JoinErrorResult.DUPLICATED_ID);
        }
        else {
            UserEntity userEntity = UserEntity.builder()
                    .userId(userId)
                    .password(password).build();

              return userRepository.save(userEntity);
        }
    }
}

