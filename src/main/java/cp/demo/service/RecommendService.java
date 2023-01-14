package cp.demo.service;

import cp.demo.domain.entity.*;
import cp.demo.domain.repository.IngredientRepository;
import cp.demo.domain.repository.MenuRepository;
import cp.demo.domain.repository.MyIngredientRepository;
import cp.demo.domain.repository.UserRepository;
import cp.exception.RecommendServiceErrorResult;
import cp.exception.RecommendServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class RecommendService {

    private final MenuRepository menuRepository;
    private final UserRepository userRepository;
    private final MyIngredientRepository myIngredientRepository;
    private final IngredientRepository ingredientRepository;


    public MyMealEntity defaultRecommend(String menuId, String userId){

        if(menuRepository.findByMenuId(menuId) == null){
            throw new RecommendServiceException(RecommendServiceErrorResult.NO_DATA);
        }

        //id로 칼로리 꺼냄.
        Optional<UserEntity> userEntity = userRepository.findById(userId);

        //id로 내 식재료 list 꺼내기
        List<MyIngredientEntity> myIngredientList = new ArrayList<>();
        myIngredientList = myIngredientRepository.findByUserEntity_UserId(userId);

        //내 식재료 list로 menuId 꺼내기 --> 쿼리문으로 list 자체를 꺼내고
        String menuName;
        List<IngredientEntity> ingredientList = new ArrayList<>();
        for(int i = 0; i <myIngredientList.size(); i++) {
            menuName = myIngredientList.get(i).getIngredName();
            ingredientList.add(ingredientRepository.findByName(menuName));
        }

        //menuId로 menuEntity 꺼내기 --> 여기까지가 식재료로만 메뉴 추출출
        List<MenuEntity> menuEntityList = new ArrayList<>();
        for(int i = 0; i <ingredientList.size(); i++){
            menuEntityList.add(menuRepository.findByMenuId(ingredientList.get(i).getName()));
        }

        //꺼낸 menuEntity 중에서 하나 myMealEntity 만들기 --> 칼로리랑 비교해서 추가 가중치





        return null;
    }

}

