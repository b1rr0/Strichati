package tokyo.ronin.tg.datebot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tokyo.ronin.tg.datebot.entity.UserEntity;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Override
    Optional<UserEntity> findById(Long id);
}
