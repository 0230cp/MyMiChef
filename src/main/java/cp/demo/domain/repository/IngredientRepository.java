package cp.demo.domain.repository;

import cp.demo.domain.entity.IngredientEntity;
import cp.demo.domain.entity.MenuEntity;
import cp.demo.domain.entity.MyIngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {
    List<IngredientEntity> findByMenuEntity_MenuId(String menuId);

   @Query(nativeQuery = true,
        value=   "select id as recipe, count(name) as cnt from ingredtbl " +
                "where name In (select ingred_name from myingredtbl) " +
                "group by name having count(name) >= 1 " +
                "order by count(name) DESC")


   IngredientEntity findByName(String menuName);

}
