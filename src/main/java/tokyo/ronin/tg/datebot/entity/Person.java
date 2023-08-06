package tokyo.ronin.tg.datebot.entity;


import tokyo.ronin.tg.datebot.constant.Language;
import tokyo.ronin.tg.datebot.contoller.PersonStatus;

public class Person {
    private Long id;
    private Biography biography = new Biography();
    private String linkTelegraph;
    private Geometry geometry = new Geometry();
    private PersonStatus personStatus = PersonStatus.DEFAULT;
    private Language language = Language.ENGLISH;

    public Long getId() {
        return id;
    }

    public Person setId(Long id) {
        this.id = id;
        return this;
    }

    public Biography getBiography() {
        return biography;
    }

    public String getLinkTelegraph() {
        return linkTelegraph;
    }

    public Person setLinkTelegraph(String linkTelegraph) {
        this.linkTelegraph = linkTelegraph;
        return this;
    }

    public Person setBiography(Biography biography) {
        this.biography = biography;
        return this;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public Person setGeometry(Geometry geometry) {
        this.geometry = geometry;
        return this;
    }

    public PersonStatus getStatus() {
        return personStatus;
    }

    public Person setStatus(PersonStatus personStatus) {
        this.personStatus = personStatus;
        return this;
    }

    public Language getLanguage() {
        return language;
    }

    public Person setLanguage(Language language) {
        this.language = language;
        return this;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", biography=" + biography +
                ", linkTelegraph='" + linkTelegraph + '\'' +
                ", geometry=" + geometry +
                ", personStatus=" + personStatus +
                ", language=" + language +
                '}';
    }
}

