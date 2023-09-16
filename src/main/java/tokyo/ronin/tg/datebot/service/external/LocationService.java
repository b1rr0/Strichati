package tokyo.ronin.tg.datebot.service.external;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Location;
import tokyo.ronin.tg.datebot.entity.GeometryEntity;
import tokyo.ronin.tg.datebot.entity.UserEntity;
import tokyo.ronin.tg.datebot.entity.id.GeometryPos;
import tokyo.ronin.tg.datebot.resource.JOpenResource;
import tokyo.ronin.tg.datebot.service.GeometryService;
import tokyo.ronin.tg.datebot.service.UserService;

import java.util.Objects;
import java.util.stream.Stream;

@Service
public class LocationService {

    private final JOpenCageGeocoder jOpenCageGeocoder;
    private final UserService userService;
    private final GeometryService geometryService;

    public LocationService(JOpenResource jOpenResource, UserService userService, GeometryService geometryService) {
        this.jOpenCageGeocoder = new JOpenCageGeocoder(jOpenResource.getSecret());
        this.userService = userService;
        this.geometryService = geometryService;
    }

    public void setLocationToPerson(UserEntity userEntity, String message) {
        JOpenCageForwardRequest request = new JOpenCageForwardRequest(message);
        request.setMinConfidence(1);
        request.setNoAnnotations(false);
        request.setNoDedupe(true);
        request.setLanguage(userEntity.getLanguage().getData());
        JOpenCageResponse response = jOpenCageGeocoder.forward(request);
        JOpenCageResult result = response.getResults()
                .stream()
                .findFirst()
                .orElseThrow();
        resultToPerson(userEntity, result);
    }

    public void setLocationToPerson(UserEntity userEntity, Location loc) {
        JOpenCageReverseRequest request = new JOpenCageReverseRequest(loc.getLatitude(), loc.getLongitude());
        request.setNoAnnotations(true);
        request.setLanguage(userEntity.getLanguage().getData());
        JOpenCageResponse response = jOpenCageGeocoder.reverse(request);
        JOpenCageResult result = response.getResults()
                .stream()
                .findFirst()
                .orElseThrow();
        resultToPerson(userEntity, result);
    }

    private void resultToPerson(UserEntity userEntity, JOpenCageResult result) {
        GeometryEntity geometryEntity = geometryEntityFromJOpenCage(result);
        geometryEntity = geometryService.getOrCreate(geometryEntity);
        userEntity.setGeometry(geometryEntity);
        userService.update(userEntity);
    }

    private GeometryEntity geometryEntityFromJOpenCage(JOpenCageResult result) {
        String cityFormat = cityFormat(result);
        JOpenCageLatLng jOpenGeometry = result.getGeometry();
        GeometryPos geometryPos = new GeometryPos()
                .setLat(jOpenGeometry.getLat())
                .setLng(jOpenGeometry.getLng());

        return new GeometryEntity()
                .setPos(geometryPos)
                .setPlaceName(cityFormat);
    }

    private String cityFormat(JOpenCageResult result) {
        JOpenCageComponents components = result.getComponents();
        String place = Stream.of(components.getCity(), components.getTown(), components.getVillage())
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(StringUtils.EMPTY);

        String state = components.getState() == null ? StringUtils.EMPTY : ", " + components.getState();
        String county = components.getCountry() == null ? StringUtils.EMPTY : ", " + components.getCountry();
        return place + state + county;
    }
}
