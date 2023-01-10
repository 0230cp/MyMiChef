package cp.demo.service;

import cp.demo.security.SecurityDetails;
import cp.demo.domain.entity.UserEntity;
import cp.demo.domain.repository.UserRepository;
import cp.exception.*;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.module.FindException;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final UserServiceException userServiceException;


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
            throw new UserServiceException(UserServiceErrorResult.DUPLICATED_ID);
        }
        else {
            UserEntity userEntity = UserEntity.builder()
                    .userId(userId)
                    .password(password).build();

            return userRepository.save(userEntity);
        }
    }

    /**
     * 로그인 서비스
     * @param username the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */

    @Override
    public SecurityDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user=userRepository.findById(username);
        if(user.isEmpty())
            throw new UsernameNotFoundException("계정이 존재하지 않습니다.");
        return new SecurityDetails(user.get());
    }

    /**
     *  아이디 찾기 서비스
     */
    public String findId(String userName){
        UserEntity userEntity = userRepository.findByName(userName);

        if(userEntity == null){
           throw new UserServiceException(UserServiceErrorResult.NO_DATA);
        }
        else{
            String name;
            name = userEntity.getUserId();
            return name;
        }
    }
}





