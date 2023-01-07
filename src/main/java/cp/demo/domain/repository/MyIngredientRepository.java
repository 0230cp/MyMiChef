package cp.demo.domain.repository;

import cp.demo.domain.entity.MyIngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyIngredientRepository extends JpaRepository<MyIngredientEntity, Long> {
}
