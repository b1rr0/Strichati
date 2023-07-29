package tokyo.ronin.tg.datebot.entity;

import java.time.Instant;
import java.util.List;

public class Biography {

    private String name;
    private Instant age;
    private String overview;
    private List<String> interests;
    private Expectation expectation = Expectation.DEFAULT;

    public String getName() {
        return name;
    }

    public Biography setName(String name) {
        this.name = name;
        return this;
    }

    public Instant getAge() {
        return age;
    }

    public Biography setAge(Instant age) {
        this.age = age;
        return this;
    }

    public String getOverview() {
        return overview;
    }

    public Biography setOverview(String overview) {
        this.overview = overview;
        return this;
    }

    public List<String> getInterests() {
        return interests;
    }

    public Biography setInterests(List<String> interests) {
        this.interests = interests;
        return this;
    }

    public Expectation getExpectation() {
        return expectation;
    }

    public Biography setExpectation(Expectation expectation) {
        this.expectation = expectation;
        return this;
    }
}
