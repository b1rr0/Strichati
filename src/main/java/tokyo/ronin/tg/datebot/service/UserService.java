package tokyo.ronin.tg.datebot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tokyo.ronin.tg.datebot.entity.UserEntity;
import tokyo.ronin.tg.datebot.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserEntity getOrCreate(Long id) {
        return repository.findById(id)
                .orElseGet(() -> repository.save(new UserEntity().setId(id)));
    }

    public void update(UserEntity userEntity) {
        repository.save(userEntity);
    }
}
