package cp.demo.service.BasketServiceTest;

import cp.demo.domain.entity.*;
import cp.demo.domain.repository.*;
import cp.demo.service.BasketService;
import cp.exception.UserServiceErrorResult;
import cp.exception.UserServiceException;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RecommendToBasketTest {
    @InjectMocks
    private BasketService basketService;
    @Mock
    private MenuRepository menuRepository;
    @Mock
    private BasketRepository basketRepository;
    @Mock
    private IngredientRepository ingredientRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private MyIngredientRepository myIngredientRepository;

    final private String userId = "user";
    final private HashMap<String,String> menuList=new HashMap<String, String>();
    final private String menuName="김밥";

    public RecommendToBasketTest() {
    }

    @Test
    public void 메뉴정보_찾지못함(){
        //given
        menuList.put("0",menuName);
        doReturn(null).when(menuRepository).findByMenuName(menuName);
        //when
        UserServiceException result= assertThrows(UserServiceException.class,()->
                          basketService.RecommendToBasket(userId,menuList));
        //then
        assertThat(result.getUserServiceErrorResult()).isEqualTo(UserServiceErrorResult.MENU_NOT_FOUND);
    }

    @Test
    public void 메뉴에해당하는_식재료없음(){
        //given
        menuList.put("0",menuName);
        final List<IngredientEntity> ingredientList=new ArrayList<>();
        final MenuEntity menuEntity= MenuEntity.builder().menuId("1").build();
        doReturn(menuEntity).when(menuRepository).findByMenuName(menuName);
        doReturn(ingredientList).when(ingredientRepository).findByMenuEntity_MenuId("1");
        //when
        UserServiceException result = assertThrows(UserServiceException.class,()->
                                                        basketService.RecommendToBasket(userId,menuList));
        //then
        assertThat(result.getUserServiceErrorResult()).isEqualTo(UserServiceErrorResult.INGREDIENT_NOT_FOUND);
    }
    @Test
    public void 장바구니_저장성공(){
        //given
        menuList.put("0",menuName);
        final List<IngredientEntity> ingredientList=new ArrayList<>();
        final MenuEntity menuEntity= MenuEntity.builder().menuName(menuName).menuId("1").build();
        final IngredientEntity ingredient= IngredientEntity.builder().name("쌀").capacity(5).menuEntity(menuEntity).build();
        ingredientList.add(ingredient);
        final UserEntity user = UserEntity.builder().userId(userId).build();
        final MyIngredientEntity myIngredientEntity=MyIngredientEntity.builder().ingredName("쌀").capacity(3).build();
        doReturn(menuEntity).when(menuRepository).findByMenuName(menuName);
        doReturn(ingredientList).when(ingredientRepository).findByMenuEntity_MenuId("1");
        doReturn(Optional.of(user)).when(userRepository).findById(userId);
        doReturn(myIngredientEntity).when(myIngredientRepository).findByUserEntity_UserIdAndIngredName(userId,"쌀");

        //when
        basketService.RecommendToBasket(userId,menuList);
        //verify
        verify(basketRepository,times(1)).save(any(BasketEntity.class));
    }
}
