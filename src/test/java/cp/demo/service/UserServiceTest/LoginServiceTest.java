package cp.demo.service.UserServiceTest;

import cp.demo.security.SecurityDetails;
import cp.demo.domain.entity.UserEntity;
import cp.demo.domain.repository.UserRepository;
import cp.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

/* 로그인 성공과 실패에 대한 처리는 Spring Security에서 하므로
   여기서는 유저 정보를 가져오는 loadUserByUsername() 서비스만 구현한다.
 */
@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    private final String userId= "user1";
    private final String password= "password";

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    /*
    @Test
    public void 로그인실패_값미입력(){
        //given
        String id=null;
        String password=null;
        //when
        final LoginException result=assertThrows(LoginException.class,()-> userService.logIn(id,password));
        //then
        assertThat(result.getErrorResult()).isEqualTo(LoginErrorResult.DATA_EMPTY);
    }
     */

    @Test
    public void 유저정보_찾지못함(){
        //given
        doReturn(Optional.empty()).when(userRepository).findById(userId);
        //when
        final UsernameNotFoundException result=assertThrows(UsernameNotFoundException.class,()->
                                                                userService.loadUserByUsername(userId));
        //then
        assertThat(result.getMessage()).isEqualTo("계정이 존재하지 않습니다.");
    }

    @Test
    public void 로그인_성공(){
        //given
        final UserEntity userEntity=UserEntity.builder().userId(userId).build();
        doReturn(Optional.of(userEntity)).when(userRepository).findById(userId);
        //when
        final SecurityDetails result=userService.loadUserByUsername(userId);
        //then
        assertThat(result.getUsername()).isEqualTo(new SecurityDetails(userEntity).getUsername());
    }
}
