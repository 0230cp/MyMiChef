package cp.demo.service.RecommendServiceTest;

import cp.demo.domain.entity.*;
import cp.demo.domain.repository.*;
import cp.demo.service.RecommendService;
import cp.exception.RecommendServiceErrorResult;
import cp.exception.RecommendServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class DefaultRecommendTest {

    @Mock
    MyMealEntity myMealEntity;
    @Mock
    UserEntity userEntity;
    @Mock
    MenuEntity menuEntity;
    @Mock
    MyIngredientEntity myIngredientEntity;
    @Mock
    IngredientEntity ingredientEntity;

    @Mock
    MenuRepository menuRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    MyMealRepository MyMealRepository;
    @Mock
    MyIngredientRepository myIngredientRepository;
    @Mock
    IngredientRepository ingredientRepository;
    @InjectMocks
    RecommendService recommendService;

    private final String userId= "user";
    private final int calorie = 100;

    @Test
    public void 식단추천실패_DB저장정보없음(){
        //given
        String menu = "메뉴";
        String userId = "id";
        doReturn(null).when(menuRepository).findByMenuId(menu);
        //when
        RecommendServiceException result = assertThrows(RecommendServiceException.class,
                () -> recommendService.defaultRecommend(menu, userId));
        //then
        assertThat(result.getRecommendServiceErrorResult()).isEqualTo(RecommendServiceErrorResult.NO_DATA);
    }

    @Test
    public void 기본식단추천성공(){
        //given
        // userId로 칼로리 꺼내기, o
        UserEntity userEntity = UserEntity.builder()
                .userId(userId)
                .calorie(calorie).build();
        doReturn(Optional.of(userEntity)).when(userRepository).findById(userId);

        //userId로 내 식재료에서 내 식재료 이름(식재료 entity) list 꺼내기 o
        List<MyIngredientEntity> myIngredientList = new ArrayList<>();
        doReturn(myIngredientList).when(myIngredientRepository).findByUserEntity_UserId(userId);
        myIngredientEntity = MyIngredientEntity.builder()
                .userEntity(UserEntity.builder()
                        .userId(userId).build())
                        .ingredName("재료").build();
        myIngredientList.add(myIngredientEntity);

        //식재료이름으로 ingredRepository에서 ingredEntity(menuId) List 꺼내기 o
        List<IngredientEntity> ingredList = new ArrayList<>();
        ingredientEntity = IngredientEntity.builder()
                .name("재료")
                .menuEntity(MenuEntity.builder()
                        .menuId("1").build())
                .build();
        doReturn(ingredientEntity).when(ingredientRepository).findByName("재료");

        //menuId로 menuRepository에서 menuEntity꺼내기
        menuEntity = MenuEntity.builder()
                .menuId("1")
                .menuName("메뉴").build();
        doReturn(menuEntity).when(menuRepository).findByMenuId("1");

        //when
        //보유식재료로 메뉴 검색하면 메뉴 리스트 출력
        //메뉴 리스트에서 칼로리 추가 점수 주고 식재료 당 1점해서 최고점 메뉴 return
        MyMealEntity result = recommendService.defaultRecommend("1", userId);

        //then
        String res = result.getMenuEntity().getMenuName();
        assertThat(res).isEqualTo(menuRepository.findByMenuName(res).getMenuName());
    }
}
//메뉴이름, 아이디, 날짜, 시간, 레시피
//식재료 기반, 칼로리 범위 내에 존재시 +점수