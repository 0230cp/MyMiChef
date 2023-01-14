//package cp.demo.service.RecommendServiceTest;
//
//import cp.demo.domain.entity.MenuEntity;
//import cp.demo.domain.entity.MyMealEntity;
//import cp.demo.domain.entity.UserEntity;
//import cp.demo.domain.repository.MenuRepository;
//import cp.demo.domain.repository.MyMealRepository;
//import cp.demo.service.RecommendService;
//import cp.exception.RecommendServiceErrorResult;
//import cp.exception.RecommendServiceException;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.doReturn;
//
//@ExtendWith(MockitoExtension.class)
//public class DefaultRecommendTest {
//
//    @Mock
//    MyMealEntity myMealEntity;
//
//    @Mock
//    UserEntity userEntity;
//
//    @Mock
//    MenuEntity menuEntity;
//
//    @Mock
//    MenuRepository menuRepository;
//
//    @Mock
//    MyMealRepository MyMealRepository;
//
//    @InjectMocks
//    RecommendService recommendService;
//
//    @Test
//    public void 식단추천실패_DB저장정보없음(){
//        //given
//        doReturn(null).when(menuRepository).defaultRecommend();
//        //when
//        RecommendServiceException result = assertThrows(RecommendServiceException.class,
//                () -> recommendService.defaultRecommend());
//        //then
//        assertThat(result.getRecommendServiceErrorResult()).isEqualTo(RecommendServiceErrorResult.NO_DATA);
//    }
//
//    @Test
//    public void 기본식단추천성공(){
//        //given
//        String userId = "id";
//        String menuName = "name";
//        String date = "date";
//        String time = "time";
//        List<MenuEntity> list = menuRepository.defaultRecommend();
//
//        //when
//        MyMealEntity result = recommendService.defaultRecommend();
//
//        //then
//        String res = result.getMenuEntity().getMenuName();
//        assertThat(res).isEqualTo(menuRepository.findByMenuName(res).getMenuName());
//    }
//}
////메뉴이름, 아이디, 날짜, 시간, 레시피
////식재료 기반, 칼로리 범위 내에 존재시 +점수