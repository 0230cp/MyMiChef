package cp.demo.service;

import cp.demo.domain.entity.*;
import cp.demo.domain.repository.*;
import cp.exception.UserServiceErrorResult;
import cp.exception.UserServiceException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BasketService {
    private final UserRepository userRepository;
    private final MenuRepository menuRepository;
    private final IngredientRepository ingredientRepository;
    private final MyIngredientRepository myIngredientRepository;
    private final BasketRepository basketRepository;
    private String menuName;
    private MenuEntity menu;
    private List<IngredientEntity> ingredientList;

    /**
     * 추천받은 식단 화면에서 장바구니에 추가 버튼을 누르면, 해당하는 메뉴의 재료가 장바구니에 담아짐
     * @param userId
     * @param menuList
     */
    @Transactional
    public void RecommendToBasket(String userId, HashMap<String, String> menuList){
        for(int i=0;i<menuList.size();i++){
            menuName=menuList.get(String.valueOf(i));
            menu= menuRepository.findByMenuName(menuName);
            UserEntity user= userRepository.findById(userId).get();
            if(menu==null)
                throw new UserServiceException(UserServiceErrorResult.MENU_NOT_FOUND);

            ingredientList=ingredientRepository.findByMenuEntity_MenuId((menu.getMenuId()));
            if(ingredientList.isEmpty())
                throw new UserServiceException(UserServiceErrorResult.INGREDIENT_NOT_FOUND);

            for(int j=0; j<ingredientList.size();j++){
                IngredientEntity ingredient=ingredientList.get(j);
                MyIngredientEntity myIngredientEntity= myIngredientRepository.findByUserEntity_UserIdAndIngredName(userId,ingredient.getName());
                if(myIngredientEntity==null){
                     BasketEntity basketEntity =BasketEntity.builder().userEntity(user)
                    .ingredName(ingredient.getName()).capacity(ingredient.getCapacity()).unit(ingredient.getUnit()).build();
                     basketRepository.save(basketEntity);

                }
                else{
                    int capacity=ingredient.getCapacity()-myIngredientEntity.getCapacity();
                    if(capacity>0) {
                        BasketEntity basketEntity = BasketEntity.builder().userEntity(user)
                                .ingredName(ingredient.getName()).capacity(capacity).unit(ingredient.getUnit()).build();
                        basketRepository.save(basketEntity);

                    }
                }
            }
        }
    }

    /**
     * 장바구니 화면에서 장바구니에 식재료 추가
     * @param userId
     * @param ingredient
     */
    @Transactional
    public void saveToBasket(String userId, HashMap<String, String> ingredient){
        String ingredName=ingredient.get("ingred");
        int capacity=Integer.parseInt(ingredient.get("amount"));
        String unit= ingredient.get("unit");
        UserEntity user= userRepository.findById(userId).get();
        BasketEntity basketEntity=basketRepository.findByUserEntity_UserIdAndIngredName(userId,ingredName);
        if(basketEntity==null){
            basketEntity= BasketEntity.builder()
                                .userEntity(user).ingredName(ingredName).capacity(capacity).unit(unit).build();
        }
        else
            basketEntity.setCapacity(basketEntity.getCapacity()+capacity);
        basketRepository.save(basketEntity);
    }

    /**
     * 내 장바구니 조회
     * @param userId
     * @return
     */
    public JSONObject getMyBasket(String userId){
        List<BasketEntity> myBasket=basketRepository.findByUserEntity_UserId(userId);
        String ingredName,capacity;
        JSONObject jsonObject=new JSONObject();
        for(int i=0;i<myBasket.size();i++){
            ingredName=myBasket.get(i).getIngredName();
            capacity=myBasket.get(i).getCapacity()+myBasket.get(i).getUnit();
            jsonObject.put(ingredName,capacity);
        }

        return jsonObject;
    }
    @Transactional
    public void basketToMyIngredient(String userId,String ingredName){
        BasketEntity basketEntity= basketRepository.findByUserEntity_UserIdAndIngredName(userId,ingredName);
        if(basketEntity==null)
            throw new UserServiceException(UserServiceErrorResult.INGREDIENT_NOT_FOUND);
        MyIngredientEntity myIngredientEntity=myIngredientRepository
                                                    .findByUserEntity_UserIdAndIngredName(userId,ingredName);
        if(myIngredientEntity==null)
            myIngredientEntity=MyIngredientEntity.builder().build();
        basketRepository.delete(basketEntity);


    }



}
