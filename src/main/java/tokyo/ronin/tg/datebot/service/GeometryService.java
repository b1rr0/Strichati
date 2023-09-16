package tokyo.ronin.tg.datebot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tokyo.ronin.tg.datebot.entity.GeometryEntity;
import tokyo.ronin.tg.datebot.repository.GeometryRepository;

@Service
public class GeometryService {

    private final GeometryRepository repository;

    @Autowired
    public GeometryService(GeometryRepository repository) {
        this.repository = repository;
    }

    public GeometryEntity getOrCreate(GeometryEntity entity) {
        return repository.findByGeometryPos(entity.getPos()).orElse(repository.save(entity));
    }
}
