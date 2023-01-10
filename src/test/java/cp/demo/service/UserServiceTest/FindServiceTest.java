package cp.demo.service.UserServiceTest;

import cp.demo.domain.entity.UserEntity;
import cp.demo.domain.repository.UserRepository;
import cp.demo.service.UserService;
import cp.exception.UserServiceErrorResult;
import cp.exception.UserServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.module.FindException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class FindServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    UserEntity userEntity;


    private final String userId = "userId";
    private final String password = "password";
    private final String userName = "userName";

    @Test
    public void 아이디찾기실패_저장된정보없음(){
        //given
        doReturn(null).when(userRepository).findByName(userName);
        //when
        final UserServiceException result = assertThrows(UserServiceException.class, () -> userService.findId(userName));
        //then
        assertThat(result.getUserServiceErrorResult()).isEqualTo(UserServiceErrorResult.NO_DATA);
    }

    @Test
    public void 아이디찾기성공(){
        doReturn(userEntity).when(userRepository).findByName(userName);
        doReturn(userId).when(userEntity).getUserId();

        //when
        String result = userService.findId(userName);

        //then
        assertThat(result).isEqualTo(userId);
    }
}
