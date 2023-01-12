package cp.demo.domain.repository;

import cp.demo.domain.entity.IngredientEntity;
import cp.demo.domain.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {

}
