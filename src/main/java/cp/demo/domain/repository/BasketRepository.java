package cp.demo.domain.repository;

import cp.demo.domain.entity.BasketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<BasketEntity, Long> {
    BasketEntity findByUserEntity_UserIdAndIngredName(String userId, String ingred);

    List<BasketEntity> findByUserEntity_UserId(String userid);
}
