package tokyo.ronin.tg.datebot.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

import tokyo.ronin.tg.datebot.entity.Person;


@Repository
public class UserRepository {

    Map<Long, Person> userMap = new HashMap<>();

    public Person getUserById(Long userid) {
        if (!userMap.containsKey(userid)) {
            createUser(userid);
        }
        return userMap.get(userid);
    }

    public Person update(Person person) {
        return userMap.put(person.getId(), person);
    }

    private Person createUser(Long userId) {
        return userMap.put(userId, new Person().setId(userId));
    }
}
