package tokyo.ronin.tg.datebot.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import tokyo.ronin.tg.datebot.entity.id.GeometryPos;

@Entity
@Table(name = "geometry")
public class GeometryEntity {
    @EmbeddedId
    private GeometryPos geometryPos;
    private String placeName;

    public GeometryPos getGeometryPos() {
        return geometryPos;
    }

    public GeometryEntity setPos(GeometryPos geometryPos) {
        this.geometryPos = geometryPos;
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