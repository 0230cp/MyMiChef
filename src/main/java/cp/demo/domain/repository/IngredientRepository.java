package cp.demo.domain.repository;

import cp.demo.domain.entity.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {
}
