package cp.demo.service.BasketServiceTest;

import cp.demo.domain.entity.BasketEntity;
import cp.demo.domain.repository.BasketRepository;
import cp.demo.service.BasketService;
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
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GetMyBasketTest {
    @InjectMocks
    private BasketService basketService;
    @Mock
    private BasketRepository basketRepository;
    private List<BasketEntity> myBasket=new ArrayList<>();
    final private String userId="user";

//    @Test
//    public void 장바구니가_비었을때(){
//        //given
//        doReturn(myBasket).when(basketRepository).findByUserEntity_UserId(userId);
//        //when
//        UserServiceException result= assertThrows(UserServiceException.class,()->
//                                                            basketService.getMyBasket(userId));
//        //then
//        assertThat(result.getUserServiceErrorResult()).isEqualTo(UserServiceErrorResult.BASKET_IS_EMPTY);
//    }

    @Test
    public void 장바구니_조회성공(){
        //given
        BasketEntity basketEntity= BasketEntity.builder().ingredName("쌀").capacity(300).unit("g").build();
        myBasket.add(basketEntity);
        doReturn(myBasket).when(basketRepository).findByUserEntity_UserId(userId);
        //when
        JSONObject result = basketService.getMyBasket(userId);
        //then
        assertThat(result.get("쌀")).isEqualTo("300g");
    }
}
