package cp.demo.service;

import cp.demo.domain.entity.UserEntity;
import cp.demo.domain.repository.UserRepository;
import cp.exception.JoinErrorResult;
import cp.exception.JoinException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class JoinServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

//    @Mock
//    JoinException joinException;

    private final String userId = "userId";
    private final String password = "password";

//    @Test
//    public void 회원가입실패_입력값없음(){
//        //given
//        String userId = NULL;
//        String password = NULL;
//
//        //when
//        doThrow(new Exception()).when(userService).join(userId,password);
//
//        //then
//        assertThatThrownBy(() -> userService.join(userId, password)).isInstanceOf(Exception.class);
//    }
//{Stubber 메소드}.when({스터빙할 클래스}).{스터빙할 메소드}

    @Test
    public void 회원가입실패_아이디중복(){
        //given
        final UserEntity userEntity=UserEntity.builder().userId(userId).build();
        doReturn(Optional.of(userEntity)).when(userRepository).findById(userId);

        //when
        final JoinException result = assertThrows(JoinException.class, () -> userService.join(userId, password));

        //then(
        assertThat(result.getJoinErrorResult()).isEqualTo(JoinErrorResult.DUPLICATED_ID);

    }

//    @Test
//    public void 회원가입실패_아이디중복이아닌경우(){
//        //given
//        final UserEntity userEntity = UserEntity.builder().userId(userId)
//                                                             .password(password).build();
//        doReturn(Optional.empty()).when(userRepository).findById(userId);
//
//        //when
//        final JoinException result = assertThrows(JoinException.class, () -> userService.join(userId, password));
//
//        //then(
//        assertThat(result.getJoinErrorResult()).isEqualTo(JoinErrorResult.WRONG_OBJECT);
//    }

    @Test
    public void 회원가입_성공(){
        //given
        UserEntity userEntity = UserEntity.builder()
                .userId(userId)
                .password(password).build();
        doReturn(Optional.empty()).when(userRepository).findById(userId);
        doReturn(userEntity).when(userRepository).save(any(UserEntity.class));

        //when
        UserEntity result = userService.join(userId, password);

        //then
        assertThat(result.getUserId()).isNotNull();

    }
}
