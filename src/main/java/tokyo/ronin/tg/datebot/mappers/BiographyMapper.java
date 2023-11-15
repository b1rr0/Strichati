package tokyo.ronin.tg.datebot.mappers;

import tokyo.ronin.tg.datebot.entity.BiographyEntity;
import tokyo.ronin.tg.datebot.models.UserData;

public class BiographyMapper {
    public static BiographyEntity toBiography(UserData user) {
        BiographyEntity biographyEntity = new BiographyEntity();
        return biographyEntity.setAge(user.getAge())
                .setName(user.getName())
                .setOverview(user.getAbout())
                .setGender(user.getGender());
    }
}