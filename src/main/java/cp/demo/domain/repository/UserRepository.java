package cp.demo.domain.repository;

import cp.demo.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByUserIdAndNameAndEmail(String userId, String name, String email);

    UserEntity findByName(final String Name);
}
