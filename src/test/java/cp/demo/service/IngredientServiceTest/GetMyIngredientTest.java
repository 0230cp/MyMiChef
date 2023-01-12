package cp.demo.service.IngredientServiceTest;

import cp.demo.domain.entity.MyIngredientEntity;
import cp.demo.domain.repository.MyIngredientRepository;
import cp.demo.service.IngredientService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class GetMyIngredientTest {
    @InjectMocks
    private IngredientService ingredientService;

    @Mock
    private MyIngredientRepository myIngredientRepository;

    private final String userId="user";

    @Test
    public void 내식재료_갱신성공(){
        //given
        final List<MyIngredientEntity> list =new ArrayList<>();
        final MyIngredientEntity myIngredientEntity= MyIngredientEntity.builder()
                                                        .ingredName("쌀").capacity(1).unit("g").build();
        list.add(myIngredientEntity);
        doReturn(list).when(myIngredientRepository).findByUserEntity_UserId(userId);
        //when
        JSONObject result=ingredientService.getMyIngredient(userId);
        //then
        assertThat(result.isEmpty()).isFalse();
    }


}
