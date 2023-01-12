package cp.demo.service;

import cp.demo.dto.UserDTO;
import cp.demo.security.SecurityDetails;
import cp.demo.domain.entity.UserEntity;
import cp.demo.domain.repository.UserRepository;
import cp.exception.*;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final UserServiceException userServiceException;

    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender emailsender;
    /**
     * 회원가입 서비스
     *
     * @param userId
     * @param password
     * @return
     */
    @Transactional
    public UserEntity join(String userId, String password){
        final Optional<UserEntity> result = userRepository.findById(userId);
        if (result.isPresent()) {
            throw new UserServiceException(UserServiceErrorResult.DUPLICATED_ID);
        }
        else {
            UserEntity userEntity = UserEntity.builder()
                    .userId(userId)
                    .password(passwordEncoder.encode(password)).build();

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

    /**
     * 비밀번호 찾기 서비스
     * @param userId
     * @param name
     * @param email
     */
    @Transactional
    public UserEntity findPw(String userId, String name, String email){
        Optional<UserEntity> user= userRepository.findByUserIdAndNameAndEmail(userId,name,email);

        if(user.isEmpty()){
            throw new UserServiceException(UserServiceErrorResult.USER_NOT_FOUND);
        }
        String password=getTempPassword();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom("rnjsgo2008@naver.com");
        message.setSubject("[MyMichef] 임시 비밀번호 발급 입니다.");
        String emailmsg = "회원님의 임시 비밀번호는 '" + password + "' 입니다." ;
        message.setText(emailmsg);
        UserEntity userEntity= user.get();
        userEntity.setPassword(passwordEncoder.encode(password));
        userRepository.save(userEntity);
        emailsender.send(message);

        return userEntity;
    }
    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }




    /**
     *  마이페이지 정보 불러오기 서비스
     */

    public UserDTO findUser(String userId){
       Optional<UserEntity> userEntity = Optional.of(new UserEntity());
        userEntity = userRepository.findById(userId);

        if(userEntity.isEmpty()){
            throw new UserServiceException(UserServiceErrorResult.NO_DATA);
        }else {
            return userEntity.get().toDto();
        }
    }

    /**
     *  마이페이지 정보 수정 서비스
     */
    @Transactional
    public UserEntity modifyUser(UserDTO userDTO,String userId){
        UserEntity userEntity=userRepository.findById(userId).get();
        userEntity.setName(userDTO.getName());
        userEntity.setCalorie(userDTO.getCalorie());
        userEntity.setHeight(userDTO.getHeight());
        userEntity.setWeight(userDTO.getWeight());
        return userRepository.save(userEntity);
    }

}





