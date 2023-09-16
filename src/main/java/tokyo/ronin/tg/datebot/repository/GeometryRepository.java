package tokyo.ronin.tg.datebot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tokyo.ronin.tg.datebot.entity.GeometryEntity;
import tokyo.ronin.tg.datebot.entity.id.GeometryPos;

import java.util.Optional;


@Repository
public interface GeometryRepository extends JpaRepository<GeometryEntity, GeometryPos> {

    Optional<GeometryEntity> findByGeometryPos(GeometryPos geometryPos);

}
