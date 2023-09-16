package tokyo.ronin.tg.datebot.entity;

import jakarta.persistence.*;
import tokyo.ronin.tg.datebot.constant.Expectation;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "biography")
public class BiographyEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private Instant age;
    private String overview;
    @ElementCollection
    private List<String> interests;
    private Expectation expectation = Expectation.DEFAULT;

    public UUID getId() {
        return id;
    }

    public BiographyEntity setId(UUID uuid) {
        this.id = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public BiographyEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Instant getAge() {
        return age;
    }

    public BiographyEntity setAge(Instant age) {
        this.age = age;
        return this;
    }

    public String getOverview() {
        return overview;
    }

    public BiographyEntity setOverview(String overview) {
        this.overview = overview;
        return this;
    }

    public List<String> getInterests() {
        return interests;
    }

    public BiographyEntity setInterests(List<String> interests) {
        this.interests = interests;
        return this;
    }

    public Expectation getExpectation() {
        return expectation;
    }

    public BiographyEntity setExpectation(Expectation expectation) {
        this.expectation = expectation;
        return this;
    }
}
