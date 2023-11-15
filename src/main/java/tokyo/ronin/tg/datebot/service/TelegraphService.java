package tokyo.ronin.tg.datebot.service;

import org.springframework.stereotype.Service;

import tokyo.ronin.tg.datebot.constant.Language;
import tokyo.ronin.tg.datebot.entity.BiographyEntity;
import tokyo.ronin.tg.datebot.entity.UserEntity;
import tokyo.ronin.tg.datebot.models.PersonWithMessageQueue;
import tokyo.ronin.tg.datebot.telegraph.TelegraphStory;
import tokyo.ronin.tg.datebot.service.external.TelegraphRestService;

@Service
public class TelegraphService {

    private final TelegraphRestService telegraphRestService;

    public TelegraphService(TelegraphRestService telegraphRestService) {
        this.telegraphRestService = telegraphRestService;
    }

    public void createOrUpdateTelegraph(PersonWithMessageQueue personWithMessageQueue) {
        UserEntity person = personWithMessageQueue.getPerson();

        TelegraphStory telegraphStory = buildTelegraphStory(person);

        if (person.getLinkTelegraph() == null) {
            person.setLinkTelegraph(telegraphRestService.createTelegraphPage(telegraphStory));
        } else {
            telegraphRestService.editTelegraphStory(telegraphStory, person.getLinkTelegraph());
        }
    }

    //TODO refactor
    private static TelegraphStory buildTelegraphStory(UserEntity person) {
        BiographyEntity biographyEntity = person.getBiography();
        Language language = person.getLanguage();
        TelegraphStory telegraphStory = new TelegraphStory();

        telegraphStory.setTitle(biographyEntity.getName());
        telegraphStory.create()
                .addWithStrongP(biographyEntity.getGender() + " ")
                .addWithStrongP(biographyEntity.getAge() + " років")
                .addImage("/file/b6e965265c7ee8db0cc20.jpg")
                .addWithStrongP(biographyEntity.getOverview());

        telegraphStory.build();
        return telegraphStory;
    }
}