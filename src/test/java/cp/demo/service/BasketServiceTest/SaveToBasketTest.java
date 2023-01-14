package cp.demo.service.BasketServiceTest;

import cp.demo.domain.entity.BasketEntity;
import cp.demo.domain.entity.UserEntity;
import cp.demo.domain.repository.BasketRepository;
import cp.demo.domain.repository.UserRepository;
import cp.demo.service.BasketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SaveToBasketTest {
    @InjectMocks
    private BasketService basketService;
    @Mock
    private BasketRepository basketRepository;
    @Mock
    private UserRepository userRepository;
    private HashMap<String,String> ingredient=new HashMap<>();
    final private String ingred="쌀";
    final private String amount="300";
    final private String unit="g";
    final private String userId="user";

    @Test
    public void 원래가지고있던식재료가_등록될때(){
        //given
        ingredient.put("ingred",ingred);
        ingredient.put("amount",amount);
        ingredient.put("unit",unit);
        final UserEntity user= UserEntity.builder().userId(userId).build();
        final BasketEntity basketEntity= BasketEntity.builder().userEntity(user).ingredName(ingred).build();
        doReturn(Optional.of(user)).when(userRepository).findById(userId);
        doReturn(basketEntity).when(basketRepository).findByUserEntity_UserIdAndIngredName(userId,ingred);
        //when
        basketService.saveToBasket(userId,ingredient);
        //verify
        verify(basketRepository,times(1)).save(any(BasketEntity.class));
    }

    public void 새로운식재료가_등록될때(){
        //given
        ingredient.put("ingred",ingred);
        ingredient.put("amount",amount);
        ingredient.put("unit",unit);
        final UserEntity user= UserEntity.builder().userId(userId).build();
        final BasketEntity basketEntity= BasketEntity.builder().userEntity(user).ingredName(ingred).build();
        doReturn(Optional.of(user)).when(userRepository).findById(userId);
        doReturn(null).when(basketRepository).findByUserEntity_UserIdAndIngredName(userId,ingred);
        //when
        basketService.saveToBasket(userId,ingredient);
        //verify
        verify(basketRepository,times(1)).save(any(BasketEntity.class));
    }
}
