package cp.demo.service.IngredientServiceTest;

import cp.demo.domain.entity.MyIngredientEntity;
import cp.demo.domain.repository.MyIngredientRepository;
import cp.demo.service.IngredientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteMyIngredTest {
    @InjectMocks
    IngredientService ingredientService;

    @Mock
    MyIngredientRepository myIngredientRepository;

    final String userId="user";
    final String ingredName="쌀";
    @Test
    public void 식재료삭제_성공(){
        //given
        doReturn(new MyIngredientEntity()).when(myIngredientRepository)
                                            .findByUserEntity_UserIdAndIngredName(userId,ingredName);
        //when
        ingredientService.deleteMyingred(userId,ingredName);
        //then
        verify(myIngredientRepository,times(1)).delete(any(MyIngredientEntity.class));
    }
}
