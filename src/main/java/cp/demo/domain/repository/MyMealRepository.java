package cp.demo.domain.repository;

import cp.demo.domain.entity.MenuEntity;
import cp.demo.domain.entity.MyMealEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyMealRepository extends JpaRepository<MyMealEntity, Long> {


}
