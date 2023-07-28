package tokyo.ronin.tg.datebot.entity;

import java.util.StringJoiner;

public class Geometry {

    private String placeName;
    private Double lat;
    private Double lng;

    public Double getLat() {
        return lat;
    }

    public Geometry setLat(Double lat) {
        this.lat = lat;
        return this;
    }

    public Double getLng() {
        return lng;
    }

    public Geometry setLng(Double lng) {
        this.lng = lng;
        return this;
    }

    public String getPlaceName() {
        return placeName;
    }

    public Geometry setPlaceName(String placeName) {
        this.placeName = placeName;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Geometry.class.getSimpleName() + "[", "]")
                .add("placeName='" + placeName + "'")
                .add("lat=" + lat)
                .add("lng=" + lng)
                .toString();
    }
}