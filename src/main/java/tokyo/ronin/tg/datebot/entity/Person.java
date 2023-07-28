package tokyo.ronin.tg.datebot.entity;

import java.util.StringJoiner;

import tokyo.ronin.tg.datebot.constant.Language;
import tokyo.ronin.tg.datebot.contoller.Status;

public class Person {
    private Long id;
    private Status status = Status.DEFAULT;
    private Geometry geometry = new Geometry();
    private Language language = Language.ENGLISH;

    public long getId() {
        return id;
    }

    public Person setId(long id) {
        this.id = id;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Language getLanguage() {
        return language;
    }

    public Person setLanguage(Language language) {
        this.language = language;
        return this;
    }

    public Person setStatus(Status status) {
        this.status = status;
        return this;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public Person setGeometry(Geometry geometry) {
        this.geometry = geometry;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("status=" + status)
                .add("geometry=" + geometry)
                .add("language=" + language)
                .toString();
    }
}
