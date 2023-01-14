package cp.demo.domain.repository;

import cp.demo.domain.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<MenuEntity, Long>{
    MenuEntity findByMenuId(String MenuId);
    MenuEntity findByMenuName(String menuName);
}


