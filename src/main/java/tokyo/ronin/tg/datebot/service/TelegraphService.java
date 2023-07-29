package tokyo.ronin.tg.datebot.service;

import org.springframework.stereotype.Service;
import tokyo.ronin.tg.datebot.entity.Biography;
import tokyo.ronin.tg.datebot.entity.Person;
import tokyo.ronin.tg.datebot.models.PersonWithMessageQueue;
import tokyo.ronin.tg.datebot.telegraph.TelegraphStory;
import tokyo.ronin.tg.datebot.service.theard.TelegraphRestService;

@Service
public class TelegraphService {

    private final TelegraphRestService telegraphRestService;

    public TelegraphService(TelegraphRestService telegraphRestService) {
        this.telegraphRestService = telegraphRestService;
    }

    public void createOrUpdateTelegraph(PersonWithMessageQueue personWithMessageQueue) {
        Person person = personWithMessageQueue.getPerson();
        Biography biography = person.getBiography();

        TelegraphStory telegraphStory = new TelegraphStory();

        telegraphStory.setTitle(biography.getName());

        telegraphStory.create()
                .addWithStrongP("DA lublu tebya")
                .addImage("/file/b6e965265c7ee8db0cc20.jpg")
                .addWithStrongP("))))");

        telegraphStory.build();
        String storyLink;
        if (person.getLinkTelegraph() == null) {
            storyLink = telegraphRestService.createTelegraphPage(telegraphStory);
            person.setLinkTelegraph(storyLink);
        } else {
            storyLink = person.getLinkTelegraph();
            telegraphRestService.editTelegraphStory(telegraphStory, storyLink);
        }
    }
}