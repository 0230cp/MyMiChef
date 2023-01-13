package cp.demo.domain.repository;

import cp.demo.domain.entity.MyIngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MyIngredientRepository extends JpaRepository<MyIngredientEntity, Long> {
    List<MyIngredientEntity> findByUserEntity_UserId(String userId);
    MyIngredientEntity findByUserEntity_UserIdAndIngredName(String userId,String ingredName);

}
