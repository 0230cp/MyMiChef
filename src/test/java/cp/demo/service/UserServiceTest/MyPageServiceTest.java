package cp.demo.service.UserServiceTest;

import cp.demo.domain.entity.UserEntity;
import cp.demo.domain.repository.UserRepository;
import cp.demo.dto.UserDTO;
import cp.demo.service.UserService;
import cp.exception.UserServiceErrorResult;
import cp.exception.UserServiceException;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.OptimisticLockingFailureException;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class MyPageServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    UserEntity userEntity;

    @Mock
    UserDTO userDTO;

    private final String userId = "userId";
    private final String password = "password";
    private final String name = "name";
    private final int height = 180;
    private final int weight = 75;
    private final int calorie = 900;

    @Test
    public void 회원정보찾기실패_저장된정보없음(){
        //given
        doReturn(Optional.empty()).when(userRepository).findById(userId);
        //when
        final UserServiceException result = assertThrows(UserServiceException.class, () -> userService.findUser(userId));
        //then
        assertThat(result.getUserServiceErrorResult()).isEqualTo(UserServiceErrorResult.NO_DATA);
    }

    @Test
    public void 회원정보찾기성공(){
        //given
        doReturn(Optional.ofNullable(userEntity)).when(userRepository).findById(userId);
        userDTO = userEntity.toDto();
        //when
        UserDTO result = userService.findUser(userId);
        //then
        assertThat(result).isEqualTo(userDTO);
    }

    @Test
    public void 회원정보수정성공(){
        //given
        final String modifiedName="modifiedName";
        final UserEntity modifiedUser = UserEntity.builder().userId(userId).name(modifiedName).build();
        UserDTO userDTO= UserDTO.builder().name(modifiedName).build();
        doReturn(Optional.of(modifiedUser)).when(userRepository).findById(userId);
        doReturn(userDTO.toEntity()).when(userRepository).save(modifiedUser);
//
        //when
        UserEntity result=userService.modifyUser(userDTO,userId);
        //then
        assertThat(result.getName()).isEqualTo(modifiedName);
    }


}
