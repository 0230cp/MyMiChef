package cp.demo.service.IngredientServiceTest;

import cp.demo.domain.entity.IngredientEntity;
import cp.demo.domain.repository.IngredientRepository;
import cp.demo.domain.repository.UserRepository;
import cp.demo.service.IngredientService;
import cp.exception.UserServiceErrorResult;
import cp.exception.UserServiceException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
public class IngredSearchTest {
    @InjectMocks
    IngredientService ingredientService;
    @Mock
    UserRepository userRepository;
    @Mock
    IngredientRepository ingredientRepository;


    @Test
    public void 식재료목록_비어있음(){
        //given
        final List<IngredientEntity> list=new ArrayList<>();
        list.clear();
        doReturn(list).when(ingredientRepository).findAll();
        //when
        final UserServiceException result= assertThrows(UserServiceException.class,()->
                                                    ingredientService.getIngredient());
        //then
        assertThat(result.getUserServiceErrorResult()).isEqualTo(UserServiceErrorResult.INGREDIENT_NOT_FOUND);
    }

    @Test
    public void 식재료조회_성공(){
        //given
        final IngredientEntity ingredientEntity= IngredientEntity.builder().name("쌀").unit("g").build();
        final List<IngredientEntity> list= new ArrayList<>();
        list.add(ingredientEntity);
        doReturn(list).when(ingredientRepository).findAll();
        //when
        JSONObject result= ingredientService.getIngredient();
        //then
        assertThat(result.get("쌀")).isEqualTo("g");
    }

}
