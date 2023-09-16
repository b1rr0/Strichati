package tokyo.ronin.tg.datebot.service;

import org.springframework.stereotype.Service;
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
        UserEntity userEntity = personWithMessageQueue.getPerson();
        BiographyEntity biographyEntity = userEntity.getBiography();

        TelegraphStory telegraphStory = new TelegraphStory();

        telegraphStory.setTitle(biographyEntity.getName());

        telegraphStory.create()
                .addWithStrongP("DA lublu tebya")
                .addImage("/file/b6e965265c7ee8db0cc20.jpg")
                .addWithStrongP("))))");

        telegraphStory.build();
        String storyLink;
        if (userEntity.getLinkTelegraph() == null) {
            storyLink = telegraphRestService.createTelegraphPage(telegraphStory);
            userEntity.setLinkTelegraph(storyLink);
        } else {
            storyLink = userEntity.getLinkTelegraph();
            telegraphRestService.editTelegraphStory(telegraphStory, storyLink);
        }
    }
}