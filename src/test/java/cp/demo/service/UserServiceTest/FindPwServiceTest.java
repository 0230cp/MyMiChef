package cp.demo.service.UserServiceTest;

import cp.demo.domain.entity.UserEntity;
import cp.demo.domain.repository.UserRepository;
import cp.demo.service.UserService;
import cp.exception.UserServiceErrorResult;
import cp.exception.UserServiceException;
import jdk.jshell.spi.ExecutionControl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static cp.demo.domain.entity.UserEntity.builder;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;


@ExtendWith(MockitoExtension.class)
public class FindPwServiceTest {
    @Spy
    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;
    @Mock
    JavaMailSender emailSender;

    @Spy
    private PasswordEncoder passwordEncoder;


    private final String userId="id";
    private final String name="name";
    private final String email="rnjsgo2008@naver.com";

    @Test
    public void 비밀번호찾기실패_유저정보없음(){
        //given
        doReturn(Optional.empty()).when(userRepository).findByUserIdAndNameAndEmail(userId,name,email);
        //when
        final UserServiceException result = assertThrows(UserServiceException.class,()->
                                                                userService.findPw(userId,name,email));
        //then
        assertThat(result.getUserServiceErrorResult()).isEqualTo(UserServiceErrorResult.USER_NOT_FOUND);
    }

    @Test
    public void 이메일전송실패(){
        //given
        final UserEntity userEntity= builder().userId(userId).name(name).email(email).build();
        doReturn(Optional.of(userEntity)).when(userRepository).findByUserIdAndNameAndEmail(userId,name,email);
        doThrow(new MailParseException("example")).when(emailSender).send(any(SimpleMailMessage.class));
        //when
        final MailException result= assertThrows(MailException.class,()->userService.findPw(userId,name,email));
        //then
        assertThat(result.getMessage()).isEqualTo("example");
    }
    @Test
    public void 비밀번호찾기성공(){
        //given
        final UserEntity userEntity= UserEntity.builder().userId(userId).name(name).email(email).build();
        doReturn(Optional.of(userEntity)).when(userRepository).findByUserIdAndNameAndEmail(userId,name,email);
        doReturn("1234").when(userService).getTempPassword();
        //when
        final UserEntity result= userService.findPw(userId,name,email);
        //then
        assertThat(result.getPassword()).isEqualTo(passwordEncoder.encode("1234"));
    }
}
