package tokyo.ronin.tg.datebot.service;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageComponents;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.byteowls.jopencage.model.JOpenCageResponse;
import com.byteowls.jopencage.model.JOpenCageResult;
import com.byteowls.jopencage.model.JOpenCageReverseRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Location;
import tokyo.ronin.tg.datebot.entity.Person;

@Service
public class LocationService {

    static JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder("9a1b1c8182c74e65b273c633afd5a247");


    public void setLocationToPerson(Person person, String message) {

        JOpenCageForwardRequest request = new JOpenCageForwardRequest(message);
        request.setMinConfidence(1);
        request.setNoAnnotations(false);
        request.setNoDedupe(true);
        request.setLanguage(person.getLanguage().getData());
        JOpenCageResponse response = jOpenCageGeocoder.forward(request);
        JOpenCageResult result = response.getResults()
                .stream()
                .findFirst()
                .orElseThrow();
        resultToPerson(person, result);
    }

    public void setLocationToPerson(Person person, Location loc) {
        JOpenCageReverseRequest request = new JOpenCageReverseRequest(loc.getLatitude(), loc.getLongitude());
        request.setNoAnnotations(true);
        request.setLanguage(person.getLanguage().getData());
        JOpenCageResponse response = jOpenCageGeocoder.reverse(request);
        JOpenCageResult result = response.getResults()
                .stream()
                .findFirst()
                .orElseThrow();
        resultToPerson(person, result);
    }

    private void resultToPerson(Person person, JOpenCageResult result) {
        String cityFormat = cityFormat(result);
        JOpenCageLatLng jOpenGeometry = result.getGeometry();

        person.getGeometry()
                .setLat(jOpenGeometry.getLat())
                .setLng(jOpenGeometry.getLng())
                .setPlaceName(cityFormat);
    }

    private String cityFormat(JOpenCageResult result) {
        JOpenCageComponents components = result.getComponents();
        String place = components.getCity();
        if (place == null) {
            place = components.getTown();
            if (place == null) {
                place = components.getVillage();
                if (place == null) {
                    place = StringUtils.EMPTY;
                }
            }
        }

        String state = components.getState() == null ? StringUtils.EMPTY : ", " + components.getState();
        String county = components.getCountry() == null ? StringUtils.EMPTY : ", " + components.getCountry();
        return place + state + county;
    }
}
