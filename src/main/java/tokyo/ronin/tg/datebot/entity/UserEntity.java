package tokyo.ronin.tg.datebot.entity;


import jakarta.persistence.*;
import org.springframework.context.annotation.Lazy;
import tokyo.ronin.tg.datebot.constant.Language;
import tokyo.ronin.tg.datebot.contoller.UserStatus;

@Entity
@Table(name = "tg_user")
public class UserEntity {
    @Id
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @Lazy
    private BiographyEntity biographyEntity;
    private String linkTelegraph;
    @OneToOne(cascade = CascadeType.ALL)
    @Lazy
    private GeometryEntity geometryEntity;
    private UserStatus userStatus = UserStatus.DEFAULT;
    private Language language = Language.ENGLISH;

    public Long getId() {
        return id;
    }

    public UserEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public BiographyEntity getBiography() {
        return biographyEntity;
    }

    public String getLinkTelegraph() {
        return linkTelegraph;
    }

    public UserEntity setLinkTelegraph(String linkTelegraph) {
        this.linkTelegraph = linkTelegraph;
        return this;
    }

    public UserEntity setBiography(BiographyEntity biographyEntity) {
        this.biographyEntity = biographyEntity;
        return this;
    }

    public GeometryEntity getGeometry() {
        return geometryEntity;
    }

    public UserEntity setGeometry(GeometryEntity geometryEntity) {
        this.geometryEntity = geometryEntity;
        return this;
    }

    public UserStatus getStatus() {
        return userStatus;
    }

    public UserEntity setStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
        return this;
    }

    public Language getLanguage() {
        return language;
    }

    public UserEntity setLanguage(Language language) {
        this.language = language;
        return this;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", biography=" + biographyEntity +
                ", linkTelegraph='" + linkTelegraph + '\'' +
                ", geometry=" + geometryEntity +
                ", personStatus=" + userStatus +
                ", language=" + language +
                '}';
    }
}

