package cp.demo.domain.repository;

import cp.demo.domain.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<MenuEntity, Long>{
}


