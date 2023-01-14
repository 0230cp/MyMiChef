package cp.demo.domain.repository;

import cp.demo.domain.entity.IngredientEntity;
import cp.demo.domain.entity.MenuEntity;
import cp.demo.domain.entity.MyIngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {

   @Query(nativeQuery = true,
           value = "select * from menutbl " +
                   "where menuName In (select ingredName from myingredtbl) " +
                   "group by ingredName having count(*) >= 1 " +
                   "order by count(menuName) DESC")
   IngredientEntity findByName(String menuName);

}
