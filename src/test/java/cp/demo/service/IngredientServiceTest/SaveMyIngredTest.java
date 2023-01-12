package cp.demo.service.IngredientServiceTest;

import cp.demo.domain.entity.*;
import cp.demo.domain.repository.IngredientRepository;
import cp.demo.domain.repository.MenuRepository;
import cp.demo.domain.repository.MyIngredientRepository;
import cp.demo.domain.repository.UserRepository;
import cp.demo.service.IngredientService;
import cp.exception.UserServiceErrorResult;
import cp.exception.UserServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SaveMyIngredTest {
    @InjectMocks
    IngredientService ingredientService;

    @Mock
    UserRepository userRepository;

    @Mock
    IngredientRepository ingredientRepository;

    @Mock
    MyIngredientRepository myIngredientRepository;
    final private String userId="user";

//    -해당하는 재료를 찾을 필요가 없어서 제외-
//    @Test
//    public void 저장실패_해당하는재료가없음(){
//        //given
//        final HashMap<String,String> map= new HashMap<>();
//        map.put("ingredname","쌀");
//        map.put("ingredamount","500");
//        map.put("ingredunit","g");
//        //when
//        UserServiceException result= assertThrows(UserServiceException.class,()->
//                                                    ingredientService.saveMyIngredient(map,userId));
//        //then
//        assertThat(result.getUserServiceErrorResult()).isEqualTo(UserServiceErrorResult.INGREDIENT_NOT_MATCHED);
//    }

    @Test
    public void 원래가지고있던식재료가_등록될때(){
        //given
        final HashMap<String,String> map= new HashMap<>();
        map.put("ingredname","쌀");
        map.put("ingredamount","500");
        map.put("ingredunit","g");
        final UserEntity user=UserEntity.builder().userId(userId).build();
        final MyIngredientEntity myIngredientEntity=MyIngredientEntity.builder()
                                                        .userEntity(user).ingredName("쌀").capacity(300).build();
        doReturn(myIngredientEntity).when(myIngredientRepository).findByUserEntity_UserIdAndIngredName(userId,"쌀");
        //when
        MyIngredientEntity result= ingredientService.saveMyIngredient(map,userId);
        //then
        verify(myIngredientRepository,times(1)).save(any(MyIngredientEntity.class));
    }
    @Test
    public void 저장성공(){
        //given
        final HashMap<String,String> map= new HashMap<>();
        map.put("ingredname","쌀");
        map.put("ingredamount","500");
        map.put("ingredunit","g");
        final UserEntity user=UserEntity.builder().userId(userId).build();
        doReturn(Optional.of(user)).when(userRepository).findById(userId);
        //when
        MyIngredientEntity result = ingredientService.saveMyIngredient(map,userId);
        //then
        assertThat(result.getUserEntity().getUserId()).isEqualTo(userId);
        assertThat(result.getIngredName()).isEqualTo("쌀");
    }


}
