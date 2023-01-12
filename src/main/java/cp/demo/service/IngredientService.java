package cp.demo.service;

import com.sun.source.tree.InstanceOfTree;
import cp.demo.domain.entity.*;
import cp.demo.domain.repository.IngredientRepository;
import cp.demo.domain.repository.MyIngredientRepository;
import cp.demo.domain.repository.UserRepository;
import cp.exception.UserServiceErrorResult;
import cp.exception.UserServiceException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IngredientService {
    private final UserRepository userRepository;

    private final IngredientRepository ingredientRepository;

    private final MyIngredientRepository myIngredientRepository;

    /**
     * 전체 식재료 조회
     * @return
     */
    public JSONObject getIngredient(){
        JSONObject jsonObject= new JSONObject();
        List<IngredientEntity> allIngred= ingredientRepository.findAll();
        if(allIngred.isEmpty())
            throw new UserServiceException(UserServiceErrorResult.INGREDIENT_NOT_FOUND);

        for(int i=0;i<allIngred.size();i++){
            jsonObject.put(allIngred.get(i).getName(),allIngred.get(i).getUnit());
        }

        return jsonObject;
    }

    /**
     * 내 식재료 저장
     * @param ingredient
     * @param userId
     * @return
     */
    @Transactional
    public MyIngredientEntity saveMyIngredient(HashMap<String,String> ingredient, String userId){
        String name=ingredient.get("ingredname");
        int capacity=Integer.parseInt(ingredient.get("ingredamount"));
        String unit=ingredient.get("ingredunit");

        MyIngredientEntity myIngredientEntity=myIngredientRepository
                                                .findByUserEntity_UserIdAndIngredName(userId,name);

        if(myIngredientEntity==null){
            UserEntity user=userRepository.findById(userId).get();
            myIngredientEntity=MyIngredientEntity.builder()
                    .userEntity(user).ingredName(name).capacity(capacity).unit(unit).build();
        }
        else{
            myIngredientEntity.setCapacity(myIngredientEntity.getCapacity()+capacity);
        }
        myIngredientRepository.save(myIngredientEntity);
        return myIngredientEntity;
    }

    /**
     * 내 식재료 조회
     * @param userId
     * @return
     */
    public JSONObject getMyIngredient(String userId){
        JSONObject jsonObject= new JSONObject();
        List <MyIngredientEntity> myIngredientEntity= myIngredientRepository.findByUserEntity_UserId(userId);
        for(int i=0;i<myIngredientEntity.size();i++)
            jsonObject.put(myIngredientEntity.get(i).getIngredName(),
                    myIngredientEntity.get(i).getCapacity()+myIngredientEntity.get(i).getUnit());

        return jsonObject;
    }

    /**
     * 내 식재료 삭제
     * @param userId
     * @param ingredName
     */
    @Transactional
    public void deleteMyingred(String userId,String ingredName){
        MyIngredientEntity myIngredientEntity=myIngredientRepository
                                        .findByUserEntity_UserIdAndIngredName(userId,ingredName);
        myIngredientRepository.delete(myIngredientEntity);

    }


}
