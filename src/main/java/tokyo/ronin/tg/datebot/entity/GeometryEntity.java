package tokyo.ronin.tg.datebot.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import tokyo.ronin.tg.datebot.entity.id.GeometryPos;

@Entity
@Table(name = "geometry")
public class GeometryEntity {
    @EmbeddedId
    private GeometryPos pos;
    private String placeName;

    public GeometryPos getPos() {
        return pos;
    }

    public GeometryEntity setPos(GeometryPos geometryPos) {
        this.pos = geometryPos;
        return this;
    }

    public String getPlaceName() {
        return placeName;
    }

    public GeometryEntity setPlaceName(String placeName) {
        this.placeName = placeName;
        return this;
    }
}