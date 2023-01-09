package cp.demo.service;

import cp.demo.domain.entity.SecurityDetails;
import cp.demo.domain.entity.UserEntity;
import cp.demo.domain.repository.UserRepository;
import cp.exception.JoinErrorResult;
import cp.exception.JoinException;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService implements UserDetailsService {

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
    @Override
    public SecurityDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user=userRepository.findById(username);
        if(user.isEmpty())
            throw new UsernameNotFoundException("계정이 존재하지 않습니다.");
        return new SecurityDetails(user.get());
    }
}




