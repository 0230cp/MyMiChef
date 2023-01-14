package cp.demo.service.BasketServiceTest;

import cp.demo.domain.entity.BasketEntity;
import cp.demo.domain.entity.MyIngredientEntity;
import cp.demo.domain.entity.UserEntity;
import cp.demo.domain.repository.BasketRepository;
import cp.demo.domain.repository.MyIngredientRepository;
import cp.demo.domain.repository.UserRepository;
import cp.demo.service.BasketService;
import cp.exception.UserServiceErrorResult;
import cp.exception.UserServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasketToMyIngredientTest {
    @InjectMocks
    private BasketService basketService;
    @Mock
    private BasketRepository basketRepository;
    @Mock
    private MyIngredientRepository myIngredientRepository;
    @Mock
    private UserRepository userRepository;
    final private String userId="user";
    final private String ingredName="쌀";
    @Test
    public void 내장바구니에서_식재료검색실패(){
        //given
        doReturn(null).when(basketRepository).findByUserEntity_UserIdAndIngredName(userId,ingredName);
        //when
        UserServiceException result= assertThrows(UserServiceException.class,()->
                                            basketService.basketToMyIngredient(userId,ingredName));
        //then
        assertThat(result.getUserServiceErrorResult()).isEqualTo(UserServiceErrorResult.INGREDIENT_NOT_FOUND);
    }
    @Test
    public void 내식재료에추가_성공(){
        //given
        final UserEntity user= UserEntity.builder().userId(userId).build();
        final BasketEntity basketEntity=BasketEntity.builder().userEntity(user).ingredName(ingredName).build();
        doReturn(basketEntity).when(basketRepository).findByUserEntity_UserIdAndIngredName(userId,ingredName);
        doReturn(Optional.of(user)).when(userRepository).findById(userId);
        //when
        basketService.basketToMyIngredient(userId,ingredName);
        //verify
        verify(basketRepository,times(1)).delete(any(BasketEntity.class));
        verify(myIngredientRepository,times(1)).save(any(MyIngredientEntity.class));
    }
}
