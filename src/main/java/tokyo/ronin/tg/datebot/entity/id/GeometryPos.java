package tokyo.ronin.tg.datebot.entity.id;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class GeometryPos implements Serializable {
    private Double lat;
    private Double lng;

    public GeometryPos() {
    }

    public GeometryPos(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public GeometryPos setLat(Double lat) {
        this.lat = lat;
        return this;
    }

    public Double getLng() {
        return lng;
    }

    public GeometryPos setLng(Double lng) {
        this.lng = lng;
        return this;
    }
}
